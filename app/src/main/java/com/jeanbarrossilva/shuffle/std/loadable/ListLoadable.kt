package com.jeanbarrossilva.shuffle.std.loadable

import com.jeanbarrossilva.loadable.Loadable
import com.jeanbarrossilva.loadable.flow.loadable
import com.jeanbarrossilva.loadable.list.SerializableList
import com.jeanbarrossilva.loadable.list.emptySerializableList
import com.jeanbarrossilva.loadable.map
import java.io.Serializable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

sealed interface ListLoadable<T : Serializable?> {
    class Loading<T : Serializable?> : ListLoadable<T> {
        override fun asLoadable(): Loadable<SerializableList<T>> {
            return Loadable.Loading()
        }
    }

    class Empty<T : Serializable?> : ListLoadable<T> {
        override fun asLoadable(): Loadable<SerializableList<T>> {
            val content = emptySerializableList<T>()
            return Loadable.Loaded(content)
        }
    }

    @JvmInline
    value class Populated<T : Serializable?>(val content: SerializableList<T>) : ListLoadable<T> {
        init {
            require(content.isNotEmpty()) {
                "Cannot create a populated ListLoadable with no elements."
            }
        }

        override fun asLoadable(): Loadable<SerializableList<T>> {
            return Loadable.Loaded(content)
        }
    }

    @JvmInline
    value class Failed<T : Serializable?>(private val error: Throwable) : ListLoadable<T> {
        override fun asLoadable(): Loadable<SerializableList<T>> {
            return Loadable.Failed(error)
        }
    }

    fun asLoadable(): Loadable<SerializableList<T>>
}

fun <I : Serializable?, O> ListLoadable<I>.ifPopulated(transform: SerializableList<I>.() -> O): O? {
    return if (this is ListLoadable.Populated) content.transform() else null
}

fun <I : Serializable?, O : Serializable?> Flow<ListLoadable<I>>.innerMap(
    transform: (SerializableList<I>) -> O
): Flow<Loadable<O>> {
    return map { listLoadable ->
        listLoadable.asLoadable().map(transform)
    }
}

fun <T : Serializable?> Flow<SerializableList<T>>.listLoadable(coroutineScope: CoroutineScope):
    StateFlow<ListLoadable<T>> {
    return listLoadable().stateIn(
        coroutineScope,
        SharingStarted.WhileSubscribed(),
        initialValue = ListLoadable.Loading()
    )
}

fun <T : Serializable?> Flow<SerializableList<T>>.listLoadable(): Flow<ListLoadable<T>> {
    return loadable().map(Loadable<SerializableList<T>>::asListLoadable)
}

fun <T : Serializable?> Loadable<SerializableList<T>>.asListLoadable(): ListLoadable<T> {
    return when {
        this is Loadable.Loading -> ListLoadable.Loading()
        this is Loadable.Loaded && content.isEmpty() -> ListLoadable.Empty()
        this is Loadable.Loaded && content.isNotEmpty() -> ListLoadable.Populated(content)
        this is Loadable.Failed -> ListLoadable.Failed(error)
        else -> throw IllegalStateException()
    }
}
