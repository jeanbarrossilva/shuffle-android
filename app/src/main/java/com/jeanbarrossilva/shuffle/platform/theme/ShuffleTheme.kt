package com.jeanbarrossilva.shuffle.platform.theme

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jeanbarrossilva.shuffle.core.samples.album.SampleAlbumProvider
import com.jeanbarrossilva.shuffle.platform.theme.extensions.LocalSampleAlbumProvider
import com.jeanbarrossilva.shuffle.platform.theme.extensions.Rubik
import com.jeanbarrossilva.shuffle.platform.theme.extensions.bottom
import com.jeanbarrossilva.shuffle.platform.theme.extensions.colorAttribute
import com.jeanbarrossilva.shuffle.platform.theme.extensions.end
import com.jeanbarrossilva.shuffle.platform.theme.extensions.start
import com.jeanbarrossilva.shuffle.platform.theme.extensions.top
import com.jeanbarrossilva.shuffle.platform.theme.extensions.with
import com.jeanbarrossilva.shuffle.platform.theme.overlay.LocalOverlays
import com.jeanbarrossilva.shuffle.platform.theme.overlay.Overlays
import com.jeanbarrossilva.shuffle.platform.theme.spacing.LocalSpacings
import com.jeanbarrossilva.shuffle.platform.theme.spacing.Spacings

private const val COLOR_SCHEME_PREVIEW_HEIGHT = 1_884
private const val SHAPES_PREVIEW_HEIGHT = 898
private const val TYPOGRAPHY_PREVIEW_HEIGHT = 1_130

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFD0BCFF),
    onPrimary = Color(0xFF381E72),
    primaryContainer = Color(0xFF4F378B),
    onPrimaryContainer = Color(0xFFEADDFF),
    secondary = Color(0xFFCCC2DC),
    onSecondary = Color(0xFF332D41),
    secondaryContainer = Color(0xFF4A4458),
    onSecondaryContainer = Color(0xFFE8DEF8),
    tertiary = Color(0xFFEFB8C8),
    onTertiary = Color(0xFF492532),
    tertiaryContainer = Color(0xFF633B48),
    onTertiaryContainer = Color(0xFFFFD8E4),
    error = Color(0xFFF2B8B5),
    onError = Color(0xFF601410),
    errorContainer = Color(0xFF8C1D18),
    onErrorContainer = Color(0xFFF9DEDC),
    outline = Color(0xFF938F99),
    background = Color(0xFF1C1B1F),
    onBackground = Color(0xFFE6E1E5),
    surface = Color(0xFF1C1B1F),
    onSurface = Color(0xFFE6E1E5),
    surfaceVariant = Color(0xFF49454F),
    onSurfaceVariant = Color(0xFFCAC4D0),
    inverseSurface = Color(0xFFE6E1E5),
    inverseOnSurface = Color(0xFF313033),
    inversePrimary = Color(0xFF6750A4),
    surfaceTint = Color(0xFFD0BCFF),
    outlineVariant = Color(0xFF49454F),
    scrim = Color(0xFF000000)
)
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6750A4),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFEADDFF),
    onPrimaryContainer = Color(0xFF21005D),
    secondary = Color(0xFF625B71),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFE8DEF8),
    onSecondaryContainer = Color(0xFF1D192B),
    tertiary = Color(0xFF7D5260),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFFFD8E4),
    onTertiaryContainer = Color(0xFF31111D),
    error = Color(0xFFB3261E),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFF9DEDC),
    onErrorContainer = Color(0xFF410E0B),
    outline = Color(0xFF79747E),
    background = Color(0xFFFFFBFE),
    onBackground = Color(0xFF1C1B1F),
    surface = Color(0xFFFFFBFE),
    onSurface = Color(0xFF1C1B1F),
    surfaceVariant = Color(0xFFE7E0EC),
    onSurfaceVariant = Color(0xFF49454F),
    inverseSurface = Color(0xFF313033),
    inverseOnSurface = Color(0xFFF4EFF4),
    inversePrimary = Color(0xFFD0BCFF),
    surfaceTint = Color(0xFF6750A4),
    outlineVariant = Color(0xFFCAC4D0),
    scrim = Color(0xFF000000)
)

private val fadedContentColor @Composable get() =
    colorAttribute(android.R.attr.colorControlNormal).copy(alpha = .5f)

internal object ShuffleTheme {
    val Icons = androidx.compose.material.icons.Icons.Rounded

    val colorScheme @Composable get() = MaterialTheme.colorScheme
    val overlays @Composable get() = LocalOverlays.current
    val shapes @Composable get() = MaterialTheme.shapes
    val spacings @Composable get() = LocalSpacings.current
    val typography @Composable get() = MaterialTheme.typography
}

