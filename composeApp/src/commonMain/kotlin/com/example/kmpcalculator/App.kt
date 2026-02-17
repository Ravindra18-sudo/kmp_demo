package com.example.kmpcalculator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.example.kmpcalculator.presentation.screen.CreateAccountNameScreen
import com.example.kmpcalculator.ui.theme.CalculatorTheme

@Composable
fun App(
    illustration: Painter? = null,
) {
    CalculatorTheme(darkTheme = true) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Black,
        ) {
            CreateAccountNameScreen(illustration = illustration)
        }
    }
}
