package com.example.myapplicationint226

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout

class AdvancedCoroutinesActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AdvancedCoroutinesScreen()
                }
            }
        }
    }
}

@Composable
fun AdvancedCoroutinesScreen() {

    var statusLog by remember {
        mutableStateOf("System Ready")
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    var currentJob by remember {
        mutableStateOf<Job?>(null)
    }

    val scope = rememberCoroutineScope()

    // Coroutine Exception Handler
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        statusLog = "ERROR: ${throwable.localizedMessage}"
        isLoading = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = statusLog,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (isLoading) {

            CircularProgressIndicator()

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    currentJob?.cancel()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                )
            ) {
                Text("Cancel Task")
            }

        } else {

            // Successful Task
            Button(
                onClick = {

                    isLoading = true

                    currentJob = scope.launch(exceptionHandler) {

                        try {

                            statusLog = fetchRemoteDataWithTimeout(
                                executionTimeMs = 1000L,
                                timeoutMs = 2000L
                            )

                        } catch (e: CancellationException) {

                            statusLog = "TASK CANCELED CLEANLY"

                        } finally {

                            isLoading = false
                        }
                    }
                }
            ) {
                Text("Run Successful Task")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Timeout Task
            Button(
                onClick = {

                    isLoading = true

                    currentJob = scope.launch(exceptionHandler) {

                        try {

                            statusLog = fetchRemoteDataWithTimeout(
                                executionTimeMs = 3000L,
                                timeoutMs = 1500L
                            )

                        } catch (e: Exception) {

                            statusLog =
                                "TIMEOUT: Operation took too long!"

                        } finally {

                            isLoading = false
                        }
                    }
                }
            ) {
                Text("Trigger Timeout")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Runtime Exception
            Button(
                onClick = {

                    isLoading = true

                    currentJob = scope.launch(exceptionHandler) {

                        try {

                            performFailingTask()

                        } catch (e: IllegalStateException) {

                            statusLog =
                                "LOCAL CATCH: ${e.message}"

                        } finally {

                            isLoading = false
                        }
                    }
                }
            ) {
                Text("Trigger Runtime Exception")
            }
        }
    }
}


// Suspend function with timeout
suspend fun fetchRemoteDataWithTimeout(
    executionTimeMs: Long,
    timeoutMs: Long
): String {

    return withContext(Dispatchers.IO) {

        withTimeout(timeoutMs) {

            delay(executionTimeMs)

            "SUCCESS: Data fetched in ${executionTimeMs}ms"
        }
    }
}


// Function that throws an exception
suspend fun performFailingTask(): Nothing {

    withContext(Dispatchers.Default) {

        delay(500L)

        throw IllegalStateException(
            "Server returned HTTP 500 Error"
        )
    }
}


// ==================== PREVIEW ====================

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Advanced Coroutines Screen"
)
@Composable
fun AdvancedCoroutinesScreenPreview() {

    MaterialTheme {

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {

            AdvancedCoroutinesScreen()
        }
    }
}