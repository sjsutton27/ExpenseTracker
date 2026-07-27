package com.example.expensetracker.presentation.components.expense

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.expensetracker.common.Constants
import com.example.expensetracker.common.formatDate
import com.example.expensetracker.data.model.expense.ExpenseItem

@Composable
fun ExpenseViewCard(
    expense: ExpenseItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = expense.category.color.copy(alpha = 0.1f)
        )
    ) {
        Column(
            modifier = Modifier.padding(all = 16.dp)
        ) {
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
                        contentDescription = expense.merchant,
                        modifier = Modifier.size(size = 40.dp)
                    )
                    Spacer(modifier = Modifier.width(width = 8.dp))
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
                Spacer(modifier = Modifier.width(width = 8.dp))
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
