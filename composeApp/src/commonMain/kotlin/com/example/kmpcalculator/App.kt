package com.example.kmpcalculator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.kmpcalculator.presentation.screen.AuthLandingScreen
import com.example.kmpcalculator.ui.theme.CalculatorTheme

@Composable
fun App() {
    CalculatorTheme {
        val platform = remember { getPlatform() }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Black,
        ) {
            AuthLandingScreen(
                showAppleLogin = platform.isApp,
            )
        }
    }
}
