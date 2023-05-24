package com.jeanbarrossilva.shuffle.feature.generator.ui.add

import android.content.res.Configuration
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.shuffle.feature.generator.ui.Count
import com.jeanbarrossilva.shuffle.feature.generator.ui.info.SongType
import com.jeanbarrossilva.shuffle.platform.theme.ShuffleTheme
import com.jeanbarrossilva.shuffle.platform.theme.ui.Button

@Composable
internal fun AddFocusSongButton(count: Int, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick, modifier) {
        if (count == 0) {
            Icon(SongType.FOCUS.icon, SongType.FOCUS.contentDescription)
        } else {
            Count(count)
        }

        @Suppress("SpellCheckingInspection")
        Text("Adicionar música-foco", Modifier.weight(1f))
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun ZeroedAddFocusSongButtonPreview() {
    ShuffleTheme {
        AddFocusSongButton(count = 0, onClick = { })
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun NonZeroedAddFocusSongButtonPreview() {
    ShuffleTheme {
        AddFocusSongButton(count = 7, onClick = { })
    }
}
