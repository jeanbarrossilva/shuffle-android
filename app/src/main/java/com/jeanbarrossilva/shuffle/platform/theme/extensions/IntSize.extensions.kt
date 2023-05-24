package com.jeanbarrossilva.shuffle.platform.theme.extensions

import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize

fun IntSize.toDpSize(density: Density): DpSize {
    val (widthInDp, heightInDp) = with(density) { width.toDp() to height.toDp() }
    return DpSize(widthInDp, heightInDp)
}
