package com.example.myapplicationint226

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// ==================================================
// STRUCTURED CONCURRENCY SCREEN
// ==================================================

@Composable
fun StructuredConcurrencyScreen() {

    var statusText by remember {
        mutableStateOf(
            "Ready to load user dashboard..."
        )
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    var activeJob by remember {
        mutableStateOf<Job?>(null)
    }

    // Coroutine scope connected to the Compose lifecycle
    val composableScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement =
            Arrangement.Center,

        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {

        Text(
            text = statusText,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        if (isLoading) {

            // Loading indicator
            CircularProgressIndicator()

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            // Cancel button
            Button(
                onClick = {

                    // Cancel parent coroutine
                    activeJob?.cancel()

                    isLoading = false

                    statusText =
                        "Operation canceled by user!"
                },

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                )
            ) {

                Text("Cancel All Dashboard Loading")
            }

        } else {

            // Fetch button
            Button(
                onClick = {

                    isLoading = true

                    statusText =
                        "Loading Dashboard Data..."

                    // Parent coroutine
                    activeJob = composableScope.launch {

                        try {

                            // Structured concurrency function
                            val dashboardResult =
                                fetchUserDashboardData()

                            statusText =
                                dashboardResult

                        } catch (e: Exception) {

                            statusText =
                                "Error: ${e.localizedMessage}"

                        } finally {

                            isLoading = false
                        }
                    }
                }
            ) {

                Text("Fetch User Dashboard")
            }
        }
    }
}

// ==================================================
// STRUCTURED CONCURRENCY FUNCTION
// ==================================================

suspend fun fetchUserDashboardData(): String =
    coroutineScope {

        // ------------------------------------------
        // TASK 1 - USER PROFILE
        // ------------------------------------------

        val profileDeferred =
            async(Dispatchers.IO) {

                fetchUserProfile()
            }

        // ------------------------------------------
        // TASK 2 - NOTIFICATIONS
        // ------------------------------------------

        val notificationsDeferred =
            async(Dispatchers.IO) {

                fetchUserNotifications()
            }

        // ------------------------------------------
        // WAIT FOR BOTH TASKS
        // ------------------------------------------

        val profile =
            profileDeferred.await()

        val notifications =
            notificationsDeferred.await()

        // ------------------------------------------
        // FINAL RESULT
        // ------------------------------------------

        "Dashboard Loaded:\n$profile\n$notifications"
    }

// ==================================================
// USER PROFILE
// ==================================================

private suspend fun fetchUserProfile(): String {

    // Simulate network request
    delay(2500L)

    return "Profile: Arsalan (Assistant Professor)"
}

// ==================================================
// USER NOTIFICATIONS
// ==================================================

private suspend fun fetchUserNotifications(): String {

    // Simulate network request
    delay(1500L)

    return "Notifications: 3 Unread Assignments"
}

// ==================================================
// PREVIEW
// ==================================================

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Structured Concurrency Preview"
)
@Composable
fun StructuredConcurrencyScreenPreview() {

    MaterialTheme {

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {

            StructuredConcurrencyScreen()
        }
    }
}