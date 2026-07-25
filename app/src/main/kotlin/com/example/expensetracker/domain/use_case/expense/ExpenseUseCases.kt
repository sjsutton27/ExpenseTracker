package com.example.expensetracker.domain.use_case.expense

class ExpenseUseCases (
    val addExpenseUseCase: AddExpenseUseCase,
    val updateExpenseUseCase: UpdateExpenseUseCase,
    val deleteExpenseUseCase: DeleteExpenseUseCase,
    val getExpensesUseCase: GetExpensesUseCase
)