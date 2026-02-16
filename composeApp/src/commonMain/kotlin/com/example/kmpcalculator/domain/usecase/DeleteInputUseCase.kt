package com.example.kmpcalculator.domain.usecase

class DeleteInputUseCase {
    operator fun invoke(current: String): String {
        if (current.isEmpty()) return current
        return current.dropLast(1)
    }
}
