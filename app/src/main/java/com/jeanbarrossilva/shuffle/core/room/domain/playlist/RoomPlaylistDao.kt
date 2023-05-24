package com.jeanbarrossilva.shuffle.core.room.domain.playlist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
abstract class RoomPlaylistDao internal constructor() {
    @Query("SELECT * FROM playlists")
    internal abstract fun selectAll(): Flow<List<RoomPlaylistEntity>>

    @Insert
    internal abstract suspend fun insert(playlist: RoomPlaylistEntity)
}
