package com.example.kmpcalculator.ui.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kmpcalculator.R
import com.example.kmpcalculator.ui.theme.CalculatorTheme

@Composable
fun EnterCodeScreen(
    phoneNumber: String,
    otpCode: TextFieldValue,
    modifier: Modifier = Modifier,
    showPinView: Boolean = false,
    showTimer: Boolean = true,
    showResend: Boolean = false,
    showContinueButton: Boolean = false,
    timerText: String = "00:05",
    onBackClick: () -> Unit = {},
    onOtpChange: (TextFieldValue) -> Unit = {},
    onResendClick: () -> Unit = {},
    onContinueClick: () -> Unit = {},
) {
    val accentColor = MaterialTheme.colorScheme.primary
    val titleColor = MaterialTheme.colorScheme.onBackground

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp),
        ) {
            EnterCodeToolbar(
                onBackClick = onBackClick,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.a_4_digit_code_sent_to),
                    color = titleColor,
                    fontSize = 18.sp,
                )
                Text(
                    text = " $phoneNumber",
                    color = accentColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = stringResource(R.string.enter_it_below2),
                color = titleColor,
                fontSize = 18.sp,
            )

            if (showPinView) {
                Spacer(modifier = Modifier.height(30.dp))
                PinViewPreviewRow(code = otpCode.text)
            } else {
                Spacer(modifier = Modifier.height(28.dp))
                OutlinedTextField(
                    value = otpCode,
                    onValueChange = { value ->
                        val digitsOnly = value.text.filter(Char::isDigit).take(4)
                        onOtpChange(
                            TextFieldValue(
                                text = digitsOnly,
                                selection = TextRange(digitsOnly.length),
                            ),
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(text = stringResource(R.string.otp_hint))
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = accentColor,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    ),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.did_t_recieve_it),
                color = titleColor,
                fontSize = 18.sp,
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (showTimer) {
                Text(
                    text = stringResource(R.string.we_ll_resend_code_in, timerText),
                    color = Color(0xFF245EA7),
                    fontSize = 16.sp,
                )
            }

            if (showResend) {
                Text(
                    text = stringResource(R.string.resend),
                    color = titleColor,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.clickable(onClick = onResendClick),
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            if (showContinueButton) {
                Button(
                    onClick = onContinueClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = accentColor,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                ) {
                    Text(text = stringResource(R.string.continuetext))
                }
            }
        }
    }
}

@Composable
fun EnterCodeScreen(
    phoneNumber: String,
    modifier: Modifier = Modifier,
    showPinView: Boolean = false,
    showTimer: Boolean = true,
    showResend: Boolean = false,
    showContinueButton: Boolean = false,
    timerText: String = "00:05",
    onBackClick: () -> Unit = {},
    onResendClick: () -> Unit = {},
    onContinueClick: () -> Unit = {},
) {
    var otpCode by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }

    EnterCodeScreen(
        phoneNumber = phoneNumber,
        otpCode = otpCode,
        modifier = modifier,
        showPinView = showPinView,
        showTimer = showTimer,
        showResend = showResend,
        showContinueButton = showContinueButton,
        timerText = timerText,
        onBackClick = onBackClick,
        onOtpChange = { otpCode = it },
        onResendClick = onResendClick,
        onContinueClick = onContinueClick,
    )
}

@Composable
private fun EnterCodeToolbar(
    onBackClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.back_arrow),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 24.sp,
            modifier = Modifier.clickable(onClick = onBackClick),
        )
    }
}

@Composable
private fun PinViewPreviewRow(
    code: String,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        repeat(4) { index ->
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFBFC6D4),
                        shape = RoundedCornerShape(8.dp),
                    ),
                contentAlignment = Alignment.Center,
            ) {
                val char = code.getOrNull(index)?.toString().orEmpty()
                Text(
                    text = char,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 15.sp,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EnterCodeScreenPreview() {
    CalculatorTheme {
        EnterCodeScreen(
            phoneNumber = "(213) 555-1212",
            showTimer = true,
            showResend = false,
            showContinueButton = false,
        )
    }
}
