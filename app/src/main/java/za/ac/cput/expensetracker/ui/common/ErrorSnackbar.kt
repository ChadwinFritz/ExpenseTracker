package za.ac.cput.expensetracker.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import za.ac.cput.expensetracker.R

@Composable
fun ErrorSnackbar(
    modifier: Modifier = Modifier,
    errorMessage: String,
    onDismiss: () -> Unit
) {
    Snackbar(
        modifier = modifier,
        content = {
            Icon(
                imageVector = Icons.Filled.Error,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error
            )
            ErrorSnackbarContent(errorMessage)
        },
        action = {
            SnackbarActionButton(
                label = stringResource(id = R.string.dismiss),
                onClick = { onDismiss() } // Invoke the onDismiss lambda
            )
        }
    )
}

@Composable
private fun ErrorSnackbarContent(errorMessage: String) {
    Text(
        text = errorMessage,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colorScheme.error,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ErrorSnackbarPreview() {
    ErrorSnackbar(
        errorMessage = "Error occurred while fetching data.",
        onDismiss = {}
    )
}