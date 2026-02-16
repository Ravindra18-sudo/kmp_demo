package com.example.kmpcalculator.data.repository

import com.example.kmpcalculator.domain.model.CalculationResult
import com.example.kmpcalculator.domain.repository.CalculatorRepository
import kotlin.math.abs

class CalculatorRepositoryImpl : CalculatorRepository {

    override fun evaluate(expression: String): CalculationResult {
        val normalizedExpression = expression.trim()
            .replace('×', '*')
            .replace('÷', '/')

        if (normalizedExpression.isBlank()) {
            return CalculationResult.Success("0")
        }

        return runCatching {
            val parsedValue = ExpressionParser(normalizedExpression).parse()
            if (parsedValue.isNaN() || parsedValue.isInfinite()) {
                throw IllegalArgumentException("Invalid operation")
            }
            formatValue(parsedValue)
        }.fold(
            onSuccess = { value -> CalculationResult.Success(value) },
            onFailure = { throwable ->
                CalculationResult.Error(throwable.message ?: "Invalid expression")
            },
        )
    }

    private fun formatValue(value: Double): String {
        val sanitizedValue = if (abs(value) < EPSILON) 0.0 else value
        val wholeNumberCandidate = sanitizedValue.toLong().toDouble()
        return if (wholeNumberCandidate == sanitizedValue) {
            wholeNumberCandidate.toLong().toString()
        } else {
            sanitizedValue.toString().trimEnd('0').trimEnd('.')
        }
    }

    private class ExpressionParser(
        private val expression: String,
    ) {
        private var cursor: Int = 0

        fun parse(): Double {
            val value = parseExpression()
            skipWhitespace()
            if (cursor != expression.length) {
                throw IllegalArgumentException("Malformed expression")
            }
            return value
        }

        private fun parseExpression(): Double {
            var value = parseTerm()
            while (true) {
                skipWhitespace()
                value = when {
                    match('+') -> value + parseTerm()
                    match('-') -> value - parseTerm()
                    else -> return value
                }
            }
        }

        private fun parseTerm(): Double {
            var value = parseFactor()
            while (true) {
                skipWhitespace()
                value = when {
                    match('*') -> value * parseFactor()
                    match('/') -> {
                        val divisor = parseFactor()
                        if (abs(divisor) < EPSILON) {
                            throw IllegalArgumentException("Division by zero")
                        }
                        value / divisor
                    }

                    else -> return value
                }
            }
        }

        private fun parseFactor(): Double {
            skipWhitespace()
            return when {
                match('+') -> parseFactor()
                match('-') -> -parseFactor()
                match('(') -> {
                    val nested = parseExpression()
                    if (!match(')')) {
                        throw IllegalArgumentException("Missing closing bracket")
                    }
                    nested
                }

                else -> parseNumber()
            }
        }

        private fun parseNumber(): Double {
            skipWhitespace()
            val startIndex = cursor
            var containsDecimal = false

            while (cursor < expression.length) {
                val current = expression[cursor]
                when {
                    current.isDigit() -> cursor++
                    current == '.' && !containsDecimal -> {
                        containsDecimal = true
                        cursor++
                    }

                    else -> break
                }
            }

            if (startIndex == cursor) {
                throw IllegalArgumentException("Expected number")
            }

            val token = expression.substring(startIndex, cursor)
            return token.toDoubleOrNull() ?: throw IllegalArgumentException("Invalid number")
        }

        private fun match(char: Char): Boolean {
            skipWhitespace()
            if (cursor < expression.length && expression[cursor] == char) {
                cursor++
                return true
            }
            return false
        }

        private fun skipWhitespace() {
            while (cursor < expression.length && expression[cursor].isWhitespace()) {
                cursor++
            }
        }
    }

    private companion object {
        const val EPSILON = 0.0000000001
    }
}
