package com.example.expensetracker.presentation.screens.expense

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.expensetracker.R
import com.example.expensetracker.common.Resource
import com.example.expensetracker.data.model.expense.ExpenseItem
import com.example.expensetracker.data.model.expense.ExpenseScreenState
import com.example.expensetracker.presentation.components.AppHeader
import com.example.expensetracker.presentation.components.expense.ExpenseContent
import com.example.expensetracker.presentation.screens.expense.actions.ExpenseActions
import com.example.expensetracker.presentation.screens.expense.actions.ExpenseEffectActions
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExpensesScreen(
    navController: NavController,
    viewModel: ExpenseViewModel = koinViewModel()
) {
    val expenses by viewModel.expenses.collectAsState()
    val getExpensesState by viewModel.getExpensesState.collectAsState()
    val expenseState by viewModel.expenseState.collectAsState()
    val deleteExpenseState by viewModel.deleteExpenseState.collectAsState()
    var editingExpenseId by remember {
        mutableStateOf<String?>(value = null)
    }
    var addingExpense by remember {
        mutableStateOf(value = false)
    }

    ExpensesScreenEffectHandler(
        getExpensesState = getExpensesState,
        expenseState = expenseState,
        deleteExpenseState = deleteExpenseState,
        actions = ExpenseEffectActions(
            onResetGetExpensesState = viewModel::resetGetExpensesState,
            onResetExpenseState = viewModel::resetExpenseState,
            onResetDeleteExpenseState = viewModel::resetDeleteExpenseState,
            onEditComplete = { editingExpenseId = null },
            onAddComplete = { addingExpense = false }
        )
    )

    Scaffold(
        topBar = {
            AppHeader(
                title = stringResource(
                    id = R.string.label_expenses
                ),
                showBackButton = true,
                navController = navController
            )
        }
    ) { innerPadding ->
        ExpenseContent(
            modifier = Modifier.padding(paddingValues = innerPadding),
            state = ExpenseScreenState(
                expenses = expenses,
                getExpensesState = getExpensesState,
                addingExpense = addingExpense,
                editingExpenseId = editingExpenseId
            ),
            actions = ExpenseActions(
                onAddExpenseClick = { addingExpense = true },
                onCancelAdd = { addingExpense = false },
                onSaveNewExpense = { newExpense ->
                    viewModel.addExpense(expense = newExpense)
                },
                onEditClick = { id ->
                    editingExpenseId = id
                },
                onCancelEdit = {
                    editingExpenseId = null
                },
                onSaveEdit = { updatedExpense ->
                    viewModel.updateExpense(
                        expense = updatedExpense
                    )
                },
                onDeleteClick = { id ->
                    viewModel.deleteExpense(
                        id = id
                    )
                }
            )
        )
    }
}

@Composable
private fun ExpensesScreenEffectHandler(
    getExpensesState: Resource<List<ExpenseItem>>?,
    expenseState: Resource<ExpenseItem>?,
    deleteExpenseState: Resource<Unit>?,
    actions: ExpenseEffectActions
) {
    val context = LocalContext.current

    // Get expenses result
    LaunchedEffect(key1 = getExpensesState) {
        if (getExpensesState is Resource.Error) {
            val errorMessage = getExpensesState.message
            if (errorMessage != "User not logged in") {
                Toast.makeText(
                    context,
                    errorMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
            actions.onResetGetExpensesState()
        }
    }

    // Add and Update expense result
    LaunchedEffect(key1 = expenseState) {
        when (expenseState) {
            is Resource.Success -> {
                Toast.makeText(
                    context,
                    "Expense saved successfully",
                    Toast.LENGTH_SHORT
                ).show()
                actions.onEditComplete()
                actions.onAddComplete()
                actions.onResetExpenseState()
            }
            is Resource.Error -> {
                Toast.makeText(
                    context,
                    expenseState.message,
                    Toast.LENGTH_SHORT
                ).show()
                actions.onResetExpenseState()
            }
            else -> {}
        }
    }

    // Delete expense result
    LaunchedEffect(key1 = deleteExpenseState) {
        when (deleteExpenseState) {
            is Resource.Success -> {
                Toast.makeText(
                    context,
                    "Expense deleted successfully",
                    Toast.LENGTH_SHORT
                ).show()
                actions.onResetDeleteExpenseState()
            }
            is Resource.Error -> {
                Toast.makeText(
                    context,
                    deleteExpenseState.message,
                    Toast.LENGTH_SHORT
                ).show()
                actions.onResetDeleteExpenseState()
            }
            else -> {}
        }
    }
}
