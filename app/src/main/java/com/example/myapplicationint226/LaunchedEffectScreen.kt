package com.example.myapplicationint226

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

// --------------------------------------------------
// ACTIVITY
// --------------------------------------------------

class LaunchedEffectDemo : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                LaunchedEffectScreen()
            }
        }
    }
}

// --------------------------------------------------
// MAIN SCREEN
// --------------------------------------------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LaunchedEffectScreen() {

    // Changing userId restarts LaunchedEffect
    var userId by remember {
        mutableIntStateOf(101)
    }

    // Stores fetched user information
    var userDetails by remember {
        mutableStateOf("")
    }

    // Loading state
    var isLoading by remember {
        mutableStateOf(false)
    }

    // --------------------------------------------------
    // LAUNCHED EFFECT
    // --------------------------------------------------

    LaunchedEffect(userId) {

        isLoading = true
        userDetails = ""

        // Simulate network request
        delay(2000)

        userDetails =
            "User Data for ID: #$userId loaded successfully!"

        isLoading = false
    }

    // --------------------------------------------------
    // UI
    // --------------------------------------------------

    Scaffold(

        topBar = {

            TopAppBar(
                title = {
                    Text(
                        text = "LaunchedEffect Demo - CSE226"
                    )
                }
            )
        }

    ) { innerPadding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),

            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.Center

        ) {

            Text(
                text = "Side Effects in Compose",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Current Target User ID: $userId",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.secondary
            )

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            // --------------------------------------------------
            // RESULT CARD
            // --------------------------------------------------

            Card(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),

                colors = CardDefaults.cardColors(
                    containerColor =
                        MaterialTheme.colorScheme.surfaceVariant
                )

            ) {

                Box(

                    modifier = Modifier.fillMaxSize(),

                    contentAlignment = Alignment.Center

                ) {

                    if (isLoading) {

                        Column(
                            horizontalAlignment =
                                Alignment.CenterHorizontally
                        ) {

                            CircularProgressIndicator()

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )

                            Text(
                                text = "Fetching from server...",
                                fontSize = 12.sp
                            )
                        }

                    } else {

                        Text(
                            text = userDetails,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            // --------------------------------------------------
            // USER BUTTONS
            // --------------------------------------------------

            Row(

                horizontalArrangement =
                    Arrangement.spacedBy(16.dp)

            ) {

                Button(
                    onClick = {
                        userId = 101
                    }
                ) {

                    Text(
                        text = "Load User 101"
                    )
                }

                Button(
                    onClick = {
                        userId = 102
                    }
                ) {

                    Text(
                        text = "Load User 102"
                    )
                }
            }
        }
    }
}

// --------------------------------------------------
// PREVIEW
// --------------------------------------------------

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LaunchedEffectDemoPreview() {

    MaterialTheme {
        LaunchedEffectScreen()
    }
}