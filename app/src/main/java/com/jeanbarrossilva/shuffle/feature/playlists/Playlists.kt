package com.jeanbarrossilva.shuffle.feature.playlists

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.rounded.AutoMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.loadable.list.serialize
import com.jeanbarrossilva.shuffle.core.domain.Playlist
import com.jeanbarrossilva.shuffle.core.samples.samples
import com.jeanbarrossilva.shuffle.feature.generator.extensions.copy
import com.jeanbarrossilva.shuffle.feature.playlists.ui.PlaylistButton
import com.jeanbarrossilva.shuffle.platform.theme.ShuffleTheme
import com.jeanbarrossilva.shuffle.platform.theme.change.OnBottomAreaAvailabilityChangeListener
import com.jeanbarrossilva.shuffle.platform.theme.extensions.plus
import com.jeanbarrossilva.shuffle.std.loadable.ListLoadable

@Composable
fun Playlists(
    viewModel: PlaylistsViewModel,
    onNavigationToPlaylist: (Playlist) -> Unit,
    onNavigationToGenerator: () -> Unit,
    onBottomAreaAvailabilityChangeListener: OnBottomAreaAvailabilityChangeListener,
    modifier: Modifier = Modifier
) {
    val playlistsLoadable by viewModel.songsListLoadableFlow.collectAsState()

    Playlists(
        playlistsLoadable,
        onNavigationToPlaylist,
        onNavigationToGenerator,
        onBottomAreaAvailabilityChangeListener,
        modifier
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun Playlists(
    playlistsLoadable: ListLoadable<Playlist>,
    onNavigationToPlaylist: (Playlist) -> Unit,
    onNavigationToGenerator: () -> Unit,
    onBottomAreaAvailabilityChangeListener: OnBottomAreaAvailabilityChangeListener,
    modifier: Modifier = Modifier
) {
    val topAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val lazyListState = rememberLazyListState()
    val isBottomAreaAvailable by remember(lazyListState) {
        derivedStateOf {
            lazyListState.canScrollForward
        }
    }

    DisposableEffect(isBottomAreaAvailable) {
        onBottomAreaAvailabilityChangeListener.onBottomAreaAvailabilityChange(isBottomAreaAvailable)
        onDispose { }
    }

    Scaffold(
        modifier,
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        "Playlists",
                        Modifier.padding(
                            horizontal = -ShuffleTheme.spacings.extraSmall +
                                ShuffleTheme.spacings.large
                        )
                    )
                },
                scrollBehavior = topAppBarScrollBehavior
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = onNavigationToGenerator) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(ShuffleTheme.spacings.medium),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    @Suppress("SpellCheckingInspection")
                    Icon(ShuffleTheme.Icons.AutoMode, contentDescription = "Gerar")

                    @Suppress("SpellCheckingInspection")
                    Text("Gerar", color = LocalContentColor.current)
                }
            }
        }
    ) { padding ->
        LazyColumn(
            Modifier.nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
            lazyListState,
            contentPadding = padding +
                PaddingValues(ShuffleTheme.spacings.large).copy(bottom = 0.dp) +
                ShuffleTheme.overlays.fab,
            verticalArrangement = Arrangement.spacedBy(ShuffleTheme.spacings.medium)
        ) {
            when (playlistsLoadable) {
                is ListLoadable.Loading -> {
                    items(24) {
                        PlaylistButton()
                    }
                }
                is ListLoadable.Populated -> {
                    items(playlistsLoadable.content) { playlist ->
                        PlaylistButton(playlist, onClick = { onNavigationToPlaylist(playlist) })
                    }
                }
                else -> { }
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun LoadingPlaylistsPreview() {
    ShuffleTheme {
        Playlists(ListLoadable.Loading())
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun LoadedPlaylistsPreview() {
    ShuffleTheme {
        Playlists(ListLoadable.Populated(Playlist.samples.serialize()))
    }
}

@Composable
private fun Playlists(playlistsLoadable: ListLoadable<Playlist>, modifier: Modifier = Modifier) {
    Playlists(
        playlistsLoadable,
        onNavigationToPlaylist = { },
        onNavigationToGenerator = { },
        OnBottomAreaAvailabilityChangeListener.empty,
        modifier
    )
}
