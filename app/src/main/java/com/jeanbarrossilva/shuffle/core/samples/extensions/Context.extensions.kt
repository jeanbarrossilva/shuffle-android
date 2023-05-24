package com.jeanbarrossilva.shuffle.core.samples.extensions

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmapOrNull

internal fun Context.getBitmap(@DrawableRes id: Int): Bitmap {
    return ContextCompat.getDrawable(this, id)?.toBitmapOrNull()
        ?: throw Resources.NotFoundException("$id")
}
