package com.example.expensetracker.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.expensetracker.common.Constants
import com.example.expensetracker.common.formatDate
import com.example.expensetracker.data.model.ExpenseItem

@Composable
@Suppress("LongParameterList", "LongMethod")
fun ExpenseCard(
    expense: ExpenseItem,
    isEditing: Boolean,
    onEditClick: () -> Unit,
    onCancelEdit: () -> Unit,
    onSaveEdit: (ExpenseItem) -> Unit,
    onDeleteClick: () -> Unit,
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
    var showDatePicker by remember {
        mutableStateOf(value = false)
    }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = date
    )
    val backgroundColor = (if (isEditing) category else expense.category).color.copy(alpha = 0.1f)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Column(
            modifier = Modifier.padding(all = 16.dp)
        ) {
            if (isEditing) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { input ->
                        title = input
                    },
                    label = {
                        Text(text = "Title")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.size(8.dp))
                OutlinedTextField(
                    value = amount,
                    onValueChange = { input ->
                        if (input.matches(Regex(pattern = "^\\d*\\.?\\d{0,2}$"))) {
                            amount = input
                        }
                    },
                    placeholder = {
                        Text(text = "0.00")
                    },
                    label = {
                        Text(text = "Amount")
                    }
                )
                Spacer(modifier = Modifier.size(size = 8.dp))
                OutlinedTextField(
                    value = merchant,
                    onValueChange = { input ->
                        merchant = input
                    },
                    label = {
                        Text(text = "Merchant")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.size(size = 8.dp))
                // Date
                OutlinedButton(
                    onClick = {
                        showDatePicker = true
                    }
                ) {
                    Text(
                        text = formatDate(timestamp = date)
                    )
                }
                if (showDatePicker) {
                    DatePickerDialog(
                        onDismissRequest = {
                            showDatePicker = false
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    date =
                                        datePickerState.selectedDateMillis
                                            ?: date
                                    showDatePicker = false
                                }
                            ) {
                                Text(text = "OK")
                            }
                        }
                    ) {
                        DatePicker(
                            state = datePickerState
                        )
                    }
                }
                Spacer(modifier = Modifier.size(size = 8.dp))
                CategoryDropdown(
                    selectedCategory = category,
                    onCategorySelected = { categorySelected ->
                        category = categorySelected
                    }
                )
                Spacer(modifier = Modifier.size(size = 8.dp))
                FrequencyDropdown(
                    selectedFrequency = frequency,
                    onFrequencySelected = { frequencySelected ->
                        frequency = frequencySelected
                    }
                )
                Spacer(modifier = Modifier.size(size = 12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = onCancelEdit
                    ) {
                        Text(text = "Cancel")
                    }
                    Spacer(modifier = Modifier.width(width = 8.dp))
                    Button(
                        onClick = {
                            onSaveEdit(
                                expense.copy(
                                    title = title,
                                    amount = amount.toDoubleOrNull()
                                        ?: 0.00,
                                    merchant = merchant,
                                    category = category,
                                    frequency = frequency,
                                    date = date
                                )
                            )
                        }
                    ) {
                        Text(text = "Save")
                    }
                }
            } else {
                // VIEW MODE
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = if (expense.merchantDomain.isBlank()) {
                                Constants.DEFAULT_LOGO
                            } else {
                                "https://img.logo.dev/${expense.merchantDomain}?token=${Constants.API_KEY}"
                            },
                            contentDescription = expense.merchant
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = expense.title
                            )
                            Text(
                                text = "%.2f".format(expense.amount)
                            )
                        }
                    }
                    Row {
                        IconButton(
                            onClick = onEditClick
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit Expense"
                            )
                        }
                        IconButton(
                            onClick = onDeleteClick
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete Expense"
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "Merchant: ${
                        expense.merchant.lowercase().replaceFirstChar { firstChar ->
                            firstChar.uppercase()
                        }
                    }"
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = expense.category.icon,
                        contentDescription = expense.category.displayName,
                        tint = expense.category.color
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = expense.category.displayName,
                        color = expense.category.color
                    )
                }
                Text(
                    text = "Frequency: ${expense.frequency.name}"
                )
                Text(
                    text = "Date: ${formatDate(timestamp = expense.date)}"
                )
            }
        }
    }
}
