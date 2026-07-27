package com.example.expensetracker.presentation.screens.auth.sign_up

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.expensetracker.R
import com.example.expensetracker.common.Resource
import com.example.expensetracker.presentation.components.auth.actions.SignUpFormActions
import com.example.expensetracker.presentation.components.auth.SignUpFooter
import com.example.expensetracker.presentation.components.auth.SignUpForm
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpScreen(
    navController: NavHostController,
    viewModel: SignUpViewModel = koinViewModel()
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = R.string.label_signup), style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.padding(all = 16.dp))

            SignUpForm(
                email = email,
                password = password,
                confirmPassword = confirmPassword,
                actions = SignUpFormActions(
                    onEmailChange = { email = it },
                    onPasswordChange = { password = it },
                    onConfirmPasswordChange = { confirmPassword = it },
                    onSignUpClick = { viewModel.signUp(email, password, confirmPassword) }
                ),
                isLoading = signUpState is Resource.Loading
            )

            Spacer(modifier = Modifier.padding(all = 16.dp))

            SignUpFooter(
                onLoginClick = {
                    navController.navigate(route = loginRoute) {
                        popUpTo(route = signupRoute) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
