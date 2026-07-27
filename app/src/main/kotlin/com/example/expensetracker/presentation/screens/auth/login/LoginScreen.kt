package com.example.expensetracker.presentation.screens.auth.login

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
import com.example.expensetracker.presentation.components.auth.LoginFooter
import com.example.expensetracker.presentation.components.auth.LoginForm
import com.example.expensetracker.presentation.components.auth.actions.LoginFormActions
import org.koin.androidx.compose.koinViewModel
@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val dashboardRoute = stringResource(id = R.string.route_dashboard)
    val loginRoute = stringResource(id = R.string.route_login)
    val signupRoute = stringResource(id = R.string.route_signup)
    val forgotPasswordRoute = stringResource(id = R.string.route_forgot_password)

    var email by remember { mutableStateOf(value = "") }
    var password by remember { mutableStateOf(value = "") }

    val loginState by viewModel.loginState.collectAsState()

    LaunchedEffect(key1 = loginState) {
        when (loginState) {
            is Resource.Success -> {
                Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show()
                viewModel.resetState()
                navController.navigate(route = dashboardRoute) {
                    popUpTo(route = loginRoute) {
                        inclusive = true
                    }
                }
            }
            is Resource.Error -> {
                Toast.makeText(context, (loginState as Resource.Error).message, Toast.LENGTH_SHORT).show()
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
            Text(text = stringResource(id = R.string.label_login), style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.padding(all = 16.dp))

            LoginForm(
                email = email,
                password = password,
                actions = LoginFormActions(
                    onEmailChange = { email = it },
                    onPasswordChange = { password = it },
                    onLoginClick = { viewModel.login(email, password) }
                ),
                isLoading = loginState is Resource.Loading
            )

            Spacer(modifier = Modifier.padding(all = 16.dp))

            LoginFooter(
                onSignUpClick = {
                    navController.navigate(route = signupRoute) {
                        popUpTo(route = loginRoute) {
                            inclusive = true
                        }
                    }
                },
                onForgotPasswordClick = {
                    navController.navigate(route = forgotPasswordRoute)
                    viewModel.resetState()
                }
            )
        }
    }
}
