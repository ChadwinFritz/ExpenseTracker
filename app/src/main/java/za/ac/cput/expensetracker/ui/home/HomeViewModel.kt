package za.ac.cput.expensetracker.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import za.ac.cput.expensetracker.local.dao.ExpenseDao
import za.ac.cput.expensetracker.local.entities.ExpenseEntity
import za.ac.cput.expensetracker.remote.NetworkRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val expenseDao: ExpenseDao,
    private val budgetRepository: NetworkRepository
) : ViewModel() {

    val expenses: LiveData<List<ExpenseEntity>> = expenseDao.getAllExpenses()
    val budget: LiveData<Double?> = budgetRepository.getBudget()

    // Function to fetch and update expenses from the network
    fun refreshExpenses() {
        viewModelScope.launch(Dispatchers.IO) {
            networkRepository.fetchExpenses()
        }
    }

    // Function to update the budget
    fun updateBudget(newBudget: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            budgetRepository.updateBudget(newBudget)
        }
    }
}