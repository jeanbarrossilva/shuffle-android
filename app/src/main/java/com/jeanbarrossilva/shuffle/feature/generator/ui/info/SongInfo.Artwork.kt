package com.jeanbarrossilva.shuffle.feature.generator.ui.info

import android.content.res.Configuration
import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.shuffle.core.domain.Track
import com.jeanbarrossilva.shuffle.core.domain.album.AlbumProvider
import com.jeanbarrossilva.shuffle.core.domain.names
import com.jeanbarrossilva.shuffle.core.samples.sample
import com.jeanbarrossilva.shuffle.platform.theme.ShuffleTheme
import com.jeanbarrossilva.shuffle.platform.theme.extensions.LocalSampleAlbumProvider

@Composable
internal fun Artwork(albumProvider: AlbumProvider, track: Track, modifier: Modifier = Modifier) {
    var artwork by remember { mutableStateOf<Bitmap?>(null) }
    val sizedModifier = remember(modifier) { modifier.size(64.dp) }

    LaunchedEffect(albumProvider) {
        artwork = albumProvider.provide(track).artwork
    }

    artwork.let {
        if (it != null) {
            @Suppress("SpellCheckingInspection")
            Image(
                it.asImageBitmap(),
                contentDescription = "Capa de ${track.title}, por ${track.artists.names}",
                sizedModifier
            )
        } else {
            Box(
                sizedModifier.background(ShuffleTheme.colorScheme.outlineVariant),
                contentAlignment = Alignment.Center
            ) {
                @Suppress("SpellCheckingInspection")
                Icon(ShuffleTheme.Icons.Image, contentDescription = "Sem capa")
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun NonexistentArtworkPreview() {
    ShuffleTheme {
        Artwork(Track.sample)
    }
}

@Composable
@Preview
private fun ExistingArtworkPreview() {
    ShuffleTheme {
        Artwork(Track.sample)
    }
}

@Composable
private fun Artwork(track: Track, modifier: Modifier = Modifier) {
    Artwork(LocalSampleAlbumProvider.current, track, modifier)
}
