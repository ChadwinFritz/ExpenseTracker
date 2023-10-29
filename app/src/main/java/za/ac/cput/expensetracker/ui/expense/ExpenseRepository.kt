package za.ac.cput.expensetracker.ui.expense

import androidx.lifecycle.LiveData
import za.ac.cput.expensetracker.db.ExpenseDao

class ExpenseRepository(private val expenseDao: ExpenseDao) {

    suspend fun insertExpense(expense: Expense) {
        expenseDao.insert(expense)
    }

    suspend fun updateExpense(expense: Expense) {
        expenseDao.update(expense)
    }

    suspend fun deleteExpense(expenseId: Int) {
        expenseDao.delete(expenseId)
    }

    fun getAllExpenses(): LiveData<List<Expense>> {
        return expenseDao.getAllExpenses()
    }

    fun getExpenseById(expenseId: Int): LiveData<Expense> {
        return expenseDao.getExpenseById(expenseId)
    }
}