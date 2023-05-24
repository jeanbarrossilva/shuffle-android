package com.jeanbarrossilva.shuffle.core.room.domain.track

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeanbarrossilva.shuffle.core.domain.Artist
import com.jeanbarrossilva.shuffle.core.domain.Track

@Entity(tableName = "tracks")
internal data class RoomTrackEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "playlist_id") val playlistID: String,
    val title: String,
    @ColumnInfo(name = "artist_names") val artistNames: String
) {
    fun toTrack(): Track {
        val artists = artistNames.split(", ").toTypedArray().map(Artist::named)
        return Track(id, artists, title)
    }
}
