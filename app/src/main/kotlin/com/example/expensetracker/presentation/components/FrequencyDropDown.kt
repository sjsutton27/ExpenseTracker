package com.example.expensetracker.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.expensetracker.data.model.ExpenseFrequency

@Composable
fun FrequencyDropdown(
    selectedFrequency: ExpenseFrequency,
    onFrequencySelected: (ExpenseFrequency) -> Unit
) {
    var expanded by remember {
        mutableStateOf(value = false)
    }

    Box {
        OutlinedButton(
            onClick = {
                expanded = true
            }
        ) {
            Text(
                text = selectedFrequency.name
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            ExpenseFrequency.entries.forEach { frequency ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = frequency.name
                        )
                    },
                    onClick = {
                        onFrequencySelected(frequency)
                        expanded = false
                    }
                )
            }
        }
    }
}
