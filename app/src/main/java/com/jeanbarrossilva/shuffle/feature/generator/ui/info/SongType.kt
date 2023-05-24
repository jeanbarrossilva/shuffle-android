package com.jeanbarrossilva.shuffle.feature.generator.ui.info

import androidx.compose.material.icons.rounded.Balance
import androidx.compose.material.icons.rounded.FilterCenterFocus
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.jeanbarrossilva.shuffle.platform.theme.ShuffleTheme

enum class SongType {
    FOCUS {
        override val icon @Composable get() = ShuffleTheme.Icons.FilterCenterFocus

        @Suppress("SpellCheckingInspection")
        override val contentDescription = "Música-foco"
    },
    FILLER {
        override val icon @Composable get() = ShuffleTheme.Icons.Balance
        override val contentDescription = "Filler"
    };

    @get:Composable
    abstract val icon: ImageVector

    abstract val contentDescription: String
}
