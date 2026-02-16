package com.example.kmpcalculator.domain.usecase

class AppendInputUseCase {
    operator fun invoke(current: String, input: String): String {
        if (current == "0" && input.singleOrNull()?.isDigit() == true) {
            return input
        }
        return current + input
    }
}
