package com.example.kmpcalculator.di

import com.example.kmpcalculator.data.repository.CalculatorRepositoryImpl
import com.example.kmpcalculator.domain.usecase.AppendInputUseCase
import com.example.kmpcalculator.domain.usecase.ClearInputUseCase
import com.example.kmpcalculator.domain.usecase.DeleteInputUseCase
import com.example.kmpcalculator.domain.usecase.EvaluateExpressionUseCase
import com.example.kmpcalculator.presentation.viewmodel.CalculatorViewModel

object AppModule {
    private val repository by lazy { CalculatorRepositoryImpl() }

    fun provideCalculatorViewModel(): CalculatorViewModel {
        return CalculatorViewModel(
            appendInputUseCase = AppendInputUseCase(),
            clearInputUseCase = ClearInputUseCase(),
            deleteInputUseCase = DeleteInputUseCase(),
            evaluateExpressionUseCase = EvaluateExpressionUseCase(repository),
        )
    }
}
