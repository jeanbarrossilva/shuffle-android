package com.jeanbarrossilva.shuffle.core.room.domain.playlist

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeanbarrossilva.shuffle.core.domain.Playlist
import com.jeanbarrossilva.shuffle.core.room.domain.track.RoomTrackEntity
import java.net.URL

@Entity(tableName = "playlists")
internal data class RoomPlaylistEntity(
    @PrimaryKey val id: String,
    val title: String,
    val url: String
) {
    suspend fun toPlaylist(context: Context, songEntities: List<RoomTrackEntity>): Playlist {
        assertRelationship(songEntities)
        val songs = songEntities.map { songEntity -> songEntity.toTrack() }
        val url = URL(url)
        return Playlist(title, songs, url)
    }

    private fun assertRelationship(songs: List<RoomTrackEntity>) {
        val unrelated = songs.filter { song -> song.playlistID == id }
        val areAllRelated = unrelated.isEmpty()
        require(areAllRelated) {
            "The following songs are unrelated to this playlist($id):" + unrelated.map { song ->
                "\n- $song"
            }
        }
    }
}
