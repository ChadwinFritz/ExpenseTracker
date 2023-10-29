package za.ac.cput.expensetracker.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("expenses")
    suspend fun getExpenses(): List<ExpenseResponse>

    @POST("expenses")
    suspend fun addExpense(@Body expense: ExpenseRequest): ExpenseResponse

    @GET("budget")
    suspend fun getBudget(): Double

    @POST("budget")
    suspend fun updateBudget(@Body updatedBudgetRequest: ExpenseRequest)
}