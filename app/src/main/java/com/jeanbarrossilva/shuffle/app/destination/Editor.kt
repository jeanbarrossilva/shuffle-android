package com.jeanbarrossilva.shuffle.app.destination

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(route = "playlists/generator/editor")
internal fun Editor(navigator: DestinationsNavigator, modifier: Modifier = Modifier) {
}
