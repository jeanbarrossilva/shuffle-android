package com.jeanbarrossilva.shuffle.core.domain
import java.io.Serializable

data class Track(
    val id: String,
    val artists: List<Artist>,
    val title: String
) : Serializable {
    constructor(id: String, artist: Artist, title: String) : this(id, listOf(artist), title)

    companion object
}
