package com.example.expensetracker.presentation.components.expense

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.expensetracker.common.Resource
import com.example.expensetracker.data.model.expense.ExpenseScreenState
import com.example.expensetracker.presentation.components.expense.actions.ExpenseCardActions
import com.example.expensetracker.presentation.screens.expense.actions.ExpenseActions

@Composable
fun ExpenseContent(
    modifier: Modifier = Modifier,
    state: ExpenseScreenState,
    actions: ExpenseActions
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            if (state.getExpensesState is Resource.Loading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            // Add Expense Card at the top
            if (state.getExpensesState !is Resource.Loading) {
                item {
                    ExpenseSection(
                        addingExpense = state.addingExpense,
                        onAddExpenseClick = actions.onAddExpenseClick,
                        onCancelEdit = actions.onCancelAdd,
                        onSaveEdit = actions.onSaveNewExpense
                    )
                    Spacer(
                        modifier = Modifier.size(
                            size = 8.dp
                        )
                    )
                }
            }

            items(
                items = state.expenses.reversed(),
                key = { expense ->
                    expense.id
                }
            ) { expense ->
                ExpenseCard(
                    expense = expense,
                    isEditing = state.editingExpenseId == expense.id,
                    actions = ExpenseCardActions(
                        onEditClick = {
                            actions.onEditClick(expense.id)
                        },
                        onCancelEdit = {
                            actions.onCancelEdit()
                        },
                        onSaveEdit = { updatedExpense ->
                            actions.onSaveEdit(updatedExpense)
                        },
                        onDeleteClick = {
                            actions.onDeleteClick(expense.id)
                        }
                    )
                )
            }
        }
    }
}
