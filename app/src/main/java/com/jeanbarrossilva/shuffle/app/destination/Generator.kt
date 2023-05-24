package com.jeanbarrossilva.shuffle.app.destination

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jeanbarrossilva.shuffle.app.destination.destinations.TuningDestination
import com.jeanbarrossilva.shuffle.core.domain.album.AlbumProvider
import com.jeanbarrossilva.shuffle.feature.generator.Generator
import com.jeanbarrossilva.shuffle.feature.generator.GeneratorViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.compose.koinInject

@Composable
@Destination(route = "playlists/generator")
internal fun Generator(navigator: DestinationsNavigator, modifier: Modifier = Modifier) {
    val albumProvider = koinInject<AlbumProvider>()
    val viewModelFactory = GeneratorViewModel.createFactory(albumProvider)
    val viewModel = viewModel<GeneratorViewModel>(factory = viewModelFactory)

    Generator(
        viewModel,
        onBackwardsNavigation = navigator::popBackStack,
        onNavigationToFocusSongPicker = { },
        onNavigationToFillerSongPicker = { navigator.navigate(TuningDestination) },
        modifier
    )
}
