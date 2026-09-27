package com.example.expensetracker.data.model.income

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.FamilyRestroom
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Interests
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Percent
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.RequestQuote
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.Work
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Suppress("MagicNumber")
enum class IncomeCategory(
    val displayName: String,
    val icon: ImageVector,
    val color: Color
) {
    SALARY(
        displayName = "Salary",
        icon = Icons.Default.Work,
        color = Color(0xFF4CAF50)
    ),
    SIDE_HUSTLE(
        displayName = "Side Hustle",
        icon = Icons.Default.Handshake,
        color = Color(0xFF2196F3)
    ),
    FREELANCE(
        displayName = "Freelance",
        icon = Icons.Default.AttachMoney,
        color = Color(0xFF03A9F4)
    ),
    BUSINESS(
        displayName = "Business",
        icon = Icons.Default.Business,
        color = Color(0xFF9C27B0)
    ),
    INVESTMENTS(
        displayName = "Investments",
        icon = Icons.Default.CurrencyExchange,
        color = Color(0xFFFF9800)
    ),
    DIVIDENDS(
        displayName = "Dividends",
        icon = Icons.Default.Percent,
        color = Color(0xFFFFC107)
    ),
    INTEREST(
        displayName = "Interest",
        icon = Icons.Default.Interests,
        color = Color(0xFF009688)
    ),
    RENTAL_INCOME(
        displayName = "Rental Income",
        icon = Icons.Default.Home,
        color = Color(0xFF795548)
    ),
    PENSION(
        displayName = "Pension",
        icon = Icons.Default.Savings,
        color = Color(0xFF607D8B)
    ),
    GOVERNMENT_BENEFITS(
        displayName = "Government Benefits",
        icon = Icons.Default.AccountBalance,
        color = Color(0xFF3F51B5)
    ),
    TAX_REFUND(
        displayName = "Tax Refund",
        icon = Icons.Default.ReceiptLong,
        color = Color(0xFF00BCD4)
    ),
    GIFT(
        displayName = "Gift",
        icon = Icons.Default.CardGiftcard,
        color = Color(0xFFE91E63)
    ),
    ALIMONY(
        displayName = "Alimony",
        icon = Icons.Default.FamilyRestroom,
        color = Color(0xFFF44336)
    ),
    ROYALTIES(
        displayName = "Royalties",
        icon = Icons.Default.Payments,
        color = Color(0xFF673AB7)
    ),
    OTHER(
        displayName = "Other",
        icon = Icons.Default.RequestQuote,
        color = Color(0xFF9E9E9E)
    )
}