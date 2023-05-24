package com.jeanbarrossilva.shuffle.feature.generator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanbarrossilva.shuffle.core.domain.Track
import com.jeanbarrossilva.shuffle.core.domain.album.AlbumProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GeneratorViewModel internal constructor(internal val albumProvider: AlbumProvider) :
    ViewModel() {
    private val focusSongsMutableFlow = MutableStateFlow(emptyList<Track>())
    private val fillerSongsMutableFlow = MutableStateFlow(emptyList<Track>())

    val focusSongsFlow = focusSongsMutableFlow.asStateFlow()
    val fillerSongsFlow = fillerSongsMutableFlow.asStateFlow()

    fun removeFocusSong(track: Track) {
        focusSongsMutableFlow.value = focusSongsFlow.value - track
    }

    companion object {
        fun createFactory(albumProvider: AlbumProvider): ViewModelProvider.Factory {
            return viewModelFactory {
                addInitializer(GeneratorViewModel::class) {
                    GeneratorViewModel(albumProvider)
                }
            }
        }
    }
}
