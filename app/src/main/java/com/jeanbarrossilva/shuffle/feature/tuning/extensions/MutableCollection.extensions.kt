package com.jeanbarrossilva.shuffle.feature.tuning.extensions

internal fun <T> MutableCollection<T>.toggle(element: T) {
    if (element in this) remove(element) else add(element)
}
