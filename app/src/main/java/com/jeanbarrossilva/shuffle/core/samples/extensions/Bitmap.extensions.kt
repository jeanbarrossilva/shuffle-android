package com.jeanbarrossilva.shuffle.core.samples.extensions

import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
internal fun bitmapResource(@DrawableRes id: Int): Bitmap? {
    return LocalContext.current.getBitmap(id)
}
