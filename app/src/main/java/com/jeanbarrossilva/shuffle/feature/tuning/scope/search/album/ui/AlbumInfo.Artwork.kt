package com.jeanbarrossilva.shuffle.feature.tuning.scope.search.album.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.rounded.BrokenImage
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.loadable.Loadable
import com.jeanbarrossilva.shuffle.core.domain.album.Album
import com.jeanbarrossilva.shuffle.core.samples.album.sample
import com.jeanbarrossilva.shuffle.platform.theme.ShuffleTheme
import com.jeanbarrossilva.shuffle.platform.theme.extensions.Placeholder
import com.jeanbarrossilva.shuffle.platform.theme.extensions.placeholder

private val Size = 64.dp

internal object ArtworkDefaults {
    val placeholderColor @Composable get() = ShuffleTheme.colorScheme.outlineVariant
}

@Composable
internal fun Artwork(albumLoadable: Loadable<Album>, modifier: Modifier = Modifier) {
    when (albumLoadable) {
        is Loadable.Loading -> LoadingArtwork(modifier)
        is Loadable.Loaded -> LoadedArtwork(albumLoadable.content, modifier)
        is Loadable.Failed -> FailedArtwork(modifier)
    }
}

@Composable
private fun LoadingArtwork(modifier: Modifier = Modifier) {
    Box(
        modifier
            .placeholder(
                Placeholder.height(Size),
                ArtworkDefaults.placeholderColor,
                isVisible = true
            )
            .width(Size)
    )
}

@Composable
private fun LoadedArtwork(album: Album, modifier: Modifier = Modifier) {
    val artwork = remember(album) {
        album.artwork.asImageBitmap()
    }

    @Suppress("SpellCheckingInspection")
    Image(
        artwork,
        contentDescription = "Capa do álbum \"${album.title}\", de ${album.artist.soloName}",
        modifier.size(Size)
    )
}

@Composable
private fun FailedArtwork(modifier: Modifier = Modifier) {
    Box(modifier.size(Size), contentAlignment = Alignment.Center) {
        @Suppress("SpellCheckingInspection")
        Icon(ShuffleTheme.Icons.BrokenImage, contentDescription = "Capa indisponível")
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun LoadingArtworkPreview() {
    ShuffleTheme {
        Artwork(Loadable.Loading())
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun LoadedArtworkPreview() {
    ShuffleTheme {
        Artwork(Loadable.Loaded(Album.sample))
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun FailedArtworkPreview() {
    ShuffleTheme {
        Artwork(Loadable.Failed(Exception()))
    }
}
