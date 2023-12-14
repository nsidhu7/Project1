package com.mobile.newsapp.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Composable used to display an error message along with a retry button.
 *
 * @param error The error message to be displayed.
 * @param onRetry The lambda function to be executed on retry button click.
 * @param modifier Optional [Modifier] to apply to the [Column].
 */
@Composable
fun RetryContent(
    error: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
){
    // Column to vertically stack child elements
    Column(
        modifier = modifier
    ) {
        // Display the error message in red with a font size of 18sp
        Text(text = error, color = Color.Red, fontSize = 18.sp)

        // Spacer to add vertical space between elements
        Spacer(modifier = Modifier.height(8.dp))

        // Button to trigger the retry action, aligned to the center horizontally
        Button(
            onClick = onRetry,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Retry")
        }
    }
}
