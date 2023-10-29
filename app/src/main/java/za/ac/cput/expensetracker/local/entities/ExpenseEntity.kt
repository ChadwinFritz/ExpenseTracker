package za.ac.cput.expensetracker.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val price: Double,
    val quantity: Int,
    val category: String,
    val amount: Double,
    val title: String,
    val date: String
) {

    // Custom logic for calculating total expense
    fun calculateTotalExpense(): Double {
        return price * quantity
    }
}