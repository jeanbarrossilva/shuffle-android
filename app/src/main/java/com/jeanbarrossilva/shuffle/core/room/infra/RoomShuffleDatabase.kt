package com.jeanbarrossilva.shuffle.core.room.infra

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jeanbarrossilva.shuffle.core.room.domain.playlist.RoomPlaylistDao
import com.jeanbarrossilva.shuffle.core.room.domain.playlist.RoomPlaylistEntity
import com.jeanbarrossilva.shuffle.core.room.domain.track.RoomTrackDao
import com.jeanbarrossilva.shuffle.core.room.domain.track.RoomTrackEntity
import org.koin.android.ext.koin.androidContext
import org.koin.core.scope.Scope

@Database(
    entities = [RoomPlaylistEntity::class, RoomTrackEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RoomShuffleDatabase internal constructor() : RoomDatabase() {
    abstract val playlistDao: RoomPlaylistDao
    abstract val songDao: RoomTrackDao

    companion object {
        private lateinit var instance: RoomShuffleDatabase

        internal fun getInstance(context: Context): RoomShuffleDatabase {
            return if (::instance.isInitialized) {
                instance
            } else {
                instance = create(context)
                instance
            }
        }

        private fun create(context: Context): RoomShuffleDatabase {
            return Room
                .databaseBuilder(context, RoomShuffleDatabase::class.java, "to-do-db")
                .build()
        }
    }
}

val Scope.database: RoomShuffleDatabase
    get() {
        val context = androidContext()
        return RoomShuffleDatabase.getInstance(context)
    }
