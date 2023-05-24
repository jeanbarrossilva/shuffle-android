package com.jeanbarrossilva.shuffle.core.domain.album

import com.jeanbarrossilva.shuffle.core.domain.Track
import com.jeanbarrossilva.shuffle.core.infra.AlbumRepository
import kotlinx.coroutines.flow.first

fun interface AlbumProvider {
    suspend fun provide(track: Track): Album

    companion object {
        fun from(repository: AlbumRepository): AlbumProvider {
            return AlbumProvider { track ->
                repository.fetch(query = "").first()[track]
            }
        }
    }
}
