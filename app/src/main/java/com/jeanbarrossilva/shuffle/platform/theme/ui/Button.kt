package com.jeanbarrossilva.shuffle.platform.theme.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.shuffle.feature.generator.extensions.copy
import com.jeanbarrossilva.shuffle.platform.theme.ShuffleTheme

@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(
        ShuffleTheme.spacings.large
    ),
    content: @Composable RowScope.() -> Unit
) {
    ElevatedButton(
        onClick,
        modifier,
        shape = ShuffleTheme.shapes.medium,
        contentPadding = ButtonDefaults.ContentPadding.copy(vertical = ShuffleTheme.spacings.large)
    ) {
        Row(
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProvideTextStyle(LocalTextStyle.current.copy(color = LocalContentColor.current)) {
                content()
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun ButtonPreview() {
    ShuffleTheme {
        Button(onClick = { }) {
            @Suppress("SpellCheckingInspection")
            Icon(ShuffleTheme.Icons.Add, contentDescription = "Adicionar")

            @Suppress("SpellCheckingInspection")
            Text("Adicionar")
        }
    }
}
