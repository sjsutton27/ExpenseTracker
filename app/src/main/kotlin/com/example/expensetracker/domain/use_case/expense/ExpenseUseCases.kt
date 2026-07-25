package com.example.expensetracker.domain.use_case.expense

class ExpenseUseCases (
    val addExpenseUseCase: AddExpenseUseCase = AddExpenseUseCase(),
    val updateExpenseUseCase: UpdateExpenseUseCase = UpdateExpenseUseCase(),
    val deleteExpenseUseCase: DeleteExpenseUseCase = DeleteExpenseUseCase(),
    val getExpensesUseCase: GetExpensesUseCase = GetExpensesUseCase()
)