@Composable
internal fun ShuffleTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme,
        Shapes(extraLarge = RoundedCornerShape(24.dp)),
        typography = with(Typography() with FontFamily.Rubik) {
            copy(
                displayLarge = displayLarge.copy(
                    color = fadedContentColor,
                    fontWeight = FontWeight.Black
                ),
                headlineMedium = headlineMedium.copy(
                    fontSize = 38.sp,
                    fontWeight = FontWeight.ExtraBold
                ),
                titleLarge = titleLarge.copy(fontWeight = FontWeight.ExtraBold),
                titleMedium = titleSmall.copy(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                titleSmall = titleSmall.copy(color = fadedContentColor, fontSize = 18.sp),
                bodyLarge = bodyLarge.copy(fontWeight = FontWeight.Bold, lineHeight = 20.sp),
                labelLarge = labelMedium.copy(
                    color = fadedContentColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    ) {
        CompositionLocalProvider(
            LocalOverlays provides Overlays.Default,
            LocalSampleAlbumProvider provides SampleAlbumProvider(LocalContext.current),
            LocalSpacings provides Spacings.default,
            LocalTextStyle provides ShuffleTheme.typography.bodyMedium,
            content = content
        )
    }
}

@Composable
@Preview(heightDp = COLOR_SCHEME_PREVIEW_HEIGHT)
@Preview(heightDp = COLOR_SCHEME_PREVIEW_HEIGHT, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun ColorSchemePreview() {
    ShuffleTheme {
        Column(Modifier.fillMaxWidth()) {
            ColorSchemeSection("Primary") {
                Color(ShuffleTheme.colorScheme.primary)
                Color(ShuffleTheme.colorScheme.inversePrimary)
                Color(ShuffleTheme.colorScheme.onPrimary)
            }

            ColorSchemeSection("Primary container") {
                Color(ShuffleTheme.colorScheme.primaryContainer)
                Color(ShuffleTheme.colorScheme.onPrimaryContainer)
            }

            ColorSchemeSection("Secondary") {
                Color(ShuffleTheme.colorScheme.secondary)
                Color(ShuffleTheme.colorScheme.onSecondary)
            }

            ColorSchemeSection("Secondary container") {
                Color(ShuffleTheme.colorScheme.secondaryContainer)
                Color(ShuffleTheme.colorScheme.onSecondaryContainer)
            }

            ColorSchemeSection("Tertiary") {
                Color(ShuffleTheme.colorScheme.tertiary)
                Color(ShuffleTheme.colorScheme.onTertiary)
            }

            ColorSchemeSection("Tertiary container") {
                Color(ShuffleTheme.colorScheme.tertiaryContainer)
                Color(ShuffleTheme.colorScheme.onTertiaryContainer)
            }

            ColorSchemeSection("Background") {
                Color(ShuffleTheme.colorScheme.background)
                Color(ShuffleTheme.colorScheme.onBackground)
            }

            ColorSchemeSection("Surface") {
                Color(ShuffleTheme.colorScheme.surface)
                Color(ShuffleTheme.colorScheme.inverseSurface)
                Color(ShuffleTheme.colorScheme.onSurface)
                Color(ShuffleTheme.colorScheme.inverseOnSurface)
                Color(ShuffleTheme.colorScheme.surfaceTint)
            }

            ColorSchemeSection("Surface variant") {
                Color(ShuffleTheme.colorScheme.surfaceVariant)
                Color(ShuffleTheme.colorScheme.onSurfaceVariant)
            }

            ColorSchemeSection("Error") {
                Color(ShuffleTheme.colorScheme.error)
                Color(ShuffleTheme.colorScheme.onError)
            }

            ColorSchemeSection("Error container") {
                Color(ShuffleTheme.colorScheme.errorContainer)
                Color(ShuffleTheme.colorScheme.onErrorContainer)
            }

            ColorSchemeSection("Outline") {
                Color(ShuffleTheme.colorScheme.outline)
            }

            ColorSchemeSection("Outline variant") {
                Color(ShuffleTheme.colorScheme.outlineVariant)
            }

            ColorSchemeSection("Scrim") {
                Color(ShuffleTheme.colorScheme.scrim)
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun OverlaysPreview() {
    ShuffleTheme {
        Surface(color = ShuffleTheme.colorScheme.background) {
            OverlaySection("FAB", ShuffleTheme.overlays.fab)
        }
    }
}

@Composable
@Preview(heightDp = SHAPES_PREVIEW_HEIGHT)
@Preview(heightDp = SHAPES_PREVIEW_HEIGHT, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun ShapesPreview() {
    ShuffleTheme {
        Surface(Modifier.fillMaxWidth(), color = ShuffleTheme.colorScheme.background) {
            Column {
                ShapeSection("Extra large", ShuffleTheme.shapes.extraLarge)
                ShapeSection("Large", ShuffleTheme.shapes.large)
                ShapeSection("Medium", ShuffleTheme.shapes.medium)
                ShapeSection("Small", ShuffleTheme.shapes.small)
                ShapeSection("Extra small", ShuffleTheme.shapes.extraSmall)
            }
        }
    }
}

@Composable
@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun SpacingsPreview() {
    ShuffleTheme {
        Surface(Modifier.fillMaxWidth()) {
            Column {
                SpacingSection("Extra large", ShuffleTheme.spacings.extraLarge)
                SpacingSection("Large", ShuffleTheme.spacings.large)
                SpacingSection("Medium", ShuffleTheme.spacings.medium)
                SpacingSection("Small", ShuffleTheme.spacings.small)
                SpacingSection("Extra small", ShuffleTheme.spacings.extraSmall)
            }
        }
    }
}

@Composable
@Preview(heightDp = TYPOGRAPHY_PREVIEW_HEIGHT)
@Preview(heightDp = TYPOGRAPHY_PREVIEW_HEIGHT, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun TypographyPreview() {
    ShuffleTheme {
        Surface(Modifier.fillMaxWidth(), color = ShuffleTheme.colorScheme.background) {
            Column {
                TypographySection("Display") {
                    Text("D1", style = ShuffleTheme.typography.displayLarge)
                    Text("D2", style = ShuffleTheme.typography.displayMedium)
                    Text("D3", style = ShuffleTheme.typography.displaySmall)
                }

                TypographySection("Headline") {
                    Text("H1", style = ShuffleTheme.typography.headlineLarge)
                    Text("H2", style = ShuffleTheme.typography.headlineMedium)
                    Text("H3", style = ShuffleTheme.typography.headlineSmall)
                }

                TypographySection("Title") {
                    Text("T1", style = ShuffleTheme.typography.titleLarge)
                    Text("T2", style = ShuffleTheme.typography.titleMedium)
                    Text("T3", style = ShuffleTheme.typography.titleSmall)
                }

                TypographySection("Body") {
                    Text("B1", style = ShuffleTheme.typography.bodyLarge)
                    Text("B2", style = ShuffleTheme.typography.bodyMedium)
                    Text("B3", style = ShuffleTheme.typography.bodySmall)
                }

                TypographySection("Label") {
                    Text("L1", style = ShuffleTheme.typography.labelLarge)
                    Text("L2", style = ShuffleTheme.typography.labelMedium)
                    Text("L3", style = ShuffleTheme.typography.labelSmall)
                }
            }
        }
    }
}

@Composable
private fun ColorSchemeSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Section(title, modifier) {
        Row(content = content)
    }
}

@Composable
private fun Color(color: Color) {
    Box(
        Modifier
            .background(color)
            .size(64.dp)
    )
}

@Composable
private fun OverlaySection(title: String, overlay: PaddingValues, modifier: Modifier = Modifier) {
    Section(title, modifier) {
        Text(
            "[${overlay.start}, ${overlay.top}, ${overlay.end}, ${overlay.bottom}]",
            Modifier.padding(ShuffleTheme.spacings.large),
            style = ShuffleTheme.typography.titleMedium
        )
    }
}

@Composable
private fun ShapeSection(title: String, shape: Shape, modifier: Modifier = Modifier) {
    Section(title, modifier) {
        Box(
            Modifier
                .padding(it)
                .clip(shape)
                .background(ShuffleTheme.colorScheme.primaryContainer)
                .height(64.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
private fun SpacingSection(title: String, spacing: Dp, modifier: Modifier = Modifier) {
    Section(title, modifier) { padding ->
        Text(
            "$spacing",
            Modifier
                .padding(start = padding.start, top = spacing, end = padding.end, bottom = spacing),
            style = ShuffleTheme.typography.titleMedium
        )
    }
}

@Composable
private fun TypographySection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Section(title, modifier) {
        Column(
            modifier.padding(it),
            Arrangement.spacedBy(ShuffleTheme.spacings.small),
            content = content
        )
    }
}

@Composable
private fun Section(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.(padding: PaddingValues) -> Unit
) {
    val padding = PaddingValues(ShuffleTheme.spacings.large)

    Column(modifier) {
        Text(
            title,
            Modifier
                .background(ShuffleTheme.colorScheme.surfaceVariant)
                .padding(padding)
                .fillMaxWidth(),
            ShuffleTheme.colorScheme.onSurfaceVariant,
            style = ShuffleTheme.typography.titleMedium
        )

        content(padding)
    }
}
