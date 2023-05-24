package com.jeanbarrossilva.shuffle.core.infra

import com.jeanbarrossilva.shuffle.core.domain.Playlist
import kotlinx.coroutines.flow.Flow

interface PlaylistRepository {
    fun fetch(): Flow<List<Playlist>>
}
