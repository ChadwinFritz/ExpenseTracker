package za.ac.cput.expensetracker

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import za.ac.cput.expensetracker.util.BudgetManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val budgetTextView: TextView = findViewById(R.id.budgetTextView)

        // Set an initial budget
        BudgetManager.setBudget(1000.0)

        // Update the budgetTextView with the initial budget
        updateBudgetTextView(budgetTextView)
    }

    private fun updateBudgetTextView(budgetTextView: TextView) {
        // Get the current budget from BudgetManager
        val remainingBudget = BudgetManager.getBudget()

        // Update the TextView text with the remaining budget
        val budgetText = remainingBudget?.let { "Budget Remaining: $$it" } ?: "Budget Not Set"
        budgetTextView.text = budgetText
    }
}