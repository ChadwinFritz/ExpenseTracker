package za.ac.cput.expensetracker.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import za.ac.cput.expensetracker.remote.ApiService

class BudgetRepository(apiService: ApiService) {
    private val budgetLiveData: MutableLiveData<Double> = MutableLiveData()

    init {
        // Initialize the initial budget value
        budgetLiveData.value = 0.0
    }

    fun getBudget(): LiveData<Double> {
        return budgetLiveData
    }

    fun updateBudget(newBudget: Double) {
        // Update the budget value and notify observers
        budgetLiveData.value = newBudget
    }

    fun updateBudget(expenses: List<Double>) {
        val currentBudget = budgetLiveData.value ?: 0.0
        val totalExpenses = expenses.sum()
        val updatedBudget = currentBudget - totalExpenses
        budgetLiveData.value = updatedBudget
    }
}