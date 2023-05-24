package com.jeanbarrossilva.shuffle.feature.tuning.domain

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jeanbarrossilva.shuffle.core.domain.Artist
import com.jeanbarrossilva.shuffle.core.domain.Categorization
import com.jeanbarrossilva.shuffle.core.domain.album.Album
import com.jeanbarrossilva.shuffle.feature.tuning.domain.ContentfulCategorization.OnFilterChangeListener
import com.jeanbarrossilva.shuffle.feature.tuning.scope.artists.ArtistsSelector
import com.jeanbarrossilva.shuffle.feature.tuning.scope.search.album.AlbumsSelector
import com.jeanbarrossilva.shuffle.feature.tuning.scope.search.album.AlbumsSelectorViewModel
import com.jeanbarrossilva.shuffle.feature.tuning.ui.StreamingCountGoalField
import com.jeanbarrossilva.shuffle.std.selectable.Selectable
import com.jeanbarrossilva.shuffle.std.selectable.selectAll
import com.jeanbarrossilva.shuffle.std.selectable.selection
import com.jeanbarrossilva.shuffle.std.selectable.toggling
import com.jeanbarrossilva.shuffle.std.selectable.values
import org.koin.compose.koinInject

internal sealed class ContentfulCategorization<T : Any> {
    abstract val name: String
    abstract val filter: T
    abstract val onFilterChangeListener: OnFilterChangeListener<T>

    fun interface OnFilterChangeListener<T : Any> {
        fun onFilterChange(filter: T)
    }

    data class ByAlbums(
        override val filter: List<Selectable<Album>>,
        override val onFilterChangeListener: OnFilterChangeListener<List<Selectable<Album>>>
    ) : ContentfulCategorization<List<Selectable<Album>>>() {
        @Suppress("SpellCheckingInspection")
        override val name = "Por álbum"

        constructor() : this(filter = emptyList(), OnFilterChangeListener { })

        override fun asCategorization(): Categorization {
            val albums = filter.map(Selectable<Album>::value)
            return Categorization.ByAlbums(albums)
        }

        @Composable
        override fun Content(modifier: Modifier) {
            val viewModel = koinInject<AlbumsSelectorViewModel>()
            AlbumsSelector(viewModel, modifier)
        }
    }

    data class ByArtists(
        override val filter: List<Selectable<Artist>>,
        override val onFilterChangeListener: OnFilterChangeListener<List<Selectable<Artist>>>
    ) : ContentfulCategorization<List<Selectable<Artist>>>() {
        @Suppress("SpellCheckingInspection")
        override val name = "Por artista"

        constructor() : this(filter = Artist.bts.selectAll(), OnFilterChangeListener { })

        override fun asCategorization(): Categorization {
            return Categorization.ByArtists(filter.values)
        }

        @Composable
        override fun Content(modifier: Modifier) {
            ArtistsSelector(
                filter.selection,
                onSelectionToggle = { artist, _ ->
                    onFilterChangeListener.onFilterChange(filter.toggling(artist))
                }
            )
        }
    }

    class ByStreamingCountGoal(
        override val filter: Int,
        override val onFilterChangeListener: OnFilterChangeListener<Int>
    ) : ContentfulCategorization<Int>() {
        override val name = "Por meta"

        constructor() : this(filter = 0, OnFilterChangeListener { })

        override fun asCategorization(): Categorization {
            return Categorization.ByStreamingCountGoal(filter)
        }

        @Composable
        override fun Content(modifier: Modifier) {
            StreamingCountGoalField(
                streamingCountGoal = filter,
                onStreamingCountGoalChange = onFilterChangeListener::onFilterChange,
                Modifier.fillMaxWidth()
            )
        }
    }

    @Composable
    fun Content() {
        Content(Modifier)
    }

    abstract fun asCategorization(): Categorization

    @Composable
    abstract fun Content(modifier: Modifier)

    companion object {
        val values = listOf(ByAlbums(), ByArtists(), ByStreamingCountGoal())
    }
}
