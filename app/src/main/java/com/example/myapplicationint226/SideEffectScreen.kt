package com.example.myapplicationint226

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// --------------------------------------------------
// EXTERNAL ANALYTICS SYSTEM
// --------------------------------------------------

object ExternalAnalyticsSystem {

    var loggedCounterValue: Int = 0
}


// --------------------------------------------------
// ACTIVITY
// --------------------------------------------------

class SideEffectDemo : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                SideEffectScreen()
            }
        }
    }
}


// --------------------------------------------------
// MAIN SCREEN
// --------------------------------------------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SideEffectScreen() {

    // Compose state
    var count by remember {
        mutableIntStateOf(0)
    }


    // --------------------------------------------------
    // SIDE EFFECT
    // --------------------------------------------------
    //
    // SideEffect runs after every successful
    // recomposition.
    //
    // It is useful for synchronizing Compose state
    // with an external non-Compose system.
    //

    SideEffect {

        ExternalAnalyticsSystem.loggedCounterValue =
            count

        println(
            "CSE226_LOG: [SideEffect] " +
                    "Recomposition successful! " +
                    "External value = " +
                    "${ExternalAnalyticsSystem.loggedCounterValue}"
        )
    }


    // --------------------------------------------------
    // UI
    // --------------------------------------------------

    Scaffold(

        topBar = {

            TopAppBar(
                title = {
                    Text(
                        text = "SideEffect Demo - CSE226"
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

            horizontalAlignment =
                Alignment.CenterHorizontally,

            verticalArrangement =
                Arrangement.Center

        ) {

            // --------------------------------------------------
            // TITLE
            // --------------------------------------------------

            Text(
                text =
                    "State Synchronization with SideEffect",

                fontSize = 20.sp,

                fontWeight =
                    FontWeight.Bold,

                textAlign =
                    TextAlign.Center
            )


            Spacer(
                modifier = Modifier.height(8.dp)
            )


            // --------------------------------------------------
            // DESCRIPTION
            // --------------------------------------------------

            Text(
                text =
                    "SideEffect runs after every successful " +
                            "recomposition to sync Compose state " +
                            "with an external non-Compose object.",

                fontSize = 14.sp,

                color =
                    MaterialTheme.colorScheme.secondary,

                textAlign =
                    TextAlign.Center
            )


            Spacer(
                modifier = Modifier.height(32.dp)
            )


            // --------------------------------------------------
            // STATE CARD
            // --------------------------------------------------

            Card(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),

                colors = CardDefaults.cardColors(
                    containerColor =
                        MaterialTheme.colorScheme.surfaceVariant
                )

            ) {

                Column(

                    modifier =
                        Modifier.fillMaxSize(),

                    horizontalAlignment =
                        Alignment.CenterHorizontally,

                    verticalArrangement =
                        Arrangement.Center

                ) {

                    Text(
                        text =
                            "Current State Value: $count",

                        fontSize = 18.sp,

                        fontWeight =
                            FontWeight.Bold
                    )


                    Spacer(
                        modifier =
                            Modifier.height(6.dp)
                    )


                    Text(
                        text =
                            "External System Value: " +
                                    "${ExternalAnalyticsSystem.loggedCounterValue}",

                        fontSize = 14.sp,

                        color =
                            MaterialTheme.colorScheme.primary
                    )
                }
            }


            Spacer(
                modifier = Modifier.height(32.dp)
            )


            // --------------------------------------------------
            // INCREMENT BUTTON
            // --------------------------------------------------

            Button(

                onClick = {
                    count++
                },

                modifier =
                    Modifier.fillMaxWidth(0.7f)

            ) {

                Text(
                    text = "Increment Counter (+1)"
                )
            }


            Spacer(
                modifier = Modifier.height(12.dp)
            )


            // --------------------------------------------------
            // RESET BUTTON
            // --------------------------------------------------

            Button(

                onClick = {
                    count = 0
                },

                modifier =
                    Modifier.fillMaxWidth(0.7f)

            ) {

                Text(
                    text = "Reset Counter"
                )
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
fun SideEffectPreview() {

    MaterialTheme {
        SideEffectScreen()
    }
}