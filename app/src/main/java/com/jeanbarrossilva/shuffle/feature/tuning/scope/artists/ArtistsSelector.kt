package com.jeanbarrossilva.shuffle.feature.tuning.scope.artists

import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.shuffle.core.domain.Artist
import com.jeanbarrossilva.shuffle.platform.setting.group.SettingGroup
import com.jeanbarrossilva.shuffle.platform.theme.ShuffleTheme

@Composable
internal fun ArtistsSelector(
    selection: List<Artist>,
    onSelectionToggle: (Artist, isSelected: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val artists = remember { Artist.bts }
    val lastIndividuallySelectedArtists = remember { mutableListOf<Artist>() }
    val areAllSelected = remember(artists, selection) { selection.containsAll(artists) }

    SettingGroup(
        text = { Text("BTS") },
        action = {
            Switch(
                checked = areAllSelected,
                onCheckedChange = { isChecked ->
                    if (isChecked) {
                        artists.forEach { artist -> onSelectionToggle(artist, true) }
                    } else {
                        artists.forEach { artist ->
                            onSelectionToggle(artist, artist in lastIndividuallySelectedArtists)
                        }
                    }
                }
            )
        },
        isExpanded = !areAllSelected,
        modifier
    ) {
        Artist.bts.forEach { artist ->
            setting(
                id = artist.bandName,
                text = { Text(artist.bandName) },
                action = {
                    Switch(
                        checked = artist in selection,
                        onCheckedChange = { isChecked ->
                            with(lastIndividuallySelectedArtists) {
                                if (isChecked) add(artist) else remove(artist)
                            }
                            onSelectionToggle(artist, isChecked)
                        }
                    )
                },
                onClick = { }
            )
        }
    }
}

@Composable
@Preview
internal fun ArtistsSelectorPreview() {
    ShuffleTheme {
        ArtistsSelector(
            selection = listOf(Artist.Jungkook, Artist.Taehyung),
            onSelectionToggle = { _, _ -> }
        )
    }
}
