package com.jeanbarrossilva.shuffle.platform.theme.ui

import android.content.res.Configuration
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.shuffle.platform.theme.ShuffleTheme
import com.jeanbarrossilva.shuffle.platform.theme.extensions.backwardsNavigationArrow

@Composable
fun BackIcon(onClick: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(onClick, modifier) {
        Icon(ShuffleTheme.Icons.backwardsNavigationArrow, contentDescription = "Back")
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun BackIconPreview() {
    ShuffleTheme {
        Surface(color = ShuffleTheme.colorScheme.background) {
            BackIcon(onClick = { })
        }
    }
}
