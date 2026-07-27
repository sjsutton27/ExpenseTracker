package com.example.expensetracker.presentation.components.auth

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.expensetracker.R

@Composable
fun SignUpFooter(
    onLoginClick: () -> Unit
) {
    Text(text = stringResource(id = R.string.txt_already_have_account))
    Button(
        onClick = onLoginClick
    ) {
        Text(
            text = stringResource(
                id = R.string.label_login
            ).lowercase().replaceFirstChar { char -> char.uppercase() }
        )
    }
}
