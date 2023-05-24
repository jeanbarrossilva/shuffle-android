package com.jeanbarrossilva.shuffle.feature.about

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.UrlAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.jeanbarrossilva.shuffle.platform.theme.ShuffleTheme
import com.jeanbarrossilva.shuffle.platform.theme.extensions.plus

@OptIn(ExperimentalTextApi::class)
@Composable
fun About(modifier: Modifier = Modifier) {
    Scaffold(modifier) { padding ->
        LazyColumn(
            Modifier.fillMaxHeight(),
            contentPadding = padding + PaddingValues(ShuffleTheme.spacings.large),
            verticalArrangement = Arrangement.spacedBy(
                ShuffleTheme.spacings.extraSmall,
                Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                @Suppress("SpellCheckingInspection")
                Text(
                    "Idealizado por Carolina Silva",
                    Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = ShuffleTheme.typography.labelLarge
                )
            }

            item {
                @Suppress("SpellCheckingInspection")
                Text(
                    buildAnnotatedString {
                        append("Desenvolvido por ")
                        withStyle(
                            SpanStyle(
                                color = ShuffleTheme.colorScheme.primary
                            )
                        ) {
                            pushUrlAnnotation(
                                UrlAnnotation("https://instagram.com/jeanbarrossilva")
                            )
                            append("@jeanbarrossilva")
                            pop()
                        }
                    },
                    Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = ShuffleTheme.typography.labelLarge
                )
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AboutPreview() {
    ShuffleTheme {
        About()
    }
}
