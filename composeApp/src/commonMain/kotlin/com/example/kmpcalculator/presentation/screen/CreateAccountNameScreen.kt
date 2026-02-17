package com.example.kmpcalculator.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.matchParentSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val screenBackgroundColor = Color.Black
private val formOutlineColor = Color.White
private val fieldBackgroundColor = Color(0xFFDCDCDC)
private val fieldTextColor = Color.Black
private val fieldHintColor = Color(0xFFA2A2A2)

@Composable
fun CreateAccountNameScreen(
    modifier: Modifier = Modifier,
    illustration: Painter? = null,
    onBackClick: () -> Unit = {},
    onContinueClick: (email: String, firstName: String, lastName: String) -> Unit = { _, _, _ -> },
) {
    var email by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(screenBackgroundColor)
            .verticalScroll(rememberScrollState()),
    ) {
        BackToolbar(onBackClick = onBackClick)

        HeaderIllustration(illustration = illustration)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Create Account",
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 21.sp,
            fontWeight = FontWeight.SemiBold,
        )

        Spacer(modifier = Modifier.height(28.dp))

        BottomFormSection(
            email = email,
            onEmailChanged = { email = it },
            firstName = firstName,
            onFirstNameChanged = { firstName = it },
            lastName = lastName,
            onLastNameChanged = { lastName = it },
            onContinueClick = {
                onContinueClick(
                    email.trim(),
                    firstName.trim(),
                    lastName.trim(),
                )
            },
        )
    }
}

@Composable
private fun BackToolbar(
    onBackClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 14.dp, top = 8.dp, end = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "<",
            modifier = Modifier
                .size(30.dp)
                .clip(RoundedCornerShape(15.dp))
                .clickable(onClick = onBackClick)
                .wrapContentSize(Alignment.Center),
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Composable
private fun HeaderIllustration(
    illustration: Painter?,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(176.dp),
        contentAlignment = Alignment.Center,
    ) {
        if (illustration != null) {
            Image(
                painter = illustration,
                contentDescription = "Create account illustration",
                modifier = Modifier.fillMaxSize(),
            )
        } else {
            Text(
                text = "create_account_1",
                color = Color(0xFF6F6F6F),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

@Composable
private fun BottomFormSection(
    email: String,
    onEmailChanged: (String) -> Unit,
    firstName: String,
    onFirstNameChanged: (String) -> Unit,
    lastName: String,
    onLastNameChanged: (String) -> Unit,
    onContinueClick: () -> Unit,
) {
    val topRoundedShape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp)

    Box(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(topRoundedShape)
                .background(formOutlineColor),
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(top = 1.dp)
                .clip(topRoundedShape)
                .background(screenBackgroundColor),
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 16.dp),
        ) {
            Spacer(modifier = Modifier.height(28.dp))

            CreateAccountTextField(
                value = email,
                onValueChange = onEmailChanged,
                hint = "Email Address",
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                ),
            )

            Spacer(modifier = Modifier.height(17.dp))

            CreateAccountTextField(
                value = firstName,
                onValueChange = onFirstNameChanged,
                hint = "First Name",
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next,
                ),
            )

            Spacer(modifier = Modifier.height(17.dp))

            CreateAccountTextField(
                value = lastName,
                onValueChange = onLastNameChanged,
                hint = "Last Name",
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Done,
                ),
            )

            Spacer(modifier = Modifier.height(17.dp))

            Button(
                onClick = onContinueClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 42.dp)
                    .border(
                        width = 1.dp,
                        color = formOutlineColor,
                        shape = RoundedCornerShape(50.dp),
                    ),
                shape = RoundedCornerShape(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = screenBackgroundColor,
                    contentColor = Color.White,
                ),
            ) {
                Text(
                    text = "Continue",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}

@Composable
private fun CreateAccountTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    keyboardOptions: KeyboardOptions,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp),
        shape = RoundedCornerShape(50.dp),
        singleLine = true,
        textStyle = TextStyle(
            color = fieldTextColor,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
        ),
        placeholder = {
            Text(
                text = hint,
                modifier = Modifier.fillMaxWidth(),
                color = fieldHintColor,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
            )
        },
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = fieldBackgroundColor,
            unfocusedContainerColor = fieldBackgroundColor,
            disabledContainerColor = fieldBackgroundColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedTextColor = fieldTextColor,
            unfocusedTextColor = fieldTextColor,
            disabledTextColor = fieldTextColor,
            focusedPlaceholderColor = fieldHintColor,
            unfocusedPlaceholderColor = fieldHintColor,
            disabledPlaceholderColor = fieldHintColor,
            cursorColor = fieldTextColor,
        ),
    )
}
