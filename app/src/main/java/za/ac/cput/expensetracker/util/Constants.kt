package za.ac.cput.expensetracker.util

object Constants {
    const val DATABASE_NAME = "expense_database"
    const val BASE_URL = "https://api.expensetracker.ac.za/"
}

object BudgetManager {
    private var budget: Double? = null

    fun setBudget(amount: Double) {
        budget = amount
    }

    fun getBudget(): Double? {
        return budget
    }

    fun deductExpenseFromBudget(expenseAmount: Double) {
        budget?.let {
            budget = it - expenseAmount
        }
    }

    fun resetBudget() {
        budget = null
    }
}