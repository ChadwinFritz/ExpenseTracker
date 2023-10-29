package za.ac.cput.expensetracker.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import za.ac.cput.expensetracker.local.dao.ExpenseDao
import za.ac.cput.expensetracker.local.database.ExpenseDatabase
import za.ac.cput.expensetracker.remote.ApiService
import za.ac.cput.expensetracker.remote.NetworkRepository
import za.ac.cput.expensetracker.ui.home.BudgetRepository
import za.ac.cput.expensetracker.ui.home.HomeViewModel

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.expensetracker.ac.za/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun provideNetworkRepository(apiService: ApiService): NetworkRepository {
        return NetworkRepository(apiService)
    }

    @Provides
    fun provideExpenseDao(@ApplicationContext context: Context): ExpenseDao {
        return ExpenseDatabase.getInstance(context).expenseDao()
    }

    @Provides
    fun provideBudgetRepository(apiService: ApiService): BudgetRepository {
        return BudgetRepository(apiService)
    }

    @Provides
    fun provideHomeViewModel(
        networkRepository: NetworkRepository,
        expenseDao: ExpenseDao,
        budgetRepository: NetworkRepository // Provide budgetRepository here
    ): HomeViewModel {
        return HomeViewModel(networkRepository, expenseDao, budgetRepository)
    }
}