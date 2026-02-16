package com.example.kmpcalculator.presentation.action

sealed interface CalculatorAction {
    data class Input(val value: String) : CalculatorAction
    data object Clear : CalculatorAction
    data object Delete : CalculatorAction
    data object Calculate : CalculatorAction
}
