package com.example.expensetracker.presentation.components.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.expensetracker.R

@Composable
fun LoginFooter(
    onSignUpClick: () -> Unit,
    onForgotPasswordClick: () -> Unit
) {
    Text(text = stringResource(id = R.string.txt_dont_have_account))
    Button(
        onClick = onSignUpClick
    ) {
        Text(
            text = stringResource(
                id = R.string.label_signup
            ).lowercase().replaceFirstChar { char -> char.uppercase() }
        )
    }

    Spacer(modifier = Modifier.padding(all = 16.dp))
    Text(
        text = stringResource(id = R.string.txt_forgot_password),
        color = MaterialTheme.colorScheme.primary,
        textDecoration = TextDecoration.Underline,
        modifier = Modifier
            .clickable(
                onClick = onForgotPasswordClick
            )
    )
}
