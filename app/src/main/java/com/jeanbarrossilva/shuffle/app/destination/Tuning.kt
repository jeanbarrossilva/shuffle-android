package com.jeanbarrossilva.shuffle.app.destination

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jeanbarrossilva.shuffle.feature.tuning.Tuning
import com.jeanbarrossilva.shuffle.feature.tuning.TuningViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination(route = "playlists/generator/tuning")
internal fun Tuning(navigator: DestinationsNavigator, modifier: Modifier = Modifier) {
    val viewModel = viewModel<TuningViewModel>()
    Tuning(viewModel, onBackwardsNavigation = navigator::popBackStack, modifier)
}
