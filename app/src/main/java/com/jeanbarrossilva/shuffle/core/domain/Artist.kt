package com.jeanbarrossilva.shuffle.core.domain

import java.io.Serializable

sealed interface Artist : Serializable {
    val bandName: String
    val soloName: String get() = bandName

    @Suppress("SpellCheckingInspection")
    object Hoseok : Artist {
        override val bandName = "J-Hope"
    }

    object Jimin : Artist {
        override val bandName = "Jimin"
    }

    @Suppress("SpellCheckingInspection")
    object Seokjin : Artist {
        override val bandName = "Jin"
    }

    object Jungkook : Artist {
        override val bandName = "Jungkook"
    }

    @Suppress("SpellCheckingInspection")
    object Namjoon : Artist {
        override val bandName = "RM"
    }

    object Taehyung : Artist {
        override val bandName = "Taehyung"
        override val soloName = "V"
    }

    @Suppress("SpellCheckingInspection")
    object Yoongi : Artist {
        override val bandName = "Suga"
        override val soloName = "Agust D"
    }

    companion object {
        val bts = listOf(Hoseok, Jimin, Seokjin, Jungkook, Namjoon, Yoongi, Taehyung)

        fun named(name: String): Artist {
            return bts.find { artist -> artist.isNamed(name) } ?: throw IllegalArgumentException(
                "No artist named \"$name\"."
            )
        }
    }
}

val Collection<Artist>.names
    get() = with(this) artists@{
        if (this == Artist.bts) {
            "BTS"
        } else if (size == 1) {
            single().soloName
        } else {
            buildString {
                this@artists.forEachIndexed { index, artist ->
                    when (index) {
                        lastIndex -> append("e ${artist.bandName}")
                        lastIndex - 1 -> append("${artist.bandName} ")
                        else -> append("${artist.bandName}, ")
                    }
                }
            }
        }
    }

fun Artist.isNamed(name: String): Boolean {
    return bandName == name || soloName == name
}
