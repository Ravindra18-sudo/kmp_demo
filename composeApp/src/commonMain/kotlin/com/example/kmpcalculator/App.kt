package com.example.kmpcalculator

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.kmpcalculator.di.AppModule
import com.example.kmpcalculator.presentation.screen.CalculatorScreen
import com.example.kmpcalculator.ui.theme.AppTheme
import com.example.kmpcalculator.ui.theme.CalculatorTheme

@Composable
fun App() {
    CalculatorTheme {
        val viewModel = remember { AppModule.provideCalculatorViewModel() }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AppTheme.colors.background,
        ) {
            CalculatorScreen(
                uiState = viewModel.uiState,
                onAction = viewModel::onAction,
            )
        }
    }
}
