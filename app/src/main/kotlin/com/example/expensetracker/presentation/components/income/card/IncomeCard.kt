package com.example.expensetracker.presentation.components.income.card

import androidx.compose.runtime.Composable
import com.example.expensetracker.data.model.income.IncomeItem
import com.example.expensetracker.presentation.components.income.actions.IncomeCardActions

@Composable
fun IncomeCard(
    income: IncomeItem,
    isEditing: Boolean,
    actions: IncomeCardActions,
    isNewIncome: Boolean = false
) {
    if (isEditing) {
        IncomeEditCard(
            income = income,
            onCancelEdit = actions.onCancelEdit,
            onSaveEdit = actions.onSaveEdit,
            isNewIncome = isNewIncome
        )
    } else {
        IncomeViewCard(
            income = income,
            onEditClick = actions.onEditClick,
            onDeleteClick = actions.onDeleteClick,
        )
    }
}