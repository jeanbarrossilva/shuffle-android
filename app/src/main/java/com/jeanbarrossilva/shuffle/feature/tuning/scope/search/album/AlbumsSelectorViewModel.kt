package com.jeanbarrossilva.shuffle.feature.tuning.scope.search.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanbarrossilva.loadable.list.serialize
import com.jeanbarrossilva.shuffle.core.domain.album.Album
import com.jeanbarrossilva.shuffle.core.infra.AlbumRepository
import com.jeanbarrossilva.shuffle.std.extensions.mutableStateIn
import com.jeanbarrossilva.shuffle.std.loadable.ListLoadable
import com.jeanbarrossilva.shuffle.std.loadable.ifPopulated
import com.jeanbarrossilva.shuffle.std.loadable.listLoadable
import com.jeanbarrossilva.shuffle.std.selectable.select
import com.jeanbarrossilva.shuffle.std.selectable.toggling
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map

class AlbumsSelectorViewModel internal constructor(repository: AlbumRepository) : ViewModel() {
    private val queryMutableFlow = MutableStateFlow("")

    @OptIn(FlowPreview::class)
    private val albumSelectablesLoadableMutableFlow = queryMutableFlow
        .flatMapConcat(repository::fetch)
        .map { albums -> albums.select().serialize() }
        .listLoadable()
        .mutableStateIn(viewModelScope, ListLoadable.Loading())

    private val albumSelectablesLoadable get() = albumSelectablesLoadableFlow.value

    internal val queryFlow = queryMutableFlow.asStateFlow()
    internal val albumSelectablesLoadableFlow = albumSelectablesLoadableMutableFlow.asStateFlow()

    internal fun search(query: String) {
        queryMutableFlow.value = query
    }

    internal fun toggle(album: Album) {
        albumSelectablesLoadable.ifPopulated {
            val toggled = toggling(album).serialize()
            albumSelectablesLoadableMutableFlow.value = ListLoadable.Populated(toggled)
        }
    }

    companion object {
        fun createFactory(repository: AlbumRepository): ViewModelProvider.Factory {
            return viewModelFactory {
                addInitializer(AlbumsSelectorViewModel::class) {
                    AlbumsSelectorViewModel(repository)
                }
            }
        }
    }
}
