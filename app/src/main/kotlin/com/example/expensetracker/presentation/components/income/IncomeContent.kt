package com.example.expensetracker.presentation.components.income

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
import com.example.expensetracker.data.model.income.IncomeScreenState
import com.example.expensetracker.presentation.components.income.actions.IncomeCardActions
import com.example.expensetracker.presentation.components.income.card.IncomeCard
import com.example.expensetracker.presentation.screens.income.actions.IncomeActions

@Composable
fun IncomeContent(
    modifier: Modifier = Modifier,
    state: IncomeScreenState,
    actions: IncomeActions
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            val loading = state.getIncomesState is Resource.Loading
            if (loading) {
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

            // Add Income Card at the top
            if (!loading) {
                item {
                    IncomeSection(
                        addingIncome = state.addingIncome,
                        onAddIncomeClick = actions.onAddIncomeClick,
                        onCancelEdit = actions.onCancelAdd,
                        onSaveEdit = actions.onSaveNewIncome
                    )
                    Spacer(
                        modifier = Modifier.size(
                            size = 8.dp
                        )
                    )
                }
            }

            items(
                items = state.incomes.reversed(),
                key = { income ->
                    income.id
                }
            ) { income ->
                IncomeCard(
                    income = income,
                    isEditing = state.editingIncomeId == income.id,
                    actions = IncomeCardActions(
                        onEditClick = {
                            actions.onEditClick(income.id)
                        },
                        onCancelEdit = {
                            actions.onCancelEdit()
                        },
                        onSaveEdit = { updatedIncome ->
                            actions.onSaveEdit(updatedIncome)
                        },
                        onDeleteClick = {
                            actions.onDeleteClick(income.id)
                        }
                    )
                )
            }
        }
    }
}
