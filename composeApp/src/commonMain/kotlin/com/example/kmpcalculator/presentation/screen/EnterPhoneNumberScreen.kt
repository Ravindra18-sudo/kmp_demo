package com.example.kmpcalculator.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kmpcalculator.ui.theme.AppTheme
import com.example.kmpcalculator.ui.theme.CalculatorTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

private val AllowedPhoneCharacters = setOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '-', '(', ')', ' ')

/**
 * Jetpack Compose version of the phone number entry XML layout.
 */
@Suppress("FunctionName")
@Composable
fun enterPhoneNumberScreen(
    modifier: Modifier = Modifier,
    countryCode: String = "+1",
    phoneNumber: String = "",
    showCountryDropdown: Boolean = false,
    showVerificationHint: Boolean = false,
    showClearButton: Boolean = phoneNumber.isNotEmpty(),
    onCountryClick: () -> Unit = {},
    onPhoneNumberChange: (String) -> Unit = {},
    onClearClick: () -> Unit = { onPhoneNumberChange("") },
    onContinueClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.background),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ToolbarPlaceholder()

            Text(
                text = "Phone number",
                modifier = Modifier.padding(start = 18.dp, end = 18.dp, top = 28.dp),
                color = Color(0xFF4A4A4A),
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 18.dp, end = 18.dp, top = 10.dp),
            ) {
                CountryCodeInput(
                    countryCode = countryCode,
                    showCountryDropdown = showCountryDropdown,
                    onCountryClick = onCountryClick,
                )

                PhoneInput(
                    phoneNumber = phoneNumber,
                    showClearButton = showClearButton,
                    onPhoneNumberChange = onPhoneNumberChange,
                    onClearClick = onClearClick,
                modifier = Modifier.weight(1f),
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(start = 18.dp, end = 18.dp, bottom = 32.dp),
        ) {
            if (showVerificationHint) {
                Text(
                    text = "Send me a verification code",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 9.dp),
                    color = Color(0xFF2D5FA8),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Normal,
                )
            }

            Button(
                onClick = onContinueClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 44.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF68A1E),
                    contentColor = Color.White,
                ),
            ) {
                Text(
                    text = "Continue",
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}

@Composable
private fun ToolbarPlaceholder() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
    )
}

@Composable
private fun CountryCodeInput(
    countryCode: String,
    showCountryDropdown: Boolean,
    onCountryClick: () -> Unit,
) {
    Column(
        modifier = Modifier.wrapContentWidth(),
        verticalArrangement = Arrangement.Top,
    ) {
        Row(
            modifier = Modifier
                .height(36.dp)
                .clip(RoundedCornerShape(4.dp))
                .clickable(onClick = onCountryClick)
                .padding(start = 4.dp, end = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            FlagPlaceholder()

            Text(
                text = countryCode,
                modifier = Modifier.padding(start = 4.dp, end = 6.dp),
                color = Color.Black,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
            )

            if (showCountryDropdown) {
                Text(
                    text = "v",
                    color = Color(0xFF5A5A5A),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Black),
        )
    }
}

@Composable
private fun FlagPlaceholder() {
    Box(
        modifier = Modifier
            .width(22.dp)
            .height(14.dp)
            .clip(RoundedCornerShape(2.dp))
            .background(Color(0xFFE4E4E4)),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "US",
            color = Color(0xFF666666),
            fontSize = 8.sp,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Composable
private fun PhoneInput(
    phoneNumber: String,
    showClearButton: Boolean,
    onPhoneNumberChange: (String) -> Unit,
    onClearClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(start = 6.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .padding(start = 8.dp, end = 8.dp),
            contentAlignment = Alignment.CenterStart,
        ) {
            BasicTextField(
                value = phoneNumber,
                onValueChange = { rawValue ->
                    onPhoneNumberChange(rawValue.filter { it in AllowedPhoneCharacters })
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = if (showClearButton) 22.dp else 0.dp),
                singleLine = true,
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done,
                ),
                decorationBox = { innerTextField ->
                    if (phoneNumber.isEmpty()) {
                        Text(
                            text = "Enter mobile number",
                            color = Color(0xFF9A9A9A),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                    innerTextField()
                },
            )

            if (showClearButton) {
                Text(
                    text = "x",
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickable(onClick = onClearClick),
                    color = Color(0xFF5F5F5F),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
                .height(1.dp)
                .background(Color.Black),
        )
    }
}

@Preview
@Composable
private fun EnterPhoneNumberScreenPreview() {
    CalculatorTheme {
        var phone by remember { mutableStateOf("") }
        enterPhoneNumberScreen(
            phoneNumber = phone,
            showVerificationHint = true,
            onPhoneNumberChange = { phone = it },
        )
    }
}
