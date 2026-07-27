package com.example.expensetracker.presentation.components.expense

import androidx.compose.runtime.Composable
import com.example.expensetracker.data.model.expense.ExpenseItem
import com.example.expensetracker.presentation.components.expense.actions.ExpenseCardActions

@Composable
fun ExpenseCard(
    expense: ExpenseItem,
    isEditing: Boolean,
    actions: ExpenseCardActions,
    isNewExpense: Boolean = false
) {
    if (isEditing) {
        ExpenseEditCard(
            expense = expense,
            onCancelEdit = actions.onCancelEdit,
            onSaveEdit = actions.onSaveEdit,
            isNewExpense = isNewExpense
        )
    } else {
        ExpenseViewCard(
            expense = expense,
            onEditClick = actions.onEditClick,
            onDeleteClick = actions.onDeleteClick
        )
    }
}
