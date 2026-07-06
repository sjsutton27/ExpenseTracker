package com.example.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.expensetracker.navigation.ExpenseNavGraph
import com.example.expensetracker.presentation.components.BottomNavBar
import com.example.expensetracker.ui.theme.ExpenseTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            ExpenseTrackerTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val loginRoute = stringResource(id = R.string.route_login)
                val signupRoute = stringResource(id = R.string.route_signup)

                // Only show bottom bar if we are NOT on auth screens
                // And wait for currentRoute to be non-null to avoid flicker
                val showBottomBar = currentRoute != null && 
                                   currentRoute != loginRoute && 
                                   currentRoute != signupRoute

                Scaffold(
                    bottomBar = {
                        if (showBottomBar) {
                            BottomNavBar(navController = navController)
                        }
                    },
                ){
                    padding ->
                    ExpenseNavGraph(
                        navController = navController,
                        paddingValues = padding
                    )
                }
            }
        }
    }
}