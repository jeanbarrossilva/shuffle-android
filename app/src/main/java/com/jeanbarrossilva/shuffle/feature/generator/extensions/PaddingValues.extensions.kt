package com.jeanbarrossilva.shuffle.feature.generator.extensions

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.takeOrElse
import com.jeanbarrossilva.shuffle.platform.theme.extensions.bottom
import com.jeanbarrossilva.shuffle.platform.theme.extensions.end
import com.jeanbarrossilva.shuffle.platform.theme.extensions.start
import com.jeanbarrossilva.shuffle.platform.theme.extensions.top

/**
 * Copies the given [PaddingValues].
 *
 * @param horizontal [Dp]s to be applied to [start] and [end].
 * @param vertical [Dp]s to be applied to [top] and [bottom].
 **/
@Composable
internal fun PaddingValues.copy(horizontal: Dp = Dp.Unspecified, vertical: Dp = Dp.Unspecified):
    PaddingValues {
    return copy(
        start = horizontal.takeOrElse { start },
        top = vertical.takeOrElse { top },
        end = horizontal.takeOrElse { end },
        bottom = vertical.takeOrElse { bottom }
    )
}

/**
 * Copies the given [PaddingValues].
 *
 * @param start [Dp]s to be applied to [start].
 * @param top [Dp]s to be applied to [top].
 * @param end [Dp]s to be applied to [end].
 * @param bottom [Dp]s to be applied to [bottom].
 **/
@Composable
internal fun PaddingValues.copy(
    start: Dp = this.start,
    top: Dp = this.top,
    end: Dp = this.end,
    bottom: Dp = this.bottom
): PaddingValues {
    return PaddingValues(start, top, end, bottom)
}
