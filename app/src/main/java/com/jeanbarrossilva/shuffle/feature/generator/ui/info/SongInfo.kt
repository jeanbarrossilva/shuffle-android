package com.jeanbarrossilva.shuffle.feature.generator.ui.info

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jeanbarrossilva.shuffle.core.domain.Track
import com.jeanbarrossilva.shuffle.core.domain.album.AlbumProvider
import com.jeanbarrossilva.shuffle.core.domain.names
import com.jeanbarrossilva.shuffle.core.samples.sample
import com.jeanbarrossilva.shuffle.platform.theme.ShuffleTheme
import com.jeanbarrossilva.shuffle.platform.theme.extensions.LocalSampleAlbumProvider
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

object SongInfoDefaults {
    val horizontalSpacing @Composable get() = ShuffleTheme.spacings.medium + 14.dp
    val verticalSpacing @Composable get() = ShuffleTheme.spacings.small
}

@Composable
internal fun SongInfo(
    albumProvider: AlbumProvider,
    track: Track,
    type: SongType,
    onRemoval: () -> Unit,
    modifier: Modifier = Modifier
) {
    SwipeableActionsBox(
        modifier,
        endActions = listOf(
            SwipeAction(
                onSwipe = onRemoval,
                icon = {
                    Icon(
                        ShuffleTheme.Icons.Delete,
                        contentDescription = "Remover",
                        Modifier.padding(start = ShuffleTheme.spacings.medium),
                        tint = ShuffleTheme.colorScheme.onError
                    )
                },
                ShuffleTheme.colorScheme.error
            )
        )
    ) {
        SongInfo(albumProvider, track, type, modifier)
    }
}

@Composable
internal fun SongInfo(
    albumProvider: AlbumProvider,
    track: Track,
    type: SongType,
    modifier: Modifier = Modifier
) {
    val horizontalSpacing = SongInfoDefaults.horizontalSpacing

    ConstraintLayout(
        modifier
            .padding(horizontalSpacing, SongInfoDefaults.verticalSpacing)
            .fillMaxWidth()
    ) {
        val (artworkRef, headlineRef, typeIconRef) = createRefs()

        Artwork(
            albumProvider,
            track,
            Modifier
                .constrainAs(artworkRef) {
                    start.linkTo(parent.start)
                    centerVerticallyTo(parent)
                }
                .clip(ShuffleTheme.shapes.small)
        )

        Column(
            Modifier.constrainAs(headlineRef) {
                width = Dimension.fillToConstraints
                height = Dimension.fillToConstraints
                start.linkTo(artworkRef.end, horizontalSpacing)
                top.linkTo(parent.top)
                end.linkTo(typeIconRef.start, horizontalSpacing)
                bottom.linkTo(parent.bottom)
            },
            Arrangement.spacedBy(ShuffleTheme.spacings.extraSmall, Alignment.CenterVertically)
        ) {
            Text(track.title, style = ShuffleTheme.typography.bodyLarge)
            Text(track.artists.names)
        }

        Icon(
            type.icon,
            type.contentDescription,
            Modifier.constrainAs(typeIconRef) {
                end.linkTo(parent.end)
                centerVerticallyTo(parent)
            },
            ShuffleTheme.colorScheme.primary
        )
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun FocusSongInfoPreview() {
    ShuffleTheme {
        Surface(color = ShuffleTheme.colorScheme.background) {
            SongInfo(SongType.FOCUS)
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun FillerSongInfoPreview() {
    ShuffleTheme {
        Surface(color = ShuffleTheme.colorScheme.background) {
            SongInfo(SongType.FILLER)
        }
    }
}

@Composable
private fun SongInfo(type: SongType, modifier: Modifier = Modifier) {
    SongInfo(LocalSampleAlbumProvider.current, Track.sample, type, modifier)
}
