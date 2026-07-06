package com.example.expensetracker.presentation.screens.login

import android.R.attr.text
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
import androidx.navigation.NavHostController
import com.example.expensetracker.R
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(navController: NavHostController) {
    val context = LocalContext.current
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val dashboardRoute = stringResource(id = R.string.route_dashboard)
    val loginRoute = stringResource(id = R.string.route_login)
    val signupRoute = stringResource(id = R.string.route_signup)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(all = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = R.string.label_login), style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.padding(all = 16.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { value ->
                    email = value
                },
                label = {
                    Text(text = stringResource(id = R.string.label_email))
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(all = 16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { value ->
                    password = value
                },
                label = {
                    Text(text = stringResource(id = R.string.label_password))
                },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(all = 16.dp))

            Button(
                onClick = {
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "Login Successful",
                                    Toast.LENGTH_SHORT).show()
                                navController.navigate(route = dashboardRoute){
                                    popUpTo(route = loginRoute){
                                        inclusive = true
                                    }
                                }
                            }else{
                                Toast.makeText(
                                    context,
                                    "Login Failed",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium,
            ){
                Text(text = stringResource(id = R.string.label_login).lowercase().replaceFirstChar { it.uppercase() })
            }
            Spacer(modifier = Modifier.padding(all = 16.dp))
            Text(text = stringResource(id = R.string.txt_dont_have_account))
            Button(
                onClick = {
                    navController.navigate(route = signupRoute){
                        popUpTo(route = loginRoute){
                            inclusive = true
                        }
                    }
                }
            ) {
                Text(text = stringResource(id = R.string.label_signup).lowercase().replaceFirstChar { it.uppercase() })
            }

        }
    }
}