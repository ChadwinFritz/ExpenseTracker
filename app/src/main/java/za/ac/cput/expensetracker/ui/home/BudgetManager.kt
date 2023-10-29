package za.ac.cput.expensetracker.ui.home

object BudgetManager {
    private var budget: Double = 0.0

    fun getBudget(): Double {
        return budget
    }

    fun setBudget(value: Double) {
        budget = value
    }

    fun updateBudget(expenses: List<Double>) {
        val totalExpenses = expenses.sum()
        budget -= totalExpenses
    }
}