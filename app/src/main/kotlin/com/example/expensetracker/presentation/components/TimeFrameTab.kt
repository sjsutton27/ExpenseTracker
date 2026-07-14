package com.example.expensetracker.presentation.components

import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun TimeFrameTab(
    modifier: Modifier = Modifier
) {
    val tabsItems = listOf("Week", "Month", "Year")

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    PrimaryTabRow(
        modifier = modifier,
        selectedTabIndex = selectedTabIndex
    ) {
        tabsItems.forEachIndexed { index, tabItem ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { selectedTabIndex = index },
                text = {
                    Text(tabItem)
                }
            )
        }
    }
}