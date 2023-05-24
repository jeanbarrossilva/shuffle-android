package com.jeanbarrossilva.shuffle.app.destination

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jeanbarrossilva.shuffle.app.destination.destinations.GeneratorDestination
import com.jeanbarrossilva.shuffle.core.infra.PlaylistRepository
import com.jeanbarrossilva.shuffle.feature.playlists.Playlists
import com.jeanbarrossilva.shuffle.feature.playlists.PlaylistsViewModel
import com.jeanbarrossilva.shuffle.platform.theme.change.OnBottomAreaAvailabilityChangeListener
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.compose.koinInject

@Composable
@Destination
@RootNavGraph(start = true)
internal fun Playlists(
    navigator: DestinationsNavigator,
    onBottomAreaAvailabilityChangeListener: OnBottomAreaAvailabilityChangeListener,
    modifier: Modifier = Modifier
) {
    val repository = koinInject<PlaylistRepository>()
    val viewModelFactory = PlaylistsViewModel.createFactory(repository)
    val viewModel = viewModel<PlaylistsViewModel>(factory = viewModelFactory)

    Playlists(
        viewModel,
        onNavigationToPlaylist = { },
        onNavigationToGenerator = { navigator.navigate(GeneratorDestination) },
        onBottomAreaAvailabilityChangeListener,
        modifier
    )
}
