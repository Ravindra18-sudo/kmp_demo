package com.example.kmpcalculator.domain.repository

import com.example.kmpcalculator.domain.model.CalculationResult

interface CalculatorRepository {
    fun evaluate(expression: String): CalculationResult
}
