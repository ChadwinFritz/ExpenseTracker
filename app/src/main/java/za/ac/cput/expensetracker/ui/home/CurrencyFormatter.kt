package za.ac.cput.expensetracker.ui.home

import java.text.NumberFormat
import java.util.Locale

object CurrencyFormatter {
    fun formatCurrency(amount: Double): String {
        val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
        return numberFormat.format(amount)
    }
}