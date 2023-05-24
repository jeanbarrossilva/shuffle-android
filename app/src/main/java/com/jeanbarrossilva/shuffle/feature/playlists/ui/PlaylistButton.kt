package com.jeanbarrossilva.shuffle.feature.playlists.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.rounded.Link
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.loadable.Loadable
import com.jeanbarrossilva.loadable.ifLoaded
import com.jeanbarrossilva.shuffle.core.domain.Playlist
import com.jeanbarrossilva.shuffle.core.samples.sample
import com.jeanbarrossilva.shuffle.platform.theme.ShuffleTheme
import com.jeanbarrossilva.shuffle.platform.theme.extensions.Placeholder
import com.jeanbarrossilva.shuffle.platform.theme.extensions.`if`
import com.jeanbarrossilva.shuffle.platform.theme.extensions.placeholder
import com.jeanbarrossilva.shuffle.platform.theme.ui.Button

@Composable
internal fun PlaylistButton(modifier: Modifier = Modifier) {
    val loadable = remember {
        Loadable.Loading<Playlist>()
    }

    PlaylistButton(loadable, onClick = { }, modifier)
}

@Composable
internal fun PlaylistButton(
    playlist: Playlist,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val loadable = remember(playlist) {
        Loadable.Loaded(playlist)
    }

    PlaylistButton(loadable, onClick, modifier)
}

@Composable
internal fun PlaylistButton(
    playlistLoadable: Loadable<Playlist>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val titleStyle = ShuffleTheme.typography.titleMedium
    val isLoading = remember(playlistLoadable) { playlistLoadable is Loadable.Loading }

    Button(onClick, modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
        Column(
            Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(ShuffleTheme.spacings.small)
        ) {
            Text(
                playlistLoadable.ifLoaded(Playlist::title).orEmpty(),
                Modifier
                    .placeholder(
                        Placeholder.Text { titleStyle },
                        ShuffleTheme.colorScheme.outlineVariant,
                        isVisible = isLoading
                    )
                    .`if`(isLoading) { fillMaxWidth(.5f) },
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                style = titleStyle
            )
        }

        Spacer(Modifier.width(ShuffleTheme.spacings.large))

        AnimatedVisibility(
            visible = playlistLoadable is Loadable.Loaded,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Icon(ShuffleTheme.Icons.Link, contentDescription = "Go to playlist")
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun LoadingPlaylistCardPreview() {
    ShuffleTheme {
        PlaylistButton(Loadable.Loading())
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun LoadedPlaylistCardPreview() {
    ShuffleTheme {
        PlaylistButton(Loadable.Loaded(Playlist.sample))
    }
}

@Composable
private fun PlaylistButton(playlistLoadable: Loadable<Playlist>, modifier: Modifier = Modifier) {
    PlaylistButton(playlistLoadable, onClick = { }, modifier)
}
