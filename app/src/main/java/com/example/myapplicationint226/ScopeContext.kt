package com.example.myapplicationint226

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// ==================================================
// SCOPE & CONTEXT SCREEN
// ==================================================

@Composable
fun ScopeContextScreen() {

    var logs by remember {
        mutableStateOf("System Ready...\n")
    }

    var activeJob by remember {
        mutableStateOf<Job?>(null)
    }

    // Compose lifecycle-bound coroutine scope
    val composableScope = rememberCoroutineScope()

    // ==================================================
    // COROUTINE EXCEPTION HANDLER
    // ==================================================

    val exceptionHandler =
        CoroutineExceptionHandler { _, throwable ->

            logs +=
                "CAUGHT ERROR: ${throwable.localizedMessage}\n"
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement =
            Arrangement.Center,

        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {

        // ==================================================
        // LOG DISPLAY
        // ==================================================

        Text(
            text = logs,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        // ==================================================
        // START PARENT + CHILD COROUTINES
        // ==================================================

        Button(
            onClick = {

                logs =
                    "Launching Parent Scope...\n"

                // Combine CoroutineContext elements
                val customContext =
                    Dispatchers.Main + exceptionHandler

                // Parent coroutine
                activeJob =
                    composableScope.launch(customContext) {

                        logs +=
                            "Parent Job Started on: " +
                                    "${Thread.currentThread().name}\n"

                        // ==========================================
                        // CHILD COROUTINE 1
                        // ==========================================

                        launch(Dispatchers.IO) {

                            withContext(Dispatchers.Main) {
                                logs +=
                                    " -> Child 1 (IO) running " +
                                            "heavy task...\n"
                            }

                            delay(2000L)

                            withContext(Dispatchers.Main) {
                                logs +=
                                    " -> Child 1 Completed successfully!\n"
                            }
                        }

                        // ==========================================
                        // CHILD COROUTINE 2
                        // ==========================================

                        launch(Dispatchers.Default) {

                            withContext(Dispatchers.Main) {
                                logs +=
                                    " -> Child 2 (Default) " +
                                            "processing data...\n"
                            }

                            delay(4000L)

                            withContext(Dispatchers.Main) {
                                logs +=
                                    " -> Child 2 Completed successfully!\n"
                            }
                        }
                    }
            }
        ) {

            Text(
                text =
                    "Start Parent & Child Coroutines"
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        // ==================================================
        // CANCEL PARENT
        // ==================================================

        Button(
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                ),

            onClick = {

                // Cancel parent
                // automatically cancels children
                activeJob?.cancel()

                logs +=
                    "STOPPED: Parent Job Canceled by User!\n"
            }
        ) {

            Text(
                text = "Cancel Parent Scope"
            )
        }
    }
}

// ==================================================
// PREVIEW
// ==================================================

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Scope Context Preview"
)
@Composable
fun ScopeContextScreenPreview() {

    MaterialTheme {

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {

            ScopeContextScreen()
        }
    }
}