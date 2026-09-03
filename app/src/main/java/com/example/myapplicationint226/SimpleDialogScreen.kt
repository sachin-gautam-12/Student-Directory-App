package com.example.myapplicationint226

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.ui.window.Dialog


// --------------------------------------------------
// ACTIVITY
// --------------------------------------------------

class SimpleDialogDemo : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                SimpleDialogScreen()
            }
        }
    }
}


// --------------------------------------------------
// MAIN SCREEN
// --------------------------------------------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleDialogScreen() {

    // Controls dialog visibility
    var isDialogOpen by remember {
        mutableStateOf(false)
    }


    Scaffold(

        topBar = {

            TopAppBar(
                title = {
                    Text(
                        text = "Basic Dialog Demo - CSE226"
                    )
                }
            )
        }

    ) { innerPadding ->

        Box(

            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),

            contentAlignment =
                Alignment.Center

        ) {

            // --------------------------------------------------
            // OPEN DIALOG BUTTON
            // --------------------------------------------------

            Button(

                onClick = {
                    isDialogOpen = true
                }

            ) {

                Text(
                    text = "Open Basic Dialog"
                )
            }


            // --------------------------------------------------
            // BASIC DIALOG
            // --------------------------------------------------

            if (isDialogOpen) {

                Dialog(

                    onDismissRequest = {
                        isDialogOpen = false
                    }

                ) {

                    Surface(

                        modifier =
                            Modifier.fillMaxWidth(0.85f),

                        shape =
                            RoundedCornerShape(16.dp),

                        color =
                            MaterialTheme.colorScheme.surface,

                        tonalElevation = 6.dp

                    ) {

                        Column(

                            modifier = Modifier
                                .padding(24.dp)
                                .fillMaxWidth(),

                            horizontalAlignment =
                                Alignment.CenterHorizontally

                        ) {

                            // --------------------------------------------------
                            // DIALOG TITLE
                            // --------------------------------------------------

                            Text(

                                text =
                                    "Simple Dialog",

                                fontSize = 20.sp,

                                fontWeight =
                                    FontWeight.Bold
                            )


                            Spacer(
                                modifier =
                                    Modifier.height(12.dp)
                            )


                            // --------------------------------------------------
                            // DIALOG MESSAGE
                            // --------------------------------------------------

                            Text(

                                text =
                                    "This is a basic raw Dialog " +
                                            "component in Jetpack Compose " +
                                            "without predefined slots.",

                                fontSize = 14.sp
                            )


                            Spacer(
                                modifier =
                                    Modifier.height(20.dp)
                            )


                            // --------------------------------------------------
                            // CLOSE BUTTON
                            // --------------------------------------------------

                            TextButton(

                                onClick = {
                                    isDialogOpen = false
                                },

                                modifier =
                                    Modifier.align(
                                        Alignment.End
                                    )

                            ) {

                                Text(
                                    text = "Close"
                                )
                            }
                        }
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
fun SimpleDialogPreview() {

    MaterialTheme {
        SimpleDialogScreen()
    }
}