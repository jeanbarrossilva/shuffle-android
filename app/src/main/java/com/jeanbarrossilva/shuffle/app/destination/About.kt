package com.jeanbarrossilva.shuffle.app.destination

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jeanbarrossilva.shuffle.feature.about.About
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
internal fun About(modifier: Modifier = Modifier) {
    About(modifier)
}
