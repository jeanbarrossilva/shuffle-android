package com.jeanbarrossilva.shuffle.feature.tuning.extensions

import com.jeanbarrossilva.shuffle.core.domain.Artist
import com.jeanbarrossilva.shuffle.core.domain.Categorization
import com.jeanbarrossilva.shuffle.core.domain.album.Album
import com.jeanbarrossilva.shuffle.feature.tuning.domain.ContentfulCategorization
import com.jeanbarrossilva.shuffle.std.selectable.Selectable
import com.jeanbarrossilva.shuffle.std.selectable.select
import com.jeanbarrossilva.shuffle.std.selectable.selectAll

internal fun <T : Any> Categorization.asTuningCategorization(
    onFilterChangeListener: ContentfulCategorization.OnFilterChangeListener<T>
): ContentfulCategorization<T> {
    @Suppress("UNCHECKED_CAST")
    return when (this) {
        is Categorization.ByAlbums -> ContentfulCategorization.ByAlbums(
            albums.select(),
            onFilterChangeListener as ContentfulCategorization.OnFilterChangeListener<List<Selectable<Album>>> // ktlint-disable max-line-length
        )
        is Categorization.ByArtists -> ContentfulCategorization.ByArtists(
            artists.selectAll(),
            onFilterChangeListener as ContentfulCategorization.OnFilterChangeListener<List<Selectable<Artist>>> // ktlint-disable max-line-length
        )
        is Categorization.ByStreamingCountGoal -> ContentfulCategorization.ByStreamingCountGoal(
            streamingCountGoal,
            onFilterChangeListener as ContentfulCategorization.OnFilterChangeListener<Int>
        )
    } as ContentfulCategorization<T>
}
