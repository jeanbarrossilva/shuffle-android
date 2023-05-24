package com.jeanbarrossilva.shuffle.core.domain

import com.jeanbarrossilva.shuffle.core.domain.album.Album

sealed interface Categorization {
    class ByAlbums(val albums: List<Album>) : Categorization

    class ByArtists(val artists: List<Artist>) : Categorization

    class ByStreamingCountGoal(val streamingCountGoal: Int) : Categorization
}
