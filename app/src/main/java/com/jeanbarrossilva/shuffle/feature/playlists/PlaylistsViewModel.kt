package com.jeanbarrossilva.shuffle.feature.playlists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanbarrossilva.loadable.list.serialize
import com.jeanbarrossilva.shuffle.core.domain.Playlist
import com.jeanbarrossilva.shuffle.core.infra.PlaylistRepository
import com.jeanbarrossilva.shuffle.std.loadable.listLoadable
import kotlinx.coroutines.flow.map

class PlaylistsViewModel internal constructor(repository: PlaylistRepository) : ViewModel() {
    internal val songsListLoadableFlow =
        repository.fetch().map(List<Playlist>::serialize).listLoadable(viewModelScope)

    companion object {
        fun createFactory(repository: PlaylistRepository): ViewModelProvider.Factory {
            return viewModelFactory {
                addInitializer(PlaylistsViewModel::class) {
                    PlaylistsViewModel(repository)
                }
            }
        }
    }
}
