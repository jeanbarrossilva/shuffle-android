package com.jeanbarrossilva.shuffle.core.samples

import androidx.compose.runtime.Composable
import com.jeanbarrossilva.shuffle.core.domain.Playlist
import com.jeanbarrossilva.shuffle.core.domain.Track

val Playlist.Companion.sample @Composable get() = samples.first()
val Playlist.Companion.samples @Composable get() = listOf(
    Playlist(
        title = "angel pt. 1 • the planet • face",
        tracks = listOf(Track.sample),
        url = null
    )
)
