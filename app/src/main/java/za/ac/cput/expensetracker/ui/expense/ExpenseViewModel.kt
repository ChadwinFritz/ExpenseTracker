package za.ac.cput.expensetracker.ui.expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import za.ac.cput.expensetracker.local.entities.ExpenseEntity

class ExpenseViewModel(private val repository: ExpenseRepository) : ViewModel() {

    private val _expenseDetails = MutableStateFlow<ExpenseEntity?>(null)
    val expenseDetails: StateFlow<ExpenseEntity?> = _expenseDetails

    private val _budgetRemaining = MutableStateFlow(0.0)
    val budgetRemaining: StateFlow<Double> = _budgetRemaining

    fun getExpenseDetails(expenseId: Int) {
        viewModelScope.launch {
            val expense = repository.getExpenseById(expenseId)
            _expenseDetails.value = expense.value
        }
    }

    fun updateBudgetRemaining(budget: Double, expenses: List<ExpenseEntity>) {
        val totalExpenses = expenses.sumOf { it.amount }
        val remainingBudget = budget - totalExpenses
        _budgetRemaining.value = remainingBudget
    }
}