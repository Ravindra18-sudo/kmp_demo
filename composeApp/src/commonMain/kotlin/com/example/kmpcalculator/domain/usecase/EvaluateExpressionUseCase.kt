package com.example.kmpcalculator.domain.usecase

import com.example.kmpcalculator.domain.model.CalculationResult
import com.example.kmpcalculator.domain.repository.CalculatorRepository

class EvaluateExpressionUseCase(
    private val repository: CalculatorRepository,
) {
    operator fun invoke(expression: String): CalculationResult = repository.evaluate(expression)
}
