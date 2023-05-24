package com.jeanbarrossilva.shuffle.platform.theme.ui

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import com.jeanbarrossilva.shuffle.platform.theme.ShuffleTheme
import com.jeanbarrossilva.shuffle.platform.theme.extensions.LocalShapes
import com.jeanbarrossilva.shuffle.platform.theme.extensions.animateDpOffsetAsState
import com.jeanbarrossilva.shuffle.platform.theme.extensions.toDpSize

@Composable
fun DropdownField(
    isExpanded: Boolean,
    onExpansionToggle: (isExpanded: Boolean) -> Unit,
    value: String,
    label: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.(width: Dp) -> Unit
) {
    val density = LocalDensity.current
    val screenSize = with(LocalContext.current.resources.configuration) {
        DpSize(screenWidthDp.dp, screenHeightDp.dp)
    }
    var top by remember { mutableStateOf(Dp.Unspecified) }
    var size by remember { mutableStateOf(DpSize.Unspecified) }
    val indicatorContentDescription = remember(isExpanded) {
        @Suppress("SpellCheckingInspection")
        if (isExpanded) "Colapsar" else "Expandir"
    }
    val spacing = ShuffleTheme.spacings.medium
    var menuTop by remember { mutableStateOf(Dp.Unspecified) }
    var menuHeight by remember { mutableStateOf(Dp.Unspecified) }
    val menuAtBottomOffsetY = remember(top, size, spacing) {
        if (top.isSpecified && size.isSpecified) top + size.height + spacing else Dp.Unspecified
    }
    val menuAtTopOffsetY = remember(top, size, spacing) {
        if (top.isSpecified && size.isSpecified) top - size.height - spacing else Dp.Unspecified
    }
    val isMenuSpaceAvailableAtBottom =
        remember(menuTop, menuAtBottomOffsetY, menuHeight, screenSize) {
            if (
                menuTop.isSpecified &&
                menuAtBottomOffsetY.isSpecified &&
                menuHeight.isSpecified &&
                screenSize.isSpecified
            ) {
                menuTop + menuAtBottomOffsetY + menuHeight > screenSize.height
            } else {
                true
            }
        }
    val menuAtBottomIndicatorRotationDegrees by animateFloatAsState(if (isExpanded) -180f else 0f)
    val menuAtTopIndicatorRotationDegrees by animateFloatAsState(if (isExpanded) 0f else -180f)
    val menuOffset by animateDpOffsetAsState(
        if (top.isSpecified && size.isSpecified) {
            DpOffset(
                x = (screenSize.width / 2) / (size.width / 2.dp),
                y = if (isMenuSpaceAvailableAtBottom) menuAtBottomOffsetY else menuAtTopOffsetY
            )
        } else {
            DpOffset.Zero
        }
    )

    Box {
        TextField(
            value,
            onValueChange = { },
            modifier
                .clip(TextFieldDefaults.shape)
                .clickable(role = Role.DropdownList) { onExpansionToggle(true) }
                .onPlaced { coordinates ->
                    top = with(density) { coordinates.positionInParent().y.toDp() }
                    size = coordinates.size.toDpSize(density)
                },
            enabled = false,
            readOnly = true,
            label = label,
            trailingIcon = {
                Icon(
                    Icons.Rounded.ArrowDropDown,
                    indicatorContentDescription,
                    Modifier.rotate(
                        if (isMenuSpaceAvailableAtBottom) {
                            menuAtBottomIndicatorRotationDegrees
                        } else {
                            menuAtTopIndicatorRotationDegrees
                        }
                    )
                )
            },
            colors = TextFieldDefaults.colors(
                disabledTextColor = ShuffleTheme.colorScheme.onSurfaceVariant,
                disabledLabelColor = ShuffleTheme.colorScheme.onSurfaceVariant
            )
        )

        // DropdownMenu hard-codes MaterialTheme.shapes.extraSmall as the shape to be used to clip
        // its content and does not expose a parameter to work around it. 🫠
        CompositionLocalProvider(
            LocalShapes provides MaterialTheme.shapes.copy(extraSmall = ShuffleTheme.shapes.medium)
        ) {
            DropdownMenu(
                isExpanded,
                onDismissRequest = { onExpansionToggle(false) },
                Modifier
                    .background(ShuffleTheme.colorScheme.surfaceVariant)
                    .onPlaced { coordinates ->
                        menuTop = with(density) { coordinates.positionInRoot().y.toDp() }
                        menuHeight = coordinates.size.toDpSize(density).height
                    },
                menuOffset
            ) {
                if (size.isSpecified) {
                    content(size.width)
                }
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun CollapsedDropdownFieldPreview() {
    ShuffleTheme {
        DropdownField(isExpanded = false)
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun ExpandedDropdownFieldPreview() {
    ShuffleTheme {
        DropdownField(isExpanded = true)
    }
}

@Composable
private fun DropdownField(isExpanded: Boolean, modifier: Modifier = Modifier) {
    @Suppress("SpellCheckingInspection")
    DropdownField(
        isExpanded,
        onExpansionToggle = { },
        value = "Texto",
        label = { Text("Rótulo") },
        modifier
    ) { width ->
        0.until(6).forEach { index ->
            DropdownMenuItem(text = { Text("Item $index") }, onClick = { }, Modifier.width(width))
        }
    }
}
