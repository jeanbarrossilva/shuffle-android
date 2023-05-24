package com.jeanbarrossilva.shuffle.feature.generator.extensions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

/** Icon for forwards navigation that adapts to the direction of the layout. **/
internal val Icons.Rounded.forwardsNavigationArrow
    @Composable get() = when (LocalLayoutDirection.current) {
        LayoutDirection.Ltr -> KeyboardArrowRight
        LayoutDirection.Rtl -> KeyboardArrowLeft
    }
