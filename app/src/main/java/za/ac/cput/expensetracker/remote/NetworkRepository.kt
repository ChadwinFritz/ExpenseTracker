package za.ac.cput.expensetracker.remote

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkRepository(private val apiService: ApiService) {

    suspend fun getExpenses(): List<ExpenseResponse> =
        apiService.getExpenses()

    suspend fun addExpense(expense: ExpenseRequest): ExpenseResponse =
        apiService.addExpense(expense)

    fun getBudget(): MutableLiveData<Double?> {
        val budgetLiveData = MutableLiveData<Double?>()

        // Simulating network request
        apiService.getBudget().enqueue(object : Callback<Double> {
            override fun onResponse(call: Call<Double>, response: Response<Double>) {
                if (response.isSuccessful) {
                    val budget = response.body()
                    budgetLiveData.postValue(budget)
                } else {
                    // Handle error case
                }
            }

            override fun onFailure(call: Call<Double>, t: Throwable) {
                // Handle failure case
            }
        })

        return budgetLiveData
    }

    suspend fun fetchExpenses() {
        // Simulating network request
        apiService.getExpenses().enqueue(object : Callback<List<ExpenseResponse>> {
            override fun onResponse(
                call: Call<List<ExpenseResponse>>,
                response: Response<List<ExpenseResponse>>
            ) {
                if (response.isSuccessful) {
                    val expenses = response.body()
                    // Handle fetched expenses
                } else {
                    // Handle error case
                }
            }

            override fun onFailure(call: Call<List<ExpenseResponse>>, t: Throwable) {
                // Handle failure case
            }
        })
    }

    fun updateBudget(newBudget: Double) {
        val updatedBudgetRequest = ExpenseRequest(budget = newBudget)

        // Simulating network request
        apiService.updateBudget(updatedBudgetRequest).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    // Handle successful update
                } else {
                    // Handle error case
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                // Handle failure case
            }
        })
    }
}