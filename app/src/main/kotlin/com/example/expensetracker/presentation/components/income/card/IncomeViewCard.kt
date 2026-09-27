package com.example.expensetracker.presentation.components.income.card

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
import com.example.expensetracker.common.formatDate
import com.example.expensetracker.data.model.income.IncomeItem

@Composable
fun IncomeViewCard(
    income: IncomeItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = income.category.color.copy(alpha = 0.1f)
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
                    Column {
                        Text(
                            text = income.title
                        )
                        Text(
                            text = "%.2f".format(income.amount)
                        )
                    }
                }
                Row {
                    IconButton(
                        onClick = onEditClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit Income"
                        )
                    }
                    IconButton(
                        onClick = onDeleteClick
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Income"
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = income.category.icon,
                    contentDescription = income.category.displayName,
                    tint = income.category.color
                )
                Spacer(modifier = Modifier.width(width = 8.dp))
                Text(
                    text = income.category.displayName,
                    color = income.category.color
                )
            }
            Text(
                text = "Frequency: ${income.frequency.name}"
            )
            Text(
                text = "Date: ${formatDate(timestamp = income.date)}"
            )
        }
    }
}