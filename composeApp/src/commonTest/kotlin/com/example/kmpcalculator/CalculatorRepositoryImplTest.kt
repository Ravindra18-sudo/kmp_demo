package com.example.kmpcalculator

import com.example.kmpcalculator.data.repository.CalculatorRepositoryImpl
import com.example.kmpcalculator.domain.model.CalculationResult
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CalculatorRepositoryImplTest {
    private val repository = CalculatorRepositoryImpl()

    @Test
    fun evaluatesExpressionWithPrecedence() {
        val result = repository.evaluate("10+2*3")

        assertEquals(CalculationResult.Success("16"), result)
    }

    @Test
    fun evaluatesExpressionWithParentheses() {
        val result = repository.evaluate("(10+2)*3")

        assertEquals(CalculationResult.Success("36"), result)
    }

    @Test
    fun returnsErrorForDivisionByZero() {
        val result = repository.evaluate("10/0")

        assertTrue(result is CalculationResult.Error)
        assertEquals("Division by zero", result.message)
    }
}
