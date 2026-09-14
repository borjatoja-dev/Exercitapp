package com.transformacion.fitness.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * Esquema de cores escuro (tema por defecto da app).
 */
private val DarkColorScheme = darkColorScheme(
    primary = YellowPrimary,
    secondary = BlueAccent,
    tertiary = YellowPrimary,
    background = DarkBlue,
    surface = SlateDark,
    onPrimary = DarkBlue,
    onSecondary = TextLight,
    onTertiary = DarkBlue,
    onBackground = TextLight,
    onSurface = TextLight
)

/**
 * Esquema de cores claro (opcional, para futuro).
 */
private val LightColorScheme = lightColorScheme(
    primary = YellowDark,
    secondary = BlueAccent,
    tertiary = YellowDark,
    background = Color(0xFFF1F5F9),
    surface = Color(0xFFFFFFFF),
    onPrimary = TextLight,
    onSecondary = TextLight,
    onTertiary = TextLight,
    onBackground = DarkBlue,
    onSurface = DarkBlue
)

/**
 * Tema principal da aplicación.
 * Por defecto usa tema escuro inspirado no prototipo HTML.
 */
@Composable
fun FitnessAppTheme(
    darkTheme: Boolean = true, // Sempre escuro por defecto
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = DarkBlue.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
