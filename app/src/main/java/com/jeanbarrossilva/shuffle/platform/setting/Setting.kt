package com.jeanbarrossilva.shuffle.platform.setting

import android.content.res.Configuration
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.shuffle.platform.setting.extensions.EmptyMutableInteractionSource
import com.jeanbarrossilva.shuffle.platform.setting.extensions.forwardsNavigationArrow
import com.jeanbarrossilva.shuffle.platform.setting.extensions.`if`
import com.jeanbarrossilva.shuffle.platform.theme.ShuffleTheme

/** Default values of a [Setting]. **/
internal object SettingDefaults {
    /** [Shape] by which a [Setting] is clipped by default. **/
    val shape
        @Composable get() = TextFieldDefaults.shape as CornerBasedShape
}

/**
 * Represents configuration that can be made by the user.
 *
 * @param text Secondary [Text] of the headline if the [Setting] has a [label]; otherwise, it's the
 * primary one.
 * @param action Content that may or may not be interactive that explicits the purpose or the
 * current state of the [Setting].
 * @param onClick Lambda to be run whenever a click occurs. The [Setting] becomes non-clickable if
 * it's `null`.
 * @param modifier [Modifier] to be applied to the underlying [Button].
 * @param shape [Shape] by which the [Setting] is clipped.
 * @param label Primary [Text] of the headline. Being `null` hands that role to the [text].
 **/
@Composable
internal fun Setting(
    text: @Composable () -> Unit,
    action: @Composable () -> Unit,
    onClick: (() -> Unit)?,
    modifier: Modifier = Modifier,
    label: (@Composable () -> Unit)? = null,
    shape: Shape = SettingDefaults.shape
) {
    val interactionSource = remember(onClick) {
        onClick?.let { MutableInteractionSource() } ?: EmptyMutableInteractionSource()
    }
    val textStyle = LocalTextStyle.current

    Button(
        onClick ?: { },
        modifier,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = ShuffleTheme.colorScheme.surfaceVariant,
            contentColor = ShuffleTheme.colorScheme.onSurface
        ),
        contentPadding = PaddingValues(ShuffleTheme.spacings.medium),
        interactionSource = interactionSource
    ) {
        ProvideTextStyle(textStyle.copy(color = ShuffleTheme.colorScheme.onSurface)) {
            Row(
                Modifier.fillMaxWidth(),
                Arrangement.SpaceBetween,
                Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(ShuffleTheme.spacings.extraSmall)
                ) {
                    label?.invoke()

                    ProvideTextStyle(
                        LocalTextStyle.current.`if`(label != null) {
                            copy(color = ShuffleTheme.colorScheme.onSurfaceVariant)
                        },
                        text
                    )
                }

                Spacer(Modifier.width(ShuffleTheme.spacings.medium))
                action()
            }
        }
    }
}

/** Preview of an unlabeled [Setting]. **/
@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun UnlabeledSettingPreview() {
    ShuffleTheme {
        Setting(label = null)
    }
}

/** Preview of a labeled [Setting]. **/
@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun LabeledSettingPreview() {
    ShuffleTheme {
        Setting(label = { Text("Label") })
    }
}

/**
 * Sample, no-op [Setting].
 *
 * @param modifier [Modifier] to be applied to the underlying [Setting].
 **/
@Composable
private fun Setting(label: (@Composable () -> Unit)?, modifier: Modifier = Modifier) {
    Setting(
        text = { Text("Setting") },
        action = {
            Icon(ShuffleTheme.Icons.forwardsNavigationArrow, contentDescription = "Navigate")
        },
        onClick = { },
        modifier,
        label
    )
}
