package com.example.expensetracker.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.expensetracker.data.model.ExpenseCategory

@Composable
fun CategoryDropdown(
    selectedCategory: ExpenseCategory,
    onCategorySelected: (ExpenseCategory) -> Unit
) {

    var expanded by remember {
        mutableStateOf(false)
    }


    Box {
        OutlinedButton(
            onClick = {
                expanded = true
            }
        ) {
            Icon(
                imageVector = selectedCategory.icon,
                contentDescription = selectedCategory.displayName,
                tint = selectedCategory.color
            )
            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Text(
                text = selectedCategory.displayName
            )
        }


        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            ExpenseCategory.entries.forEach { category ->
                DropdownMenuItem(
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Icon(
                                imageVector = category.icon,
                                contentDescription = category.displayName,
                                tint = category.color
                            )

                            Spacer(
                                modifier = Modifier.width(8.dp)
                            )

                            Text(
                                text = category.displayName
                            )
                        }
                    },

                    onClick = {
                        onCategorySelected(category)
                        expanded = false
                    }
                )
            }
        }
    }
}