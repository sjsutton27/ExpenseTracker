package com.example.expensetracker.presentation.screens.income

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
import com.example.expensetracker.data.model.income.IncomeItem
import com.example.expensetracker.data.model.income.IncomeScreenState
import com.example.expensetracker.presentation.components.AppHeader
import com.example.expensetracker.presentation.components.income.IncomeContent
import com.example.expensetracker.presentation.screens.income.actions.IncomeActions
import com.example.expensetracker.presentation.screens.income.actions.IncomeEffectActions
import org.koin.androidx.compose.koinViewModel

@Composable
fun IncomeScreen(
    navController: NavController,
    viewModel: IncomeViewModel = koinViewModel()
) {
    val incomes by viewModel.income.collectAsState()
    val getIncomeState by viewModel.getIncomeState.collectAsState()
    val incomeState by viewModel.incomeState.collectAsState()
    val deleteIncomeState by viewModel.deleteIncomeState.collectAsState()
    var editingIncomeId by remember {
        mutableStateOf<String?>(value = null)
    }
    var addingIncome by remember {
        mutableStateOf(value = false)
    }

    IncomeScreenEffectHandler(
        getIncomeState = getIncomeState,
        incomeState = incomeState,
        deleteIncomeState = deleteIncomeState,
        actions = IncomeEffectActions(
            onResetGetIncomeState = viewModel::resetGetIncomeState,
            onResetIncomeState = viewModel::resetIncomeState,
            onResetDeleteIncomeState = viewModel::resetDeleteIncomeState,
            onEditComplete = { editingIncomeId = null },
            onAddComplete = { addingIncome = false }
        )
    )

    Scaffold(
        topBar = {
            AppHeader(
                title = stringResource(
                    id = R.string.label_income
                ),
                showBackButton = true,
                navController = navController
            )
        }
    ) { innerPadding ->
        IncomeContent(
            modifier = Modifier.padding(paddingValues = innerPadding),
            state = IncomeScreenState(
                incomes = incomes,
                getIncomesState = getIncomeState,
                addingIncome = addingIncome,
                editingIncomeId = editingIncomeId
            ),
            actions = IncomeActions(
                onAddIncomeClick = { addingIncome = true },
                onCancelAdd = { addingIncome = false },
                onSaveNewIncome = { newIncome ->
                    viewModel.addIncome(income = newIncome)
                },
                onEditClick = { id ->
                    editingIncomeId = id
                },
                onCancelEdit = {
                    editingIncomeId = null
                },
                onSaveEdit = { updatedIncome ->
                    viewModel.updateIncome(
                        income = updatedIncome
                    )
                },
                onDeleteClick = { id ->
                    viewModel.deleteIncome(
                        id = id
                    )
                }
            )
        )
    }
}

@Composable
private fun IncomeScreenEffectHandler(
    getIncomeState: Resource<List<IncomeItem>>?,
    incomeState: Resource<IncomeItem>?,
    deleteIncomeState: Resource<Unit>?,
    actions: IncomeEffectActions
) {
    val context = LocalContext.current

    // Get income result
    LaunchedEffect(key1 = getIncomeState) {
        if (getIncomeState is Resource.Error) {
            val errorMessage = getIncomeState.message
            if (errorMessage != "User not logged in") {
                Toast.makeText(
                    context,
                    errorMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
            actions.onResetGetIncomeState()
        }
    }

    // Add and Update income result
    LaunchedEffect(key1 = incomeState) {
        when (incomeState) {
            is Resource.Success -> {
                Toast.makeText(
                    context,
                    "Income saved successfully",
                    Toast.LENGTH_SHORT
                ).show()
                actions.onEditComplete()
                actions.onAddComplete()
                actions.onResetIncomeState()
            }
            is Resource.Error -> {
                Toast.makeText(
                    context,
                    incomeState.message,
                    Toast.LENGTH_SHORT
                ).show()
                actions.onResetIncomeState()
            }
            else -> {}
        }
    }

    // Delete income result
    LaunchedEffect(key1 = deleteIncomeState) {
        when (deleteIncomeState) {
            is Resource.Success -> {
                Toast.makeText(
                    context,
                    "Income deleted successfully",
                    Toast.LENGTH_SHORT
                ).show()
                actions.onResetDeleteIncomeState()
            }
            is Resource.Error -> {
                Toast.makeText(
                    context,
                    deleteIncomeState.message,
                    Toast.LENGTH_SHORT
                ).show()
                actions.onResetDeleteIncomeState()
            }
            else -> {}
        }
    }
}
