package com.jeanbarrossilva.shuffle.platform.theme.spacing

import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.compositionLocalOf

/** [CompositionLocal] that provides [Spacings]. **/
internal val LocalSpacings = compositionLocalOf {
    Spacings.Unspecified
}
