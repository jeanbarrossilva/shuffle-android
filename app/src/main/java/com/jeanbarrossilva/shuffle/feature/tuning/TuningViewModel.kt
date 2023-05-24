package com.jeanbarrossilva.shuffle.feature.tuning

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeanbarrossilva.shuffle.feature.tuning.domain.ContentfulCategorization
import com.jeanbarrossilva.shuffle.std.extensions.mutableStateIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

class TuningViewModel internal constructor() : ViewModel() {
    private val initialEmptyCategorization = ContentfulCategorization.ByArtists()
    private val artistsFlow = MutableStateFlow(initialEmptyCategorization.filter)
    private val categorizationMutableFlow = artistsFlow
        .map { artists ->
            ContentfulCategorization.ByArtists(artists) {
                artistsFlow.value = it
            }
        }
        .mutableStateIn<ContentfulCategorization<*>>(
            viewModelScope,
            initialValue = ContentfulCategorization.ByArtists(artistsFlow.value) {
                artistsFlow.value = it
            }
        )

    internal val categorizationFlow = categorizationMutableFlow.asStateFlow()

    internal fun setCategorization(categorization: ContentfulCategorization<*>) {
        categorizationMutableFlow.value = categorization
    }
}
