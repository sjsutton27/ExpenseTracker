package com.example.expensetracker.presentation.components.auth

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.expensetracker.R
import com.example.expensetracker.presentation.components.auth.actions.LoginFormActions

@Composable
fun LoginForm(
    email: String,
    password: String,
    actions: LoginFormActions,
    isLoading: Boolean
) {
    OutlinedTextField(
        value = email,
        onValueChange = actions.onEmailChange,
        label = { Text(text = stringResource(id = R.string.label_email)) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.padding(all = 16.dp))
    OutlinedTextField(
        value = password,
        onValueChange = actions.onPasswordChange,
        label = { Text(text = stringResource(id = R.string.label_password)) },
        singleLine = true,
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.padding(all = 16.dp))

    Button(
        onClick = actions.onLoginClick,
        enabled = !isLoading,
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(24.dp)
            )
        } else {
            Text(
                text = stringResource(
                    id = R.string.label_login
                ).lowercase().replaceFirstChar { char -> char.uppercase() }
            )
        }
    }
}
