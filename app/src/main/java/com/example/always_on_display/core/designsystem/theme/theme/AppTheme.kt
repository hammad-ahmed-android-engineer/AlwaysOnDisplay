package com.example.always_on_display.core.designsystem.theme.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.always_on_display.core.designsystem.theme.color.Black
import com.example.always_on_display.core.designsystem.theme.color.BorderColor
import com.example.always_on_display.core.designsystem.theme.color.DarkBackground
import com.example.always_on_display.core.designsystem.theme.color.DarkSurface
import com.example.always_on_display.core.designsystem.theme.color.DarkSurfaceVariant
import com.example.always_on_display.core.designsystem.theme.color.PrimaryGold
import com.example.always_on_display.core.designsystem.theme.color.SecondaryGold
import com.example.always_on_display.core.designsystem.theme.color.White
import com.example.always_on_display.core.designsystem.theme.dimensions.LocalDimensions
import com.example.always_on_display.core.designsystem.theme.dimensions.resolveDimensions
import com.example.always_on_display.core.designsystem.theme.typography.AppTypography

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryGold,
    onPrimary = Black,
    secondary = SecondaryGold,
    onSecondary = Black,
    background = DarkBackground,
    onBackground = White,
    surface = DarkSurface,
    onSurface = White,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = White,
    outline = BorderColor,
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val dimensions = resolveDimensions()
    val typography = AppTypography.material3Typography(dimensions.typeScale)

    CompositionLocalProvider(
        LocalDimensions provides dimensions
    ) {
        MaterialTheme(
            colorScheme = DarkColorScheme,
            typography = typography,
            content = content
        )
    }
}
