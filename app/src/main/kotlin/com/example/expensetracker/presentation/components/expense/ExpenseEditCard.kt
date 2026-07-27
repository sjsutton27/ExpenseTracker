package com.example.expensetracker.presentation.components.expense

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.expensetracker.common.formatDate
import com.example.expensetracker.data.model.expense.ExpenseCategory
import com.example.expensetracker.data.model.expense.ExpenseFrequency
import com.example.expensetracker.data.model.expense.ExpenseItem
import com.example.expensetracker.presentation.components.expense.actions.BasicInfoActions
import com.example.expensetracker.presentation.components.expense.actions.DetailSelectorActions

@Composable
fun ExpenseEditCard(
    expense: ExpenseItem,
    onCancelEdit: () -> Unit,
    onSaveEdit: (ExpenseItem) -> Unit,
    modifier: Modifier = Modifier,
    isNewExpense: Boolean = false
) {
    var title by remember(key1 = expense.id) {
        mutableStateOf(value = expense.title)
    }
    var amount by remember(key1 = expense.id) {
        mutableStateOf(
            value = if (isNewExpense) {
                ""
            } else {
                "%.2f".format(expense.amount)
            }
        )
    }
    var merchant by remember(key1 = expense.id) {
        mutableStateOf(value = expense.merchant)
    }
    var category by remember(key1 = expense.id) {
        mutableStateOf(value = expense.category)
    }
    var frequency by remember(key1 = expense.id) {
        mutableStateOf(value = expense.frequency)
    }
    var date by remember(key1 = expense.id) {
        mutableLongStateOf(value = expense.date)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = category.color.copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier.padding(all = 16.dp)
        ) {
            BasicInfoFields(
                title = title,
                amount = amount,
                merchant = merchant,
                actions = BasicInfoActions(
                    onTitleChange = { title = it },
                    onAmountChange = { amount = it },
                    onMerchantChange = { merchant = it }
                )
            )

            Spacer(modifier = Modifier.size(size = 8.dp))

            ExpenseDetailSelectors(
                date = date,
                category = category,
                frequency = frequency,
                actions = DetailSelectorActions(
                    onDateChange = { date = it },
                    onCategoryChange = { category = it },
                    onFrequencyChange = { frequency = it }
                )
            )

            Spacer(modifier = Modifier.size(size = 12.dp))

            EditActions(
                onCancelEdit = onCancelEdit,
                onSaveEdit = {
                    onSaveEdit(
                        expense.copy(
                            title = title,
                            amount = amount.toDoubleOrNull() ?: 0.00,
                            merchant = merchant,
                            category = category,
                            frequency = frequency,
                            date = date
                        )
                    )
                }
            )
        }
    }
}

@Composable
private fun BasicInfoFields(
    title: String,
    amount: String,
    merchant: String,
    actions: BasicInfoActions
) {
    OutlinedTextField(
        value = title,
        onValueChange = actions.onTitleChange,
        label = { Text(text = "Title") },
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.size(8.dp))
    OutlinedTextField(
        value = amount,
        onValueChange = { input ->
            if (input.matches(Regex(pattern = "^\\d*\\.?\\d{0,2}$"))) {
                actions.onAmountChange(input)
            }
        },
        placeholder = { Text(text = "0.00") },
        label = { Text(text = "Amount") }
    )
    Spacer(modifier = Modifier.size(size = 8.dp))
    OutlinedTextField(
        value = merchant,
        onValueChange = actions.onMerchantChange,
        label = { Text(text = "Merchant") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun ExpenseDetailSelectors(
    date: Long,
    category: ExpenseCategory,
    frequency: ExpenseFrequency,
    actions: DetailSelectorActions
) {
    var showDatePicker by remember { mutableStateOf(value = false) }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = date)

    OutlinedButton(onClick = { showDatePicker = true }) {
        Text(text = formatDate(timestamp = date))
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        actions.onDateChange(datePickerState.selectedDateMillis ?: date)
                        showDatePicker = false
                    }
                ) {
                    Text(text = "OK")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Spacer(modifier = Modifier.size(size = 8.dp))
    CategoryDropdown(
        selectedCategory = category,
        onCategorySelected = actions.onCategoryChange
    )
    Spacer(modifier = Modifier.size(size = 8.dp))
    FrequencyDropdown(
        selectedFrequency = frequency,
        onFrequencySelected = actions.onFrequencyChange
    )
}

@Composable
private fun EditActions(
    onCancelEdit: () -> Unit,
    onSaveEdit: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Button(onClick = onCancelEdit) {
            Text(text = "Cancel")
        }
        Spacer(modifier = Modifier.width(width = 8.dp))
        Button(onClick = onSaveEdit) {
            Text(text = "Save")
        }
    }
}
