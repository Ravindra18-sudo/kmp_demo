package com.example.kmpcalculator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.kmpcalculator.presentation.screen.WhiteCircleLoginScreen
import com.example.kmpcalculator.ui.theme.AppTheme
import com.example.kmpcalculator.ui.theme.CalculatorTheme

@Composable
fun App() {
    CalculatorTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AppTheme.colors.background,
        ) {
            WhiteCircleLoginScreen()
        }
    }
}
