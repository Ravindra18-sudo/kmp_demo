package com.example.kmpcalculator.ui.theme

import androidx.compose.ui.graphics.Color

object AppColors {
    // Shared tokens
    val Accent = Color(0xFFFF5A00)
    val Teal200 = Color(0xFF03DAC5)
    val Teal700 = Color(0xFF00838A)
    val White = Color(0xFFFFFFFF)
    val Black = Color(0xFF000000)
    val Blue = Color(0xFF0061A4)
    val BlueHint = Color(0xFFB5BCC4)
    val GreyInput = Color(0xFFE5E5E5)

    // Material light theme tokens
    val LightPrimary = Blue
    val LightOnPrimary = White
    val LightPrimaryContainer = Color(0xFFD1E4FF)
    val LightOnPrimaryContainer = Color(0xFF001D36)
    val LightBackground = Color(0xFFF8F9FF)
    val LightOnBackground = Color(0xFF191C20)
    val LightSurface = Color(0xFFF8F9FF)
    val LightOnSurface = Color(0xFF191C20)
    val LightError = Color(0xFFBA1A1A)
    val LightOnError = White

    // Material dark theme tokens
    val DarkPrimary = Color(0xFF9ECAFF)
    val DarkOnPrimary = Color(0xFF003258)
    val DarkPrimaryContainer = Color(0xFF00497D)
    val DarkOnPrimaryContainer = Color(0xFFD1E4FF)
    val DarkBackground = Color(0xFF111418)
    val DarkOnBackground = Color(0xFFE1E2E8)
    val DarkSurface = Color(0xFF111418)
    val DarkOnSurface = Color(0xFFE1E2E8)
    val DarkError = Color(0xFFFFB4AB)
    val DarkOnError = Color(0xFF690005)

    // Alternate card verification palette (optional flow)
    val CardPrimary = Accent
    val CardOnPrimary = White
    val CardSecondary = Accent
    val CardOnSecondary = White
    val CardTertiary = Accent
    val CardBackground = Black
    val CardOnBackground = White
    val CardSurface = Black
    val CardOnSurface = White

    // Calculator component tokens (light)
    val LightDigitButton = Color(0xFFE4ECFF)
    val LightOperatorButton = Color(0xFF99CCFF)
    val LightActionButton = Color(0xFFFFDAD7)
    val LightEqualButton = LightPrimary
    val LightOnEqualButton = White

    // Calculator component tokens (dark)
    val DarkDigitButton = Color(0xFF2A3440)
    val DarkOperatorButton = Color(0xFF3E6C96)
    val DarkActionButton = Color(0xFF6A4242)
    val DarkEqualButton = DarkPrimary
    val DarkOnEqualButton = DarkOnPrimary

    // Backward-compatible aliases
    val DigitButton = LightDigitButton
    val OperatorButton = LightOperatorButton
    val ActionButton = LightActionButton
    val EqualButton = LightEqualButton
    val OnEqualButton = LightOnEqualButton
}
