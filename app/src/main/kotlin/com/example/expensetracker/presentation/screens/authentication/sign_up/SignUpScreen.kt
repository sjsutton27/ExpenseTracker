package com.example.expensetracker.presentation.screens.authentication.sign_up

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.expensetracker.R
import com.example.expensetracker.common.Resource

@Composable
fun SignUpScreen(
    navController: NavHostController,
    viewModel: SignUpViewModel = viewModel()
) {
    val context = LocalContext.current
    val loginRoute = stringResource(id = R.string.route_login)
    val signupRoute = stringResource(id = R.string.route_signup)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val signUpState by viewModel.signUpState.collectAsState()

    LaunchedEffect(key1 = signUpState) {
        when (signUpState) {
            is Resource.Success -> {
                Toast.makeText(context, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                viewModel.resetState()
                navController.navigate(route = loginRoute) {
                    popUpTo(route = signupRoute) {
                        inclusive = true
                    }
                }
            }
            is Resource.Error -> {
                Toast.makeText(context, (signUpState as Resource.Error).message, Toast.LENGTH_SHORT).show()
                viewModel.resetState()
            }
            else -> {}
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(all = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = R.string.label_signup), style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.padding(all = 16.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { inputEmail -> email = inputEmail },
                label = { Text(text = stringResource(id = R.string.label_email)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(all = 16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { inputPassword -> password = inputPassword },
                label = { Text(text = stringResource(id = R.string.label_password)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(all = 16.dp))
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { inputConfirmPassword -> confirmPassword = inputConfirmPassword },
                label = { Text(text = stringResource(id = R.string.label_confirm_password)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(all = 16.dp))

            Button(
                onClick = {
                    viewModel.signUp(email, password, confirmPassword)
                },
                enabled = signUpState !is Resource.Loading,
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
            ) {
                if (signUpState is Resource.Loading) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                } else {
                    Text(text = stringResource(id = R.string.label_signup).lowercase().replaceFirstChar { char -> char.uppercase() })
                }
            }
            Spacer(modifier = Modifier.padding(all = 16.dp))
            Text(text = stringResource(id = R.string.txt_already_have_account))
            Button(
                onClick = {
                    navController.navigate(route = loginRoute) {
                        popUpTo(route = signupRoute) {
                            inclusive = true
                        }
                    }
                }
            ) {
                Text(text = stringResource(id = R.string.label_login).lowercase().replaceFirstChar { char -> char.uppercase() })
            }
        }
    }
}
