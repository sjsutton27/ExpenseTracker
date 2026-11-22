package com.example.expensetracker.presentation.components.income

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.expensetracker.data.model.income.IncomeItem
import com.example.expensetracker.presentation.components.income.actions.IncomeCardActions
import com.example.expensetracker.presentation.components.income.card.IncomeCard
import com.example.expensetracker.ui.theme.MediumGreen

@Composable
fun IncomeSection(
    addingIncome: Boolean,
    onAddIncomeClick: () -> Unit,
    onCancelEdit: () -> Unit,
    onSaveEdit: (IncomeItem) -> Unit
) {
    if (addingIncome) {
        IncomeCard(
            income = IncomeItem(),
            isEditing = true,
            isNewIncome = true,
            actions = IncomeCardActions(
                onEditClick = {},
                onCancelEdit = onCancelEdit,
                onSaveEdit = onSaveEdit,
                onDeleteClick = {}
            )
        )
    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = onAddIncomeClick,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(MediumGreen)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Income",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(32.dp)
                )
            }
        }
    }
}
