package com.jeanbarrossilva.shuffle.std.selectable

import java.io.Serializable

data class Selectable<T : Serializable?>(val value: T, val isSelected: Boolean) : Serializable

val <T : Serializable?> Collection<Selectable<T>>.selection
    get() = filter(Selectable<T>::isSelected).values
val <T : Serializable?> Collection<Selectable<T>>.values
    get() = map(Selectable<T>::value)

fun <T : Serializable> Collection<T>.selectAll(): List<Selectable<T>> {
    return select { _, _ ->
        true
    }
}

fun <T : Serializable?> Collection<T>.selectFirst(): List<Selectable<T>> {
    return select { index, _ ->
        index == 0
    }
}

fun <T : Serializable?> Collection<T>.select(
    selection: (index: Int, T) -> Boolean = { _, _ -> false }
): List<Selectable<T>> {
    return mapIndexed { index, element ->
        Selectable(element, selection(index, element))
    }
}

fun <T : Serializable?> Collection<Selectable<T>>.toggling(value: T): List<Selectable<T>> {
    return map { selectable ->
        if (selectable.value == value) {
            selectable.copy(isSelected = !selectable.isSelected)
        } else {
            selectable
        }
    }
}
