package com.jeanbarrossilva.shuffle.platform.theme.extensions

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import com.jeanbarrossilva.shuffle.core.samples.album.SampleAlbumProvider

internal val LocalShapes
    @Composable get() = material3CompositionLocalOf(MaterialTheme.shapes)

val LocalSampleAlbumProvider
    @Composable get() = compositionLocalOf<SampleAlbumProvider> {
        error("CompositionLocal LocalSampleAlbumProvider not present.")
    }

private inline fun <reified T : Any> material3CompositionLocalOf(fallback: T):
    ProvidableCompositionLocal<T> {
    val typeClass = T::class
    val typeName =
        typeClass.simpleName ?: throw IllegalStateException("Could not get $typeClass's name.")
    val siteClassName = "androidx.compose.material3.${typeName}Kt"
    val fieldName = "Local$typeName"

    @Suppress("UNCHECKED_CAST")
    return Class
        .forName(siteClassName)
        ?.getDeclaredField(fieldName)
        ?.apply { isAccessible = true }
        ?.get(null)
        ?.let { it as ProvidableCompositionLocal<T> }
        ?: staticCompositionLocalOf { fallback }
}
