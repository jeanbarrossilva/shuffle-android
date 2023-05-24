package com.jeanbarrossilva.shuffle.feature.tuning.scope.search.album

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.loadable.list.serialize
import com.jeanbarrossilva.shuffle.core.domain.album.Album
import com.jeanbarrossilva.shuffle.core.samples.album.samples
import com.jeanbarrossilva.shuffle.feature.generator.extensions.copy
import com.jeanbarrossilva.shuffle.feature.tuning.scope.search.album.ui.AlbumInfo
import com.jeanbarrossilva.shuffle.platform.theme.ShuffleTheme
import com.jeanbarrossilva.shuffle.platform.theme.extensions.plus
import com.jeanbarrossilva.shuffle.std.loadable.ListLoadable
import com.jeanbarrossilva.shuffle.std.selectable.Selectable
import com.jeanbarrossilva.shuffle.std.selectable.selectFirst

private val listSpacing @Composable get() = ShuffleTheme.spacings.medium

@Composable
internal fun AlbumsSelector(viewModel: AlbumsSelectorViewModel, modifier: Modifier = Modifier) {
    val query by viewModel.queryFlow.collectAsState()
    val albumSelectables by viewModel.albumSelectablesLoadableFlow.collectAsState()

    AlbumsSelector(
        query,
        onQueryChange = viewModel::search,
        albumSelectables,
        onSelectionToggle = viewModel::toggle,
        modifier
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun AlbumsSelector(
    query: String,
    onQueryChange: (query: String) -> Unit,
    albumSelectablesLoadable: ListLoadable<Selectable<Album>>,
    onSelectionToggle: (Album) -> Unit,
    modifier: Modifier = Modifier
) {
    val searchFieldFocusRequester = remember(::FocusRequester)
    val screenSpacing = ShuffleTheme.spacings.large

    LaunchedEffect(Unit) {
        searchFieldFocusRequester.requestFocus()
    }

    Surface(modifier.fillMaxSize(), color = ShuffleTheme.colorScheme.background) {
        LazyColumn(
            Modifier
                .imePadding()
                .statusBarsPadding()
                .fillMaxWidth(),
            contentPadding = PaddingValues(screenSpacing).copy(top = 0.dp) +
                ShuffleTheme.overlays.fab
        ) {
            stickyHeader {
                TextField(
                    query,
                    onQueryChange,
                    Modifier
                        .focusRequester(searchFieldFocusRequester)
                        .fillMaxWidth(1f),
                    label = {
                        @Suppress("SpellCheckingInspection")
                        Text("Pesquisar...")
                    },
                    leadingIcon = {
                        @Suppress("SpellCheckingInspection")
                        Icon(ShuffleTheme.Icons.Search, contentDescription = "Pesquisar")
                    }
                )
            }

            item {
                Spacer(Modifier.height(screenSpacing))
            }

            when (albumSelectablesLoadable) {
                is ListLoadable.Loading -> albumInfo()
                is ListLoadable.Populated ->
                    albumInfo(albumSelectablesLoadable.content, onSelectionToggle)
                else -> { }
            }
        }
    }
}

private fun LazyListScope.albumInfo() {
    items(24) { index ->
        AlbumInfo()

        if (index != 23) {
            Spacer(Modifier.height(listSpacing))
        }
    }
}

private fun LazyListScope.albumInfo(
    items: List<Selectable<Album>>,
    onSelectionToggle: (Album) -> Unit
) {
    itemsIndexed(items) { index, selectable ->
        AlbumInfo(selectable, onSelectionToggle = { onSelectionToggle(selectable.value) })

        if (index != items.lastIndex) {
            Spacer(Modifier.height(listSpacing))
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun LoadingAlbumsSelectorPreview() {
    ShuffleTheme {
        AlbumsSelector(ListLoadable.Loading())
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun PopulatedAlbumsSelectorPreview() {
    ShuffleTheme {
        AlbumsSelector(ListLoadable.Populated(Album.samples.selectFirst().serialize()))
    }
}

@Composable
private fun AlbumsSelector(
    albumsLoadable: ListLoadable<Selectable<Album>>,
    modifier: Modifier = Modifier
) {
    AlbumsSelector(
        query = "",
        onQueryChange = { },
        albumsLoadable,
        onSelectionToggle = { },
        modifier
    )
}
