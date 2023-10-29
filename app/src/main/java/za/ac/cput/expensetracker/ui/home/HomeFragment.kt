package za.ac.cput.expensetracker.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.livedata.observeAsState
import za.ac.cput.expensetracker.local.entities.ExpenseEntity
import za.ac.cput.expensetracker.ui.common.ExpenseTrackerApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeFragment(
    navController: NavController,
    viewModel: HomeViewModel = viewModel(),
    scaffoldState: ScaffoldState
) {
    var budgetText by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    val budgetState by viewModel.budget.observeAsState()
    val expenses by viewModel.expenses.observeAsState()

    val budget = budgetState ?: 0.0

    // Function to update the budget
    fun updateBudget() {
        val newBudget = budgetText.toDoubleOrNull() ?: 0.0
        viewModel.updateBudget(newBudget)
        keyboardController?.hideSoftwareKeyboard()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Expense Tracker") })
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Card(
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Budget",
                            style = MaterialTheme.typography.body1
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        TextField(
                            value = budgetText,
                            onValueChange = { budgetText = it },
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            keyboardActions = KeyboardActions(onDone = { updateBudget() }),
                            textStyle = LocalTextStyle.current.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = MaterialTheme.typography.h4.fontSize
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = { updateBudget() },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text("Set Budget")
                        }
                    }
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp)
                ) {
                    items(expenses ?: emptyList()) { expense ->
                        ExpenseCard(expense = expense)
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseCard(expense: ExpenseEntity) {
    val formattedPrice = CurrencyFormatter.formatCurrency(expense.amount)
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        onClick = {
            // Handle item click
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = expense.title,
                style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Amount: $formattedPrice",
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Composable
fun HomeScreen(
    navController: NavController = rememberNavController(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    viewModel: HomeViewModel = viewModel()
) {
    ExpenseTrackerApplicationTheme {
        HomeFragment(navController, viewModel, scaffoldState)
    }
}