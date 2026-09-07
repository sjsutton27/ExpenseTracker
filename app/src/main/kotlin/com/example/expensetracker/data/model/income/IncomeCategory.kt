package com.example.expensetracker.data.model.income

import androidx.compose.ui.graphics.Color

@Suppress("MagicNumber")
enum class IncomeCategory(
    val displayName: String,
    val color: Color
) {
    SALARY(
        displayName = "Salary",
        color = Color(0xFF4CAF50)
    ),
    SIDE_HUSTLE(
        displayName = "Side Hustle",
        color = Color(0xFF2196F3)
    ),
    FREELANCE(
        displayName = "Freelance",
        color = Color(0xFF03A9F4)
    ),
    BUSINESS(
        displayName = "Business",
        color = Color(0xFF9C27B0)
    ),
    INVESTMENTS(
        displayName = "Investments",
        color = Color(0xFFFF9800)
    ),
    DIVIDENDS(
        displayName = "Dividends",
        color = Color(0xFFFFC107)
    ),
    INTEREST(
        displayName = "Interest",
        color = Color(0xFF009688)
    ),
    RENTAL_INCOME(
        displayName = "Rental Income",
        color = Color(0xFF795548)
    ),
    PENSION(
        displayName = "Pension",
        color = Color(0xFF607D8B)
    ),
    GOVERNMENT_BENEFITS(
        displayName = "Government Benefits",
        color = Color(0xFF3F51B5)
    ),
    TAX_REFUND(
        displayName = "Tax Refund",
        color = Color(0xFF00BCD4)
    ),
    GIFT(
        displayName = "Gift",
        color = Color(0xFFE91E63)
    ),
    ALIMONY(
        displayName = "Alimony",
        color = Color(0xFFF44336)
    ),
    ROYALTIES(
        displayName = "Royalties",
        color = Color(0xFF673AB7)
    ),
    OTHER(
        displayName = "Other",
        color = Color(0xFF9E9E9E)
    )
}
