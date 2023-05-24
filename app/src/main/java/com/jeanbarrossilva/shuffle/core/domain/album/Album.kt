package com.jeanbarrossilva.shuffle.core.domain.album

import android.graphics.Bitmap
import com.jeanbarrossilva.shuffle.core.domain.Artist
import com.jeanbarrossilva.shuffle.core.domain.Track
import java.io.Serializable

data class Album(
    val id: String,
    val artist: Artist,
    val title: String,
    val tracks: List<Track>,
    val artwork: Bitmap
) : Serializable {
    companion object
}

operator fun Collection<Album>.get(track: Track): Album {
    return first { album ->
        track in album.tracks
    }
}
