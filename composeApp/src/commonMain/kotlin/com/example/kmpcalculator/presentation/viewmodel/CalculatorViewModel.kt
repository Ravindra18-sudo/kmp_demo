package com.example.kmpcalculator.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.kmpcalculator.domain.model.CalculationResult
import com.example.kmpcalculator.domain.usecase.AppendInputUseCase
import com.example.kmpcalculator.domain.usecase.ClearInputUseCase
import com.example.kmpcalculator.domain.usecase.DeleteInputUseCase
import com.example.kmpcalculator.domain.usecase.EvaluateExpressionUseCase
import com.example.kmpcalculator.presentation.action.CalculatorAction
import com.example.kmpcalculator.presentation.state.CalculatorUiState

class CalculatorViewModel(
    private val appendInputUseCase: AppendInputUseCase,
    private val clearInputUseCase: ClearInputUseCase,
    private val deleteInputUseCase: DeleteInputUseCase,
    private val evaluateExpressionUseCase: EvaluateExpressionUseCase,
) {
    var uiState by mutableStateOf(CalculatorUiState())
        private set

    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Input -> onInput(action.value)
            CalculatorAction.Clear -> onClear()
            CalculatorAction.Delete -> onDelete()
            CalculatorAction.Calculate -> onCalculate()
        }
    }

    private fun onInput(value: String) {
        if (value.isBlank()) return

        val currentExpression = uiState.expression
        if (shouldIgnoreInput(currentExpression, value)) return

        uiState = uiState.copy(
            expression = appendInputUseCase(currentExpression, value),
            isError = false,
        )
    }

    private fun onClear() {
        uiState = CalculatorUiState(
            expression = clearInputUseCase(),
            result = "0",
            isError = false,
        )
    }

    private fun onDelete() {
        val updatedExpression = deleteInputUseCase(uiState.expression)
        uiState = uiState.copy(
            expression = updatedExpression,
            result = if (updatedExpression.isBlank()) "0" else uiState.result,
            isError = false,
        )
    }

    private fun onCalculate() {
        if (uiState.expression.isBlank()) return

        when (val response = evaluateExpressionUseCase(uiState.expression)) {
            is CalculationResult.Success -> {
                uiState = uiState.copy(
                    expression = response.value,
                    result = response.value,
                    isError = false,
                )
            }

            is CalculationResult.Error -> {
                uiState = uiState.copy(
                    result = response.message,
                    isError = true,
                )
            }
        }
    }

    private fun shouldIgnoreInput(current: String, next: String): Boolean {
        val isOperator = next.singleOrNull() in OPERATORS
        val isDecimal = next == "."

        if (isOperator) {
            if (current.isBlank() && next != "-") return true
            if (current.lastOrNull() in OPERATORS) return true
        }

        if (isDecimal && currentNumberContainsDecimal(current)) {
            return true
        }

        return false
    }

    private fun currentNumberContainsDecimal(expression: String): Boolean {
        if (expression.isBlank()) return false

        val currentNumber = buildString {
            var index = expression.length - 1
            while (index >= 0 && expression[index] !in OPERATORS) {
                append(expression[index])
                index--
            }
        }.reversed()

        return '.' in currentNumber
    }

    private companion object {
        val OPERATORS = setOf('+', '-', '*', '/')
    }
}
