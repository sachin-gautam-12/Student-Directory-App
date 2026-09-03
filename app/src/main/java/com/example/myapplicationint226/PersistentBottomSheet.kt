package com.example.myapplicationint226

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import kotlinx.coroutines.launch


// --------------------------------------------------
// ACTIVITY
// --------------------------------------------------

class PersistentBottomSheetDemo : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                PersistentBottomSheetScreen()
            }
        }
    }
}


// --------------------------------------------------
// MAIN SCREEN
// --------------------------------------------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersistentBottomSheetScreen() {

    // Bottom Sheet Scaffold state
    val scaffoldState =
        rememberBottomSheetScaffoldState()

    // Coroutine scope for animations
    val coroutineScope =
        rememberCoroutineScope()


    BottomSheetScaffold(

        scaffoldState = scaffoldState,

        // Height of collapsed sheet
        sheetPeekHeight = 80.dp,

        // Rounded top corners
        sheetShape = RoundedCornerShape(
            topStart = 24.dp,
            topEnd = 24.dp
        ),

        // Sheet background
        sheetContainerColor =
            MaterialTheme.colorScheme.surfaceVariant,

        // --------------------------------------------------
        // PERSISTENT BOTTOM SHEET
        // --------------------------------------------------

        sheetContent = {

            Column(

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 10.dp
                    )

            ) {

                // --------------------------------------------------
                // MINI PLAYER / HEADER
                // --------------------------------------------------

                Row(

                    modifier =
                        Modifier.fillMaxWidth(),

                    verticalAlignment =
                        Alignment.CenterVertically,

                    horizontalArrangement =
                        Arrangement.SpaceBetween

                ) {

                    Column {

                        Text(
                            text =
                                "CSE226 - Android App Deployment",
                            fontSize = 16.sp,
                            fontWeight =
                                FontWeight.Bold
                        )

                        Text(
                            text =
                                "Lecture 01 • Live Streaming",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }

                    // Simple play button without icons dependency
                    Button(
                        onClick = {
                            // Play action
                        }
                    ) {
                        Text("▶ Play")
                    }
                }


                Spacer(
                    modifier = Modifier.height(12.dp)
                )


                // Divider
                HorizontalDivider()


                Spacer(
                    modifier = Modifier.height(12.dp)
                )


                // --------------------------------------------------
                // LECTURE NOTES
                // --------------------------------------------------

                Text(
                    text = "Lecture Notes & Outline",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )


                Text(
                    text =
                        "1. Introduction to Material Design 3\n" +
                                "2. Modal vs. Persistent Bottom Sheets\n" +
                                "3. Coroutine-driven State Animations\n" +
                                "4. Hands-on Classroom Demo",

                    fontSize = 14.sp,

                    lineHeight = 22.sp
                )


                Spacer(
                    modifier = Modifier.height(30.dp)
                )
            }
        }

    ) { innerPadding ->


        // --------------------------------------------------
        // MAIN SCREEN
        // --------------------------------------------------

        Box(

            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(
                    MaterialTheme.colorScheme.background
                ),

            contentAlignment =
                Alignment.Center

        ) {

            Column(

                horizontalAlignment =
                    Alignment.CenterHorizontally

            ) {

                Text(
                    text = "Main Screen View",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )


                Spacer(
                    modifier = Modifier.height(20.dp)
                )


                // --------------------------------------------------
                // CONTROL BUTTONS
                // --------------------------------------------------

                Row(

                    horizontalArrangement =
                        Arrangement.spacedBy(10.dp)

                ) {

                    // Expand Sheet
                    Button(

                        onClick = {

                            coroutineScope.launch {

                                scaffoldState
                                    .bottomSheetState
                                    .expand()
                            }
                        }

                    ) {

                        Text(
                            text = "Expand Sheet"
                        )
                    }


                    // Collapse Sheet
                    OutlinedButton(

                        onClick = {

                            coroutineScope.launch {

                                scaffoldState
                                    .bottomSheetState
                                    .partialExpand()
                            }
                        }

                    ) {

                        Text(
                            text = "Collapse Sheet"
                        )
                    }
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
fun PersistentBottomSheetScreenPreview() {

    MaterialTheme {
        PersistentBottomSheetScreen()
    }
}