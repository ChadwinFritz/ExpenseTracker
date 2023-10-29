package za.ac.cput.expensetracker.remote

data class ExpenseRequest(
    val name: String,
    val price: Double,
    val quantity: Int,
    val category: String
)