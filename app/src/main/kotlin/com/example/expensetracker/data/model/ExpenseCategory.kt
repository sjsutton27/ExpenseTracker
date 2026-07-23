package com.example.expensetracker.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class ExpenseCategory(
    val displayName: String,
    val icon: ImageVector,
    val color: Color
) {
    GROCERIES(
        displayName = "Groceries",
        icon = Icons.Default.ShoppingCart,
        color = Color(0xFF4CAF50)
    ),

    GAS(
        displayName = "Gas",
        icon = Icons.Default.LocalGasStation,
        color = Color(0xFFFF9800)
    ),

    RESTAURANTS(
        displayName = "Restaurants",
        icon = Icons.Default.Restaurant,
        color = Color(0xFFF44336)
    ),

    SHOPPING(
        displayName = "Shopping",
        icon = Icons.Default.ShoppingBag,
        color = Color(0xFFE91E63)
    ),

    BILLS(
        displayName = "Bills",
        icon = Icons.Default.Receipt,
        color = Color(0xFF2196F3)
    ),

    SUBSCRIPTIONS(
        displayName = "Subscriptions",
        icon = Icons.Default.Subscriptions,
        color = Color(0xFF9C27B0)
    ),

    HEALTH(
        displayName = "Health",
        icon = Icons.Default.LocalHospital,
        color = Color(0xFF009688)
    ),

    FITNESS(
        displayName = "Fitness",
        icon = Icons.Default.FitnessCenter,
        color = Color(0xFF673AB7)
    ),

    TRANSPORTATION(
        displayName = "Transportation",
        icon = Icons.Default.DirectionsCar,
        color = Color(0xFF3F51B5)
    ),

    TRAVEL(
        displayName = "Travel",
        icon = Icons.Default.Flight,
        color = Color(0xFF03A9F4)
    ),

    ENTERTAINMENT(
        displayName = "Entertainment",
        icon = Icons.Default.Movie,
        color = Color(0xFFFF5722)
    ),

    INSURANCE(
        displayName = "Insurance",
        icon = Icons.Default.Security,
        color = Color(0xFF607D8B)
    ),

    PERSONAL_CARE(
        displayName = "Personal Care",
        icon = Icons.Default.Face,
        color = Color(0xFFFFC107)
    ),

    HOUSING(
        displayName = "Housing",
        icon = Icons.Default.Home,
        color = Color(0xFF795548)
    ),

    OTHER(
        displayName = "Other",
        icon = Icons.Default.AttachMoney,
        color = Color(0xFF9E9E9E)
    )
}