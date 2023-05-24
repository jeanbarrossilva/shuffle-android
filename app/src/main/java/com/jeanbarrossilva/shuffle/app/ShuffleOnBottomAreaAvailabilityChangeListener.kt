package com.jeanbarrossilva.shuffle.app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.jeanbarrossilva.shuffle.platform.theme.change.OnBottomAreaAvailabilityChangeListener

internal class ShuffleOnBottomAreaAvailabilityChangeListener :
    OnBottomAreaAvailabilityChangeListener {
    var isAvailable by mutableStateOf(false)

    override fun onBottomAreaAvailabilityChange(isAvailable: Boolean) {
        this.isAvailable = isAvailable
    }
}
