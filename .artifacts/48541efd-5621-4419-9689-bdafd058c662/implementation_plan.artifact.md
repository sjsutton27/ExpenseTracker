# Implement and Fix IncomeSection

Implement `IncomeSection.kt` to mirror `ExpenseSection.kt`, providing the UI component for adding and editing income items.

## User Review Required

> [!NOTE]
> `IncomeSection.kt` was previously left incomplete with only a function signature. This plan completes its implementation following the project's architectural patterns (similar to `ExpenseSection`).

## Proposed Changes

### presentation/components/income

#### [MODIFY] [IncomeSection.kt](file:///C:/Users/ssutt/AndroidStudioProjects/ExpenseTracker/app/src/main/kotlin/com/example/expensetracker/presentation/components/income/IncomeSection.kt)
- Complete `IncomeSection` composable with `addingIncome`, `onAddIncomeClick`, `onCancelEdit`, and `onSaveEdit` parameters.
- Render `IncomeCard` when `addingIncome` is true, or an add action button (`IconButton` with `MediumGreen` background) when false.

## Verification Plan

### Automated Tests
- Build project using `./gradlew app:assembleDebug` and run detekt to verify formatting and compliance.
