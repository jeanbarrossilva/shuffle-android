package com.jeanbarrossilva.shuffle.feature.generator

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jeanbarrossilva.shuffle.core.domain.Track
import com.jeanbarrossilva.shuffle.core.domain.album.AlbumProvider
import com.jeanbarrossilva.shuffle.core.samples.album.SampleAlbumProvider
import com.jeanbarrossilva.shuffle.core.samples.samples
import com.jeanbarrossilva.shuffle.feature.generator.extensions.forwardsNavigationArrow
import com.jeanbarrossilva.shuffle.feature.generator.ui.add.AddFillerSongButton
import com.jeanbarrossilva.shuffle.feature.generator.ui.add.AddFocusSongButton
import com.jeanbarrossilva.shuffle.feature.generator.ui.info.SongInfo
import com.jeanbarrossilva.shuffle.feature.generator.ui.info.SongInfoDefaults
import com.jeanbarrossilva.shuffle.feature.generator.ui.info.SongType
import com.jeanbarrossilva.shuffle.platform.theme.ShuffleTheme
import com.jeanbarrossilva.shuffle.platform.theme.extensions.plus
import com.jeanbarrossilva.shuffle.platform.theme.ui.BackIcon

@Composable
fun Generator(
    viewModel: GeneratorViewModel,
    onBackwardsNavigation: () -> Unit,
    onNavigationToFocusSongPicker: () -> Unit,
    onNavigationToFillerSongPicker: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusSongs by viewModel.focusSongsFlow.collectAsState()
    val fillerSongs by viewModel.fillerSongsFlow.collectAsState()

    Generator(
        viewModel.albumProvider,
        focusSongs,
        onFocusSongRemoval = viewModel::removeFocusSong,
        fillerSongs,
        onBackwardsNavigation,
        onNavigationToFocusSongPicker,
        onNavigationToFillerSongPicker,
        modifier
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun Generator(
    albumProvider: AlbumProvider,
    focusTracks: List<Track>,
    onFocusSongRemoval: (Track) -> Unit,
    fillerTracks: List<Track>,
    onBackwardsNavigation: () -> Unit,
    onNavigationToFocusSongPicker: () -> Unit,
    onNavigationToFillerSongPicker: () -> Unit,
    modifier: Modifier = Modifier
) {
    val topAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        modifier,
        topBar = {
            LargeTopAppBar(
                title = {
                    @Suppress("SpellCheckingInspection")
                    Text(
                        "Gerar",
                        Modifier.padding(
                            horizontal = -ShuffleTheme.spacings.extraSmall +
                                ShuffleTheme.spacings.large
                        )
                    )
                },
                navigationIcon = { BackIcon(onClick = onBackwardsNavigation) },
                scrollBehavior = topAppBarScrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                @Suppress("SpellCheckingInspection")
                Icon(ShuffleTheme.Icons.forwardsNavigationArrow, contentDescription = "Próximo")
            }
        }
    ) { padding ->
        ConstraintLayout(
            Modifier
                .padding(padding + ShuffleTheme.overlays.fab)
                .fillMaxHeight()
        ) {
            val (songsRef, buttonsRef) = createRefs()

            LazyColumn(
                Modifier
                    .constrainAs(songsRef) {
                        height = Dimension.fillToConstraints
                        top.linkTo(parent.top)
                        centerHorizontallyTo(parent)
                        bottom.linkTo(buttonsRef.top)
                    }
                    .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
                contentPadding = PaddingValues(top = SongInfoDefaults.verticalSpacing * 2)
            ) {
                itemsIndexed(focusTracks) { index, song ->
                    SongInfo(
                        albumProvider,
                        song,
                        SongType.FOCUS,
                        onRemoval = { onFocusSongRemoval(song) }
                    )

                    if (index == focusTracks.lastIndex) {
                        Spacer(Modifier.height(SongInfoDefaults.verticalSpacing * 2))
                        Divider()
                        Spacer(Modifier.height(SongInfoDefaults.verticalSpacing * 2))
                    }
                }

                items(fillerTracks) { song ->
                    SongInfo(albumProvider, song, type = SongType.FILLER)
                }
            }

            Column(
                Modifier
                    .constrainAs(buttonsRef) {
                        centerHorizontallyTo(parent)
                        bottom.linkTo(parent.bottom)
                    }
                    .padding(horizontal = ShuffleTheme.spacings.large),
                verticalArrangement = Arrangement.spacedBy(ShuffleTheme.spacings.medium)
            ) {
                AddFocusSongButton(focusTracks.size, onClick = onNavigationToFocusSongPicker)
                AddFillerSongButton(fillerTracks.size, onClick = onNavigationToFillerSongPicker)
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun GeneratorPreview() {
    ShuffleTheme {
        Generator(
            SampleAlbumProvider(LocalContext.current),
            focusTracks = with(Track.samples) { subList(0, size / 2) },
            onFocusSongRemoval = { },
            fillerTracks = with(Track.samples) { subList(size / 2, size) },
            onBackwardsNavigation = { },
            onNavigationToFocusSongPicker = { },
            onNavigationToFillerSongPicker = { }
        )
    }
}
