package com.jeanbarrossilva.shuffle.platform.theme.extensions

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpOffset

@Composable
internal fun animateDpOffsetAsState(targetValue: DpOffset): State<DpOffset> {
    val x by animateDpAsState(targetValue.x)
    val y by animateDpAsState(targetValue.y)
    return remember(x, y) {
        derivedStateOf {
            DpOffset(x, y)
        }
    }
}
