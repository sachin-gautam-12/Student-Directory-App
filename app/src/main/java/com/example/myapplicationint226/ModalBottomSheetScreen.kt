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

import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// --------------------------------------------------
// ACTIVITY
// --------------------------------------------------

class ModalBottomSheetDemo : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                PlaceScreen()
            }
        }
    }
}


// --------------------------------------------------
// MAIN SCREEN
// --------------------------------------------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceScreen() {

    // Bottom Sheet state
    val sheetState = rememberModalBottomSheetState()

    // Controls Bottom Sheet visibility
    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "LPU Place Finder"
                    )
                }
            )
        }

    ) { padding ->

        Box(

            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFE3F2FD),
                            Color.White
                        )
                    )
                )

        ) {

            Column(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),

                horizontalAlignment =
                    Alignment.CenterHorizontally

            ) {

                Spacer(
                    modifier = Modifier.height(20.dp)
                )


                // --------------------------------------------------
                // LPU PLACE CARD
                // No external image required
                // --------------------------------------------------

                Card(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),

                    shape = RoundedCornerShape(20.dp),

                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    )

                ) {

                    Box(

                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xFF1976D2),
                                        Color(0xFF64B5F6)
                                    )
                                )
                            ),

                        contentAlignment =
                            Alignment.Center

                    ) {

                        Column(

                            horizontalAlignment =
                                Alignment.CenterHorizontally,

                            verticalArrangement =
                                Arrangement.Center

                        ) {

                            Text(
                                text = "🏫",
                                fontSize = 50.sp
                            )

                            Spacer(
                                modifier =
                                    Modifier.height(8.dp)
                            )

                            Text(
                                text = "LPU",
                                fontSize = 32.sp,
                                fontWeight =
                                    FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                }


                Spacer(
                    modifier = Modifier.height(25.dp)
                )


                // --------------------------------------------------
                // HEADING
                // --------------------------------------------------

                Text(
                    text = "Explore Nearby Places",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )


                // --------------------------------------------------
                // SUB HEADING
                // --------------------------------------------------

                Text(
                    text = "Tap below to view place details",
                    color = Color.Gray,
                    fontSize = 16.sp
                )

                Spacer(
                    modifier = Modifier.height(25.dp)
                )


                // --------------------------------------------------
                // OPEN BOTTOM SHEET
                // --------------------------------------------------

                Button(

                    onClick = {
                        showBottomSheet = true
                    }

                ) {

                    Text(
                        text = "Show Place Details"
                    )
                }
            }


            // --------------------------------------------------
            // MODAL BOTTOM SHEET
            // --------------------------------------------------

            if (showBottomSheet) {

                ModalBottomSheet(

                    onDismissRequest = {
                        showBottomSheet = false
                    },

                    sheetState = sheetState

                ) {

                    Column(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)

                    ) {

                        // --------------------------------------------------
                        // PLACE NAME
                        // --------------------------------------------------

                        Text(
                            text =
                                "📍 Lovely Professional University",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier =
                                Modifier.height(12.dp)
                        )


                        // --------------------------------------------------
                        // RATING
                        // --------------------------------------------------

                        Text(
                            text =
                                "⭐ Rating: 4.4 (25K Reviews)",
                            fontSize = 18.sp
                        )

                        Spacer(
                            modifier =
                                Modifier.height(8.dp)
                        )


                        // --------------------------------------------------
                        // LOCATION
                        // --------------------------------------------------

                        Text(
                            text =
                                "📍 Phagwara, Punjab",
                            fontSize = 18.sp
                        )

                        Spacer(
                            modifier =
                                Modifier.height(25.dp)
                        )


                        // --------------------------------------------------
                        // ACTION BUTTONS
                        // --------------------------------------------------

                        Row(

                            modifier =
                                Modifier.fillMaxWidth(),

                            horizontalArrangement =
                                Arrangement.SpaceEvenly

                        ) {

                            Button(
                                onClick = {
                                    // Directions action
                                }
                            ) {
                                Text("Directions")
                            }

                            Button(
                                onClick = {
                                    // Call action
                                }
                            ) {
                                Text("Call")
                            }
                        }

                        Spacer(
                            modifier =
                                Modifier.height(20.dp)
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
fun ModalBottomSheetScreenPreview() {

    MaterialTheme {
        PlaceScreen()
    }
}