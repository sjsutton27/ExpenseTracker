package com.example.expensetracker.data.util

import com.example.expensetracker.common.Constants
import com.example.expensetracker.data.model.ExpenseItem
import com.example.expensetracker.domain.repository.ExpenseRepository

suspend fun prepareExpense(repository: ExpenseRepository, expense: ExpenseItem): ExpenseItem {
    val logo = repository.getMerchantLogo(merchant = expense.merchant)

    return if (logo != null && logo.domain.isNotBlank()) {
        expense.copy(
            merchantDomain = logo.domain,
            imageUrl = logo.url
        )
    } else {
        val cleanMerchant = expense.merchant.lowercase().trim().replace(" ", "")
        if (cleanMerchant.isBlank()) {
            return expense.copy(
                merchantDomain = "",
                imageUrl = Constants.DEFAULT_LOGO
            )
        }
        val domain = if (cleanMerchant.contains(".")) cleanMerchant else "$cleanMerchant.com"
        expense.copy(
            merchantDomain = domain,
            imageUrl = "https://img.logo.dev/$domain?token=${Constants.API_KEY}"
        )
    }
}
