package com.example.expensetracker.presentation.screens.expenses

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.expensetracker.R
import com.example.expensetracker.common.Resource
import com.example.expensetracker.data.model.ExpenseItem
import com.example.expensetracker.presentation.components.AppHeader
import com.example.expensetracker.presentation.components.ExpenseCard
import com.example.expensetracker.ui.theme.MediumGreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun ExpensesScreen(
    navController: NavController,
    viewModel: ExpensesViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val expenses by viewModel.expenses.collectAsState()
    val getExpensesState by viewModel.getExpensesState.collectAsState()
    val expenseState by viewModel.expenseState.collectAsState()
    val deleteExpenseState by viewModel.deleteExpenseState.collectAsState()
    var editingExpenseId by remember {
        mutableStateOf<String?>(null)
    }
    var addingExpense by remember {
        mutableStateOf(false)
    }

    // Get expenses result
    LaunchedEffect(
        key1 = getExpensesState
    ) {
        if (getExpensesState is Resource.Error) {
            Toast.makeText(
                context,
                (getExpensesState as Resource.Error).message,
                Toast.LENGTH_SHORT
            ).show()
            viewModel.resetGetExpensesState()
        }
    }

    // Add and Update expense result
    LaunchedEffect(
        key1 = expenseState
    ) {
        when (expenseState) {
            is Resource.Success -> {
                Toast.makeText(
                    context,
                    "Expense saved successfully",
                    Toast.LENGTH_SHORT
                ).show()
                editingExpenseId = null
                addingExpense = false
                viewModel.resetExpenseState()
            }
            is Resource.Error -> {
                Toast.makeText(
                    context,
                    (expenseState as Resource.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.resetExpenseState()
            }
            else -> {}
        }
    }
    // Delete expense result
    LaunchedEffect(
        key1 = deleteExpenseState
    ) {
        when (deleteExpenseState) {
            is Resource.Success -> {
                Toast.makeText(
                    context,
                    "Expense deleted successfully",
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.resetDeleteExpenseState()
            }
            is Resource.Error -> {
                Toast.makeText(
                    context,
                    (deleteExpenseState as Resource.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
                viewModel.resetDeleteExpenseState()
            }
            else -> {}
        }
    }
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                if (getExpensesState is Resource.Loading) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                // Add Expense Card at the top
                if (getExpensesState !is Resource.Loading) {
                    item {
                        if (addingExpense) {
                            ExpenseCard(
                                expense = ExpenseItem(),
                                isEditing = true,
                                isNewExpense = true,
                                onEditClick = {},
                                onCancelEdit = {
                                    addingExpense = false
                                },
                                onSaveEdit = { newExpense ->
                                    viewModel.addExpense(
                                        expense = newExpense
                                    )
                                },
                                onDeleteClick = {}
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                IconButton(
                                    onClick = {
                                        addingExpense = true
                                    },
                                    modifier = Modifier
                                        .size(64.dp)
                                        .clip(CircleShape)
                                        .background(MediumGreen)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "Add Expense",
                                        tint = Color.White,
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .size(32.dp)
                                    )
                                }
                            }
                        }
                        Spacer(
                            modifier = Modifier.size(
                                size = 8.dp
                            )
                        )
                    }
                }

                items(
                    items = expenses.reversed(),
                    key = { expense ->
                        expense.id
                    }
                ) { expense ->
                    ExpenseCard(
                        expense = expense,
                        isEditing = editingExpenseId == expense.id,
                        onEditClick = {
                            editingExpenseId = expense.id
                        },
                        onCancelEdit = {
                            editingExpenseId = null
                        },
                        onSaveEdit = { updatedExpense ->
                            viewModel.updateExpense(
                                expense = updatedExpense
                            )
                        },
                        onDeleteClick = {
                            viewModel.deleteExpense(
                                id = expense.id
                            )
                        }
                    )
                }
            }
        }
    }
}
