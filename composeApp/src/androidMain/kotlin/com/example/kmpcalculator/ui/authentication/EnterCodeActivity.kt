package com.example.kmpcalculator.ui.authentication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.kmpcalculator.ui.theme.CalculatorTheme

class EnterCodeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                EnterCodeScreen(
                    phoneNumber = "(213) 555-1212",
                    showPinView = false,
                    showTimer = true,
                    showResend = false,
                    showContinueButton = false,
                )
            }
        }
    }
}
