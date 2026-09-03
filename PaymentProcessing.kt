package com.example.myapplicationint226

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun PaymentProcessing() {

    var isProcessing by remember { mutableStateOf(false) }
    var status by remember { mutableStateOf("Ready to Pay") }
    var seconds by remember { mutableStateOf(0) }

    // Starts whenever isProcessing changes
    LaunchedEffect(isProcessing) {

        if (isProcessing) {

            status = "Verifying Payment..."
            seconds = 3

            // 3-second verification
            while (seconds > 0) {
                delay(1000)
                seconds--
            }

            // Runs only if coroutine was not cancelled
            status = "Transaction Complete"
            isProcessing = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "E-Commerce Checkout",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(20.dp))

        Text(
            text = status,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(20.dp))

        if (isProcessing) {

            // Progress indicator
            CircularProgressIndicator()

            Spacer(Modifier.height(16.dp))

            // Active timer
            Text("Verification: $seconds seconds")

            Spacer(Modifier.height(16.dp))

            // Cancel Payment
            Button(
                onClick = {
                    isProcessing = false
                    status = "Payment Cancelled"
                    seconds = 0
                }
            ) {
                Text("Cancel Payment")
            }

        } else {

            // Pay Now
            Button(
                onClick = {
                    isProcessing = true
                }
            ) {
                Text("Pay Now")
            }
        }
    }
}


// ---------------- PREVIEW ----------------

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Payment Processing"
)
@Composable
fun PaymentProcessingPreview() {

    MaterialTheme {
        PaymentProcessing()
    }
}