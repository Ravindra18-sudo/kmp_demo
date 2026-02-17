package com.example.kmpcalculator.presentation.screen

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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.weight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.BorderStroke

private data class AuthOption(
    val iconLabel: String,
    val label: String,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthLandingScreen(
    showAppleLogin: Boolean,
    modifier: Modifier = Modifier,
) {
    var isBottomSheetVisible by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 28.dp),
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            listOf("Chauffeur", "Valet", "Delivery", "Concierge").forEach { title ->
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White.copy(alpha = 0.9f),
                )
                Spacer(modifier = Modifier.height(6.dp))
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Courial",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(14.dp))
            Text(
                text = "Anything? Possible!",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(34.dp))
            OutlinedButton(
                onClick = { },
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White,
                    containerColor = Color.Transparent,
                ),
                border = BorderStroke(1.dp, Color.White),
                modifier = Modifier
                    .fillMaxWidth(0.64f)
                    .height(52.dp),
            ) {
                Text(text = "Just Browsing")
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(bottom = 22.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Button(
                onClick = { isBottomSheetVisible = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                ),
                shape = RoundedCornerShape(28.dp),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp),
            ) {
                Text(
                    text = "Sign In",
                    style = MaterialTheme.typography.labelLarge,
                )
            }
            OutlinedButton(
                onClick = { isBottomSheetVisible = true },
                shape = RoundedCornerShape(28.dp),
                border = BorderStroke(1.dp, Color.White),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White,
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp),
            ) {
                Text(
                    text = "Sign Up",
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
    }

    if (isBottomSheetVisible) {
        ModalBottomSheet(
            onDismissRequest = { isBottomSheetVisible = false },
            containerColor = Color.Black,
            contentColor = Color.White,
            shape = RoundedCornerShape(topStart = 36.dp, topEnd = 36.dp),
            dragHandle = {
                Box(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .size(width = 56.dp, height = 4.dp)
                        .background(
                            color = Color.White.copy(alpha = 0.28f),
                            shape = RoundedCornerShape(50),
                        ),
                )
            },
        ) {
            AuthBottomSheetContent(
                showAppleLogin = showAppleLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .navigationBarsPadding()
                    .padding(bottom = 12.dp),
            )
        }
    }
}

@Composable
private fun AuthBottomSheetContent(
    showAppleLogin: Boolean,
    modifier: Modifier = Modifier,
) {
    val authOptions = mutableListOf<AuthOption>()
    if (showAppleLogin) {
        authOptions += AuthOption(iconLabel = "A", label = "Continue with Apple")
    }
    authOptions += AuthOption(iconLabel = "G", label = "Continue with Google")
    authOptions += AuthOption(iconLabel = "+1", label = "Continue with Phone")

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        authOptions.forEach { option ->
            AuthOptionButton(
                iconLabel = option.iconLabel,
                label = option.label,
                onClick = { },
            )
        }

        OutlinedButton(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Transparent,
                contentColor = Color.White,
            ),
            border = BorderStroke(1.dp, Color.White),
        ) {
            Text(
                text = "Continue",
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}

@Composable
private fun AuthOptionButton(
    iconLabel: String,
    label: String,
    onClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(28.dp),
        color = Color(0xFFE9E9E9),
        contentColor = Color(0xFF1F1F1F),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .border(1.dp, Color(0xFFD0D0D0), CircleShape),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = iconLabel,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF2A2A2A),
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Spacer(modifier = Modifier.size(14.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFF1F1F1F),
            )
        }
    }
}
