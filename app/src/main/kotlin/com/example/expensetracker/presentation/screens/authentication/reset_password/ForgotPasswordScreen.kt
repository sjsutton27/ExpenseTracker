package com.example.expensetracker.presentation.screens.authentication.reset_password

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.expensetracker.R
import com.example.expensetracker.common.Resource

@Composable
fun ForgotPasswordScreen(
    navController: NavHostController,
    viewModel: ForgotPasswordViewModel = viewModel()
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf(value = "") }
    val forgotPasswordState by viewModel.forgotPasswordState.collectAsState()
    val loginRoute = stringResource(id = R.string.route_login)

    LaunchedEffect(key1 = forgotPasswordState) {
        when (forgotPasswordState) {
            is Resource.Success -> {
                Toast.makeText(/* context = */ context, /* text = */
                    (forgotPasswordState as Resource.Success).data, /* duration = */
                    Toast.LENGTH_SHORT).show()
                viewModel.resetState()
                navController.navigate(route = loginRoute) {
                    popUpTo(route = loginRoute) {
                        inclusive = true
                    }
                }
            }
            is Resource.Error -> {
                Toast.makeText(/* context = */ context, /* text = */
                    (forgotPasswordState as Resource.Error).message, /* duration = */
                    Toast.LENGTH_SHORT).show()
                viewModel.resetState()
            }
            else -> {}
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(all = 16.dp),
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = stringResource(id = R.string.label_forgot_password), style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.padding(all = 16.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { inputEmail -> email = inputEmail },
                label = { Text(text = stringResource(id = R.string.label_email)) },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(all = 16.dp))
            Button(
                onClick = {
                    viewModel.forgotPassword(email)
                    if (forgotPasswordState is Resource.Success) {
                        navController.navigate(route = loginRoute) {
                            popUpTo(route = loginRoute) {
                                inclusive = true
                            }
                        }
                    }
                },
            ){
                Text(text = "Send Reset Email")
            }
            Spacer(modifier = Modifier.padding(all = 8.dp))
            Button(
                onClick = {
                    navController.navigate(route = loginRoute) {
                        popUpTo(route = loginRoute) {
                            inclusive = true
                        }
                    }
                },
            ){
                Text(text = "Cancel")
            }
        }
    }
}