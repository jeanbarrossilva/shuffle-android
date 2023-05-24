package com.jeanbarrossilva.shuffle.feature.tuning

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jeanbarrossilva.shuffle.feature.generator.extensions.copy
import com.jeanbarrossilva.shuffle.feature.tuning.domain.ContentfulCategorization
import com.jeanbarrossilva.shuffle.platform.theme.ShuffleTheme
import com.jeanbarrossilva.shuffle.platform.theme.extensions.plus
import com.jeanbarrossilva.shuffle.platform.theme.ui.BackIcon
import com.jeanbarrossilva.shuffle.platform.theme.ui.DropdownField

@Composable
fun Tuning(
    viewModel: TuningViewModel,
    onBackwardsNavigation: () -> Unit,
    modifier: Modifier = Modifier
) {
    val categorization by viewModel.categorizationFlow.collectAsState()

    Tuning(
        categorization,
        onCategorizationChange = viewModel::setCategorization,
        onBackwardsNavigation,
        modifier
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun Tuning(
    categorization: ContentfulCategorization<*>,
    onCategorizationChange: (ContentfulCategorization<*>) -> Unit,
    onBackwardsNavigation: () -> Unit,
    modifier: Modifier = Modifier
) {
    val topAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    var areCategoriesExpanded by remember { mutableStateOf(false) }

    Scaffold(
        modifier,
        topBar = {
            LargeTopAppBar(
                title = {
                    @Suppress("SpellCheckingInspection")
                    Text(
                        "Configurar",
                        Modifier.padding(
                            horizontal = -ShuffleTheme.spacings.extraSmall +
                                ShuffleTheme.spacings.large
                        )
                    )
                },
                navigationIcon = { BackIcon(onClick = onBackwardsNavigation) },
                scrollBehavior = topAppBarScrollBehavior
            )
        }
    ) { padding ->
        LazyColumn(
            Modifier.nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
            contentPadding = padding +
                PaddingValues(ShuffleTheme.spacings.large).copy(bottom = 0.dp),
            verticalArrangement = Arrangement.spacedBy(ShuffleTheme.spacings.medium)
        ) {
            item {
                DropdownField(
                    areCategoriesExpanded,
                    onExpansionToggle = { areCategoriesExpanded = it },
                    value = categorization.name,
                    label = {
                        @Suppress("SpellCheckingInspection")
                        Text("Categorização")
                    },
                    Modifier.fillMaxWidth()
                ) { width ->
                    ContentfulCategorization.values.forEach {
                        DropdownMenuItem(
                            text = { Text(it.name) },
                            onClick = {
                                onCategorizationChange(it)
                                areCategoriesExpanded = false
                            },
                            Modifier.width(width),
                            trailingIcon = if (categorization::class == it) {
                                {
                                    @Suppress("SpellCheckingInspection")
                                    Icon(
                                        ShuffleTheme.Icons.Done,
                                        contentDescription = "Selecionado"
                                    )
                                }
                            } else {
                                null
                            }
                        )
                    }
                }
            }

            item {
                categorization.Content()
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun ByAlbumTuningPreview() {
    ShuffleTheme {
        Tuning<ContentfulCategorization.ByAlbums>()
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun ByArtistsTuningPreview() {
    ShuffleTheme {
        Tuning<ContentfulCategorization.ByArtists>()
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun ByStreamingCountGoalTuningPreview() {
    ShuffleTheme {
        Tuning<ContentfulCategorization.ByStreamingCountGoal>()
    }
}

@Composable
private inline fun <reified T : ContentfulCategorization<*>> Tuning(modifier: Modifier = Modifier) {
    Tuning(
        ContentfulCategorization.values.first { it is T },
        onCategorizationChange = { },
        onBackwardsNavigation = { },
        modifier
    )
}
