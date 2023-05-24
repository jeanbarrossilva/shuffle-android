package com.jeanbarrossilva.shuffle.core.samples.album

import android.content.Context
import com.jeanbarrossilva.shuffle.core.domain.Track
import com.jeanbarrossilva.shuffle.core.domain.album.Album
import com.jeanbarrossilva.shuffle.core.domain.album.AlbumProvider
import com.jeanbarrossilva.shuffle.core.domain.album.get
import java.lang.ref.WeakReference

class SampleAlbumProvider private constructor(private val contextRef: WeakReference<Context>) :
    AlbumProvider {
    constructor(context: Context) : this(WeakReference(context))

    override suspend fun provide(track: Track): Album {
        val context = contextRef.get() ?: throw NullPointerException("context")
        return Album.getSamples(context)[track]
    }
}
