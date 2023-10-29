package za.ac.cput.expensetracker.ui.expense

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import za.ac.cput.expensetracker.local.entities.ExpenseEntity

@Composable
fun ExpenseDetailsScreen(
    navController: NavController,
    viewModel: ExpenseViewModel = viewModel(),
    expenseId: Int
) {
    val expenseDetails by viewModel.getExpenseDetails(expenseId).collectAsState()
    val budgetRemaining by viewModel.budgetRemaining.collectAsState()
    val scaffoldState = rememberScaffoldState()
    var isEditScreenVisible by remember { mutableStateOf(false) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ScreenTitle(navController = navController)
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                ExpenseDetailsContent(
                    expenseDetails = expenseDetails,
                    onEditIconClick = {
                        isEditScreenVisible = true
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                BudgetTracker(budgetRemaining = budgetRemaining)

                Spacer(modifier = Modifier.height(16.dp))

                if (isEditScreenVisible) {
                    EditExpenseScreen(
                        navController = navController,
                        viewModel = viewModel,
                        expenseId = expenseId,
                        onEditScreenDismiss = {
                            isEditScreenVisible = false
                        }
                    )
                }
            }
        }
    )
}

@Composable
fun EditExpenseScreen(
    navController: NavController,
    viewModel: ExpenseViewModel,
    expenseId: Int,
    onEditScreenDismiss: () -> Unit
) {
    val expenseDetails by viewModel.getExpenseDetails(expenseId).collectAsState()
    val expense = expenseDetails ?: return

}

@Composable
fun BudgetTracker(budgetRemaining: Double) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Budget Remaining",
            style = MaterialTheme.typography.subtitle1
        )
        Text(
            text = budgetRemaining.toString(),
            style = MaterialTheme.typography.h4
        )
    }
}

@Composable
fun ExpenseDetailsContent(expenseDetails: ExpenseEntity?, onEditIconClick: () -> Unit) {
    val expense = expenseDetails ?: return

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = expense.name,
            style = MaterialTheme.typography.h5
        )
        Text(
            text = expense.category,
            style = MaterialTheme.typography.body1
        )
        Text(
            text = expense.date,
            style = MaterialTheme.typography.body1
        )

        IconButton(
            onClick = onEditIconClick,
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit Expense"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTitle(navController: NavController) {
    TopAppBar(
        title = { Text(text = "Expense Details") },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        backgroundColor = MaterialTheme.colorScheme.primarySurface
    )
}