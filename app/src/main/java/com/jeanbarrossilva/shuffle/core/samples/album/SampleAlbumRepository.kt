package com.jeanbarrossilva.shuffle.core.samples.album

import android.content.Context
import com.jeanbarrossilva.shuffle.core.domain.album.Album
import com.jeanbarrossilva.shuffle.core.infra.AlbumRepository
import java.lang.ref.WeakReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf

class SampleAlbumRepository private constructor(private val contextRef: WeakReference<Context>) :
    AlbumRepository {
    constructor(context: Context) : this(WeakReference(context))

    override fun fetch(query: String): Flow<List<Album>> {
        val context = contextRef.get() ?: return emptyFlow()
        val samples = Album.getSamples(context)
        val queried = if (query.isEmpty()) {
            samples
        } else {
            samples.filter { album ->
                album.title.contains(query, ignoreCase = true)
            }
        }
        return flowOf(queried)
    }
}
