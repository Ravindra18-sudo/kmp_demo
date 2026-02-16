package com.example.kmpcalculator.presentation.state

data class CalculatorUiState(
    val expression: String = "",
    val result: String = "0",
    val isError: Boolean = false,
)
