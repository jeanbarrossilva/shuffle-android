package com.jeanbarrossilva.shuffle.core.extensions

internal val <T> Collection<T>.duplicates: List<T>
    get() {
        val asList = toList()
        val iterator = iterator()
        val accumulator = mutableListOf<T>()
        var index = 0
        while (iterator.hasNext()) {
            val current = iterator.next()
            val exclusive = asList.subList(0, index) + asList.subList(index + 1, size)
            if (current in exclusive) {
                accumulator.add(current)
            }
            index++
        }
        return accumulator.toList()
    }
