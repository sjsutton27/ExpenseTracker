# Implement Income Screen

## Goal Description
Implement `IncomeScreen.kt` to make it fully functional and consistent with `ExpensesScreen.kt`, integrating `IncomeViewModel`, `IncomeContent`, `IncomeActions`, and `IncomeEffectActions`.

## User Review Required
> [!NOTE]
> The income components, actions, viewmodel, and model classes have already been implemented in the codebase. This plan wires them up inside `IncomeScreen.kt`.

## Open Questions
- None.

## Proposed Changes

### presentation/screens/income

#### [MODIFY] [IncomeScreen.kt](file:///C:/Users/ssutt/AndroidStudioProjects/ExpenseTracker/app/src/main/kotlin/com/example/expensetracker/presentation/screens/income/IncomeScreen.kt)
- Update `IncomeScreen` composable to accept `navController` and `viewModel: IncomeViewModel = koinViewModel()`.
- Collect flows (`income`, `getIncomeState`, `incomeState`, `deleteIncomeState`).
- Add local state for `editingIncomeId` and `addingIncome`.
- Implement `IncomeScreenEffectHandler` to handle Toasts for loading errors, save/update success/error, and delete success/error.
- Use `Scaffold`, `AppHeader`, and `IncomeContent` with `IncomeScreenState` and `IncomeActions` (handling adding, editing, saving new/edited income, and deleting).

## Verification Plan

### Automated Tests
- Run Gradle build (`app:assembleDebug`) to ensure no compilation errors.

### Manual Verification
- Deploy the app to an emulator/device and verify that navigating to the Income screen correctly displays income items, allows adding, editing, and deleting income entries.
