package com.jeanbarrossilva.shuffle.core.samples

import androidx.compose.runtime.Composable
import com.jeanbarrossilva.shuffle.core.domain.Track
import com.jeanbarrossilva.shuffle.core.domain.album.Album
import com.jeanbarrossilva.shuffle.core.samples.album.samples

val Track.Companion.sample @Composable get() = samples.first()
val Track.Companion.samples @Composable get() = Album.samples.flatMap(Album::tracks)
