package com.jeanbarrossilva.shuffle.std.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

fun <T> Flow<T>.mutableStateIn(scope: CoroutineScope, initialValue: T):
    MutableStateFlow<T> {
    return MutableStateFlow(initialValue).apply {
        scope.launch {
            this@mutableStateIn.collect(this@apply)
        }
    }
}
