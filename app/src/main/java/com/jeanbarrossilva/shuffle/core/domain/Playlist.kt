package com.jeanbarrossilva.shuffle.core.domain

import java.io.Serializable
import java.net.URL

data class Playlist(val title: String, val tracks: List<Track>, val url: URL?) : Serializable {
    companion object
}
