package com.jeanbarrossilva.shuffle.app

import com.jeanbarrossilva.shuffle.core.domain.album.AlbumProvider
import com.jeanbarrossilva.shuffle.core.infra.AlbumRepository
import com.jeanbarrossilva.shuffle.core.infra.PlaylistRepository
import com.jeanbarrossilva.shuffle.core.infra.SongRepository
import com.jeanbarrossilva.shuffle.core.room.infra.RoomPlaylistRepository
import com.jeanbarrossilva.shuffle.core.room.infra.RoomSongRepository
import com.jeanbarrossilva.shuffle.core.room.infra.database
import com.jeanbarrossilva.shuffle.core.samples.album.SampleAlbumRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

@Suppress("FunctionName")
internal fun ShuffleModule(): Module {
    return module {
        single<PlaylistRepository> {
            RoomPlaylistRepository(androidContext(), database.playlistDao, database.songDao)
        }
        single<SongRepository> { RoomSongRepository(androidContext(), database.songDao) }
        single<AlbumRepository> { SampleAlbumRepository(androidContext()) }
        single { AlbumProvider.from(repository = get()) }
    }
}
