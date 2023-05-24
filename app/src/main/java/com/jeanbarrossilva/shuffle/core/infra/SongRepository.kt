package com.jeanbarrossilva.shuffle.core.infra

import com.jeanbarrossilva.shuffle.core.domain.Track
import kotlinx.coroutines.flow.Flow

interface SongRepository {
    fun fetch(): Flow<List<Track>>
}
