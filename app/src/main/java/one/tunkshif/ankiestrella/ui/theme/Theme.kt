package one.tunkshif.ankiestrella.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

// TODO: Dark Mode Color Palette
private val DarkColorPalette = darkColors(
    primary = AnkiBlue100,
    primaryVariant = AnkiBlue200,
    secondary = AnkiBlue50,
)

private val LightColorPalette = lightColors(
    primary = AnkiBlue100,
    primaryVariant = AnkiBlue200,
    secondary = AnkiBlue50,
    background = White,
    surface = White,
    onPrimary = White,
    onSecondary = Black,
    onBackground = Black,
    onSurface = Black
)

@Composable
fun AnkiEstrellaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
//    val colors = if (darkTheme) {
//        DarkColorPalette
//    } else {
//        LightColorPalette
//    }
    val colors = LightColorPalette

    MaterialTheme(
        colors = colors,
        content = content
    )
}