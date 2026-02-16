package com.example.kmpcalculator.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kmpcalculator.presentation.action.CalculatorAction
import com.example.kmpcalculator.presentation.component.CalculatorButton
import com.example.kmpcalculator.presentation.state.CalculatorUiState
import com.example.kmpcalculator.ui.theme.AppTheme

private val calculatorLayout = listOf(
    listOf("C", "⌫", "/", "*"),
    listOf("7", "8", "9", "-"),
    listOf("4", "5", "6", "+"),
    listOf("1", "2", "3", "="),
    listOf("0", ".", "(", ")"),
)

@Composable
fun CalculatorScreen(
    uiState: CalculatorUiState,
    onAction: (CalculatorAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = if (uiState.expression.isBlank()) "0" else uiState.expression,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.End,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Text(
            text = uiState.result,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.End,
            color = if (uiState.isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
        )

        Spacer(modifier = Modifier.height(4.dp))

        calculatorLayout.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                row.forEach { label ->
                    val colors = buttonColors(label)
                    CalculatorButton(
                        label = label,
                        onClick = { onAction(label.toAction()) },
                        containerColor = colors.first,
                        contentColor = colors.second,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

private fun String.toAction(): CalculatorAction {
    return when (this) {
        "C" -> CalculatorAction.Clear
        "⌫" -> CalculatorAction.Delete
        "=" -> CalculatorAction.Calculate
        else -> CalculatorAction.Input(this)
    }
}

@Composable
private fun buttonColors(label: String): Pair<Color, Color> {
    val themeButtonColors = AppTheme.buttonColors
    val defaultContentColor = AppTheme.colors.onBackground

    return when (label) {
        "C", "⌫" -> themeButtonColors.actionButton to defaultContentColor
        "+", "-", "*", "/" -> themeButtonColors.operatorButton to defaultContentColor
        "=" -> themeButtonColors.equalButton to themeButtonColors.onEqualButton
        else -> themeButtonColors.digitButton to defaultContentColor
    }
}
