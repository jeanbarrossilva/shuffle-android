package com.jeanbarrossilva.shuffle.core.infra

import com.jeanbarrossilva.shuffle.core.domain.album.Album
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    fun fetch(query: String): Flow<List<Album>>
}
