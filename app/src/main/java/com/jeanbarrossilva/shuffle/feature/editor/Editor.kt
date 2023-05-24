package com.jeanbarrossilva.shuffle.feature.editor

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.shuffle.feature.generator.extensions.copy
import com.jeanbarrossilva.shuffle.platform.theme.ShuffleTheme
import com.jeanbarrossilva.shuffle.platform.theme.extensions.plus
import com.jeanbarrossilva.shuffle.platform.theme.ui.BackIcon

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun Editor(
    title: String,
    onTitleChange: (title: String) -> Unit,
    onBackwardsNavigation: () -> Unit,
    onDone: () -> Unit,
    modifier: Modifier = Modifier
) {
    val topAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val titleFocusRequester = remember(::FocusRequester)

    LaunchedEffect(Unit) {
        titleFocusRequester.requestFocus()
    }

    Scaffold(
        modifier,
        topBar = {
            LargeTopAppBar(
                title = {
                    @Suppress("SpellCheckingInspection")
                    Text(
                        "Editar playlist",
                        Modifier.padding(
                            horizontal = -ShuffleTheme.spacings.extraSmall +
                                ShuffleTheme.spacings.large
                        )
                    )
                },
                navigationIcon = { BackIcon(onClick = onBackwardsNavigation) },
                scrollBehavior = topAppBarScrollBehavior
            )
        },
        floatingActionButton = {
            AnimatedVisibility(
                visible = title.isNotBlank(),
                enter = fadeIn() + slideInVertically { it },
                exit = fadeOut() + slideOutVertically { it }
            ) {
                FloatingActionButton(onClick = { }, Modifier.imePadding()) {
                    @Suppress("SpellCheckingInspection")
                    Icon(ShuffleTheme.Icons.Done, contentDescription = "Finalizar")
                }
            }
        }
    ) { padding ->
        LazyColumn(
            Modifier.nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
            contentPadding = padding +
                PaddingValues(ShuffleTheme.spacings.large).copy(bottom = 0.dp) +
                ShuffleTheme.overlays.fab
        ) {
            item {
                TextField(
                    title,
                    onTitleChange,
                    Modifier
                        .focusRequester(titleFocusRequester)
                        .fillMaxWidth(),
                    label = {
                        @Suppress("SpellCheckingInspection")
                        Text("Título")
                    },
                    singleLine = true
                )
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun EditorPreview() {
    ShuffleTheme {
        Editor(title = "", onTitleChange = { }, onBackwardsNavigation = { }, onDone = { })
    }
}
