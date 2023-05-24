package com.jeanbarrossilva.shuffle.feature.tuning.scope.search.album.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.loadable.Loadable
import com.jeanbarrossilva.loadable.ifLoaded
import com.jeanbarrossilva.loadable.map
import com.jeanbarrossilva.shuffle.core.domain.album.Album
import com.jeanbarrossilva.shuffle.core.samples.album.sample
import com.jeanbarrossilva.shuffle.feature.generator.ui.info.SongInfoDefaults
import com.jeanbarrossilva.shuffle.platform.theme.ShuffleTheme
import com.jeanbarrossilva.shuffle.platform.theme.extensions.Placeholder
import com.jeanbarrossilva.shuffle.platform.theme.extensions.`if`
import com.jeanbarrossilva.shuffle.platform.theme.extensions.placeholder
import com.jeanbarrossilva.shuffle.std.selectable.Selectable

@Composable
internal fun AlbumInfo(modifier: Modifier = Modifier) {
    val loadable = remember {
        Loadable.Loading<Selectable<Album>>()
    }

    AlbumInfo(loadable, onSelectionToggle = { }, modifier)
}

@Composable
internal fun AlbumInfo(
    selectable: Selectable<Album>,
    onSelectionToggle: (isSelected: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val loadable = remember {
        Loadable.Loaded(selectable)
    }

    AlbumInfo(loadable, onSelectionToggle, modifier)
}

@Composable
private fun AlbumInfo(
    selectableLoadable: Loadable<Selectable<Album>>,
    onSelectionToggle: (isSelected: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val titleStyle = ShuffleTheme.typography.bodyLarge
    val isLoaded = remember(selectableLoadable) { selectableLoadable !is Loadable.Loaded }
    val placeholderColor = ArtworkDefaults.placeholderColor

    Row(modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(SongInfoDefaults.horizontalSpacing),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Artwork(
                selectableLoadable.map(Selectable<Album>::value),
                Modifier.clip(ShuffleTheme.shapes.small)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(
                    ShuffleTheme.spacings.extraSmall,
                    Alignment.CenterVertically
                )
            ) {
                Text(
                    selectableLoadable.ifLoaded { value.title }.orEmpty(),
                    Modifier
                        .placeholder(
                            Placeholder.Text { titleStyle },
                            placeholderColor,
                            isVisible = isLoaded
                        )
                        .`if`(isLoaded) { fillMaxWidth(.5f) },
                    style = titleStyle
                )

                Text(
                    selectableLoadable.ifLoaded { value.artist.soloName }.orEmpty(),
                    Modifier
                        .placeholder(Placeholder.Text(), placeholderColor, isVisible = isLoaded)
                        .`if`(isLoaded) { fillMaxWidth(.2f) }
                )
            }
        }

        selectableLoadable.ifLoaded {
            Switch(isSelected, onSelectionToggle)
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun LoadingAlbumInfoPreview() {
    ShuffleTheme {
        Surface(color = ShuffleTheme.colorScheme.background) {
            AlbumInfo()
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun LoadedAlbumInfoPreview() {
    ShuffleTheme {
        Surface(color = ShuffleTheme.colorScheme.background) {
            AlbumInfo(Selectable(Album.sample, isSelected = true), onSelectionToggle = { })
        }
    }
}
