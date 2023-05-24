package com.jeanbarrossilva.shuffle.core.room.domain.track

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
abstract class RoomTrackDao internal constructor() {
    @Query("SELECT * FROM tracks")
    internal abstract fun selectAll(): Flow<List<RoomTrackEntity>>

    @Insert
    internal abstract suspend fun insert(track: RoomTrackEntity)
}
