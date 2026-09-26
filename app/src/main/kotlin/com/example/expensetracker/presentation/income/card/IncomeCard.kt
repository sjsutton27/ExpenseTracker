package com.example.expensetracker.presentation.income.card

import androidx.compose.runtime.Composable
import com.example.expensetracker.data.model.income.IncomeItem
import com.example.expensetracker.presentation.components.expense.card.ExpenseEditCard
import com.example.expensetracker.presentation.components.expense.card.ExpenseViewCard
import com.example.expensetracker.presentation.income.actions.IncomeCardActions

@Composable
fun IncomeCard(
    income: IncomeItem,
    isEditing: Boolean,
    actions: IncomeCardActions,
    isNewIncome: Boolean = false
) {
    if (isEditing) {
        IncomeEditCard(

        )
    } else {
        IncomeViewCard(

        )
    }
}