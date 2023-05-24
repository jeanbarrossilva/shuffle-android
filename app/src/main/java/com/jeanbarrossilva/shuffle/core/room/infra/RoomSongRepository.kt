package com.jeanbarrossilva.shuffle.core.room.infra

import android.content.Context
import com.jeanbarrossilva.shuffle.core.domain.Track
import com.jeanbarrossilva.shuffle.core.infra.SongRepository
import com.jeanbarrossilva.shuffle.core.room.domain.track.RoomTrackDao
import java.lang.ref.WeakReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class RoomSongRepository private constructor(
    private val contextRef: WeakReference<Context>,
    songDao: RoomTrackDao
) : SongRepository {
    private val songsFlow = songDao.selectAll().map { entities ->
        val context = contextRef.get() ?: return@map emptyList()
        entities.map { entity -> entity.toTrack() }
    }

    constructor(context: Context, songDao: RoomTrackDao) : this(WeakReference(context), songDao)

    override fun fetch(): Flow<List<Track>> {
        return songsFlow
    }
}
