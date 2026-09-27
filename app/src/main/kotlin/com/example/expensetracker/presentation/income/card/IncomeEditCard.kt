package com.example.expensetracker.presentation.income.card

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
import com.example.expensetracker.data.model.Frequency
import com.example.expensetracker.data.model.income.IncomeCategory
import com.example.expensetracker.data.model.income.IncomeItem
import com.example.expensetracker.presentation.components.actions.BasicInfoActions
import com.example.expensetracker.presentation.components.drop_down.FrequencyDropdown
import com.example.expensetracker.presentation.income.actions.IncomeDetailSelectorActions
import com.example.expensetracker.presentation.income.drop_down.IncomeCategoryDropDown

@Composable
fun IncomeEditCard(
    income: IncomeItem,
    onCancelEdit: () -> Unit,
    onSaveEdit: (IncomeItem) -> Unit,
    modifier: Modifier = Modifier,
    isNewIncome: Boolean = false
){
    var title by remember(key1 = income.id) {
        mutableStateOf(value = income.title)
    }
    var amount by remember(key1 = income.id) {
        mutableStateOf(
            value = if (isNewIncome) {
                ""
            } else {
                "%.2f".format(income.amount)
            }
        )
    }
    var category by remember(key1 = income.id) {
        mutableStateOf(value = income.category)
    }
    var frequency by remember(key1 = income.id) {
        mutableStateOf(value = income.frequency)
    }
    var date by remember(key1 = income.id) {
        mutableLongStateOf(value = income.date)
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
                actions = BasicInfoActions(
                    onTitleChange = { title = it },
                    onAmountChange = { amount = it },

                    ),
            )

            Spacer(modifier = Modifier.size(size = 8.dp))

            IncomeDetailSelectors(
                date = date,
                category = category,
                frequency = frequency,
                actions = IncomeDetailSelectorActions(
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
                        income.copy(
                            title = title,
                            amount = amount.toDoubleOrNull() ?: 0.00,
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
}

@Composable
private fun IncomeDetailSelectors(
    date: Long,
    category: IncomeCategory,
    frequency: Frequency,
    actions: IncomeDetailSelectorActions
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
    IncomeCategoryDropDown(
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
