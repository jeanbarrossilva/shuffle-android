package com.jeanbarrossilva.shuffle.core.room.infra

import android.content.Context
import com.jeanbarrossilva.shuffle.core.domain.Playlist
import com.jeanbarrossilva.shuffle.core.infra.PlaylistRepository
import com.jeanbarrossilva.shuffle.core.room.domain.playlist.RoomPlaylistDao
import com.jeanbarrossilva.shuffle.core.room.domain.track.RoomTrackDao
import java.lang.ref.WeakReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

internal class RoomPlaylistRepository private constructor(
    private val contextRef: WeakReference<Context>,
    playlistDao: RoomPlaylistDao,
    trackDao: RoomTrackDao
) : PlaylistRepository {
    private val playlistsFlow =
        combine(playlistDao.selectAll(), trackDao.selectAll()) { playlistEntities, trackEntities ->
            val context = contextRef.get() ?: return@combine emptyList()
            playlistEntities.map { playlistEntity ->
                playlistEntity.toPlaylist(
                    context,
                    trackEntities
                        .filter { trackEntity -> trackEntity.playlistID == playlistEntity.id }
                )
            }
        }

    constructor(context: Context, playlistDao: RoomPlaylistDao, songDao: RoomTrackDao) :
        this(WeakReference(context), playlistDao, songDao)

    override fun fetch(): Flow<List<Playlist>> {
        return playlistsFlow
    }
}
