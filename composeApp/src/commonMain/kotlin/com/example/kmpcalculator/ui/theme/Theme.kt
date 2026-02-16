package com.example.kmpcalculator.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val CalculatorLightColorScheme = lightColorScheme(
    primary = AppColors.LightPrimary,
    onPrimary = AppColors.LightOnPrimary,
    primaryContainer = AppColors.LightPrimaryContainer,
    onPrimaryContainer = AppColors.LightOnPrimaryContainer,
    background = AppColors.LightBackground,
    onBackground = AppColors.LightOnBackground,
    surface = AppColors.LightSurface,
    onSurface = AppColors.LightOnSurface,
    error = AppColors.LightError,
    onError = AppColors.LightOnError,
)

private val CalculatorDarkColorScheme = darkColorScheme(
    primary = AppColors.DarkPrimary,
    onPrimary = AppColors.DarkOnPrimary,
    primaryContainer = AppColors.DarkPrimaryContainer,
    onPrimaryContainer = AppColors.DarkOnPrimaryContainer,
    background = AppColors.DarkBackground,
    onBackground = AppColors.DarkOnBackground,
    surface = AppColors.DarkSurface,
    onSurface = AppColors.DarkOnSurface,
    error = AppColors.DarkError,
    onError = AppColors.DarkOnError,
)

private val CardVerificationColorScheme = lightColorScheme(
    primary = AppColors.CardPrimary,
    onPrimary = AppColors.CardOnPrimary,
    secondary = AppColors.CardSecondary,
    onSecondary = AppColors.CardOnSecondary,
    tertiary = AppColors.CardTertiary,
    background = AppColors.CardBackground,
    onBackground = AppColors.CardOnBackground,
    surface = AppColors.CardSurface,
    onSurface = AppColors.CardOnSurface,
    error = AppColors.LightError,
    onError = AppColors.LightOnError,
)

@Immutable
data class AppButtonColors(
    val digitButton: Color,
    val operatorButton: Color,
    val actionButton: Color,
    val equalButton: Color,
    val onEqualButton: Color,
)

private val LightButtonColors = AppButtonColors(
    digitButton = AppColors.LightDigitButton,
    operatorButton = AppColors.LightOperatorButton,
    actionButton = AppColors.LightActionButton,
    equalButton = AppColors.LightEqualButton,
    onEqualButton = AppColors.LightOnEqualButton,
)

private val DarkButtonColors = AppButtonColors(
    digitButton = AppColors.DarkDigitButton,
    operatorButton = AppColors.DarkOperatorButton,
    actionButton = AppColors.DarkActionButton,
    equalButton = AppColors.DarkEqualButton,
    onEqualButton = AppColors.DarkOnEqualButton,
)

private val CardVerificationButtonColors = AppButtonColors(
    digitButton = AppColors.GreyInput,
    operatorButton = AppColors.BlueHint,
    actionButton = AppColors.LightActionButton,
    equalButton = AppColors.CardPrimary,
    onEqualButton = AppColors.CardOnPrimary,
)

private val LocalButtonColors = staticCompositionLocalOf { LightButtonColors }

@Composable
fun CalculatorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    useCardVerificationPalette: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        useCardVerificationPalette -> CardVerificationColorScheme
        darkTheme -> CalculatorDarkColorScheme
        else -> CalculatorLightColorScheme
    }

    val buttonColors = when {
        useCardVerificationPalette -> CardVerificationButtonColors
        darkTheme -> DarkButtonColors
        else -> LightButtonColors
    }

    CompositionLocalProvider(LocalButtonColors provides buttonColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography,
            shapes = AppShapes,
            content = content,
        )
    }
}

object AppTheme {
    val colors: ColorScheme
        @Composable get() = MaterialTheme.colorScheme

    val buttonColors: AppButtonColors
        @Composable get() = LocalButtonColors.current
}
