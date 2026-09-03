package com.example.myapplicationint226

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


@Composable
fun CombinedEffectScreen() {

    var showMainPage by remember {
        mutableStateOf(true)
    }

    if (showMainPage) {

        MainPageScreen(
            onNavigateToDetails = {
                showMainPage = false
            }
        )

    } else {

        DetailsPageScreen(
            onNavigateBack = {
                showMainPage = true
            }
        )
    }
}


@Composable
fun MainPageScreen(
    onNavigateToDetails: () -> Unit
) {

    var launchedEffectStatus by remember {
        mutableStateOf("Initializing...")
    }

    var disposableEffectStatus by remember {
        mutableStateOf("Listener Inactive")
    }


    // LaunchedEffect
    LaunchedEffect(Unit) {

        launchedEffectStatus =
            "LaunchedEffect: Task Running..."

        delay(4000)

        launchedEffectStatus =
            "LaunchedEffect: Task Completed! ✅"
    }


    // DisposableEffect
    DisposableEffect(Unit) {

        disposableEffectStatus =
            "DisposableEffect: Listener ACTIVE 🟢"

        onDispose {
            println("DisposableEffect: Listener destroyed 🔴")
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "📄 Main Page",
            fontSize = 24.sp
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = launchedEffectStatus,
                    fontSize = 16.sp
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = disposableEffectStatus,
                    fontSize = 16.sp
                )
            }
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Button(
            onClick = onNavigateToDetails
        ) {

            Text(
                text = "Navigate to Details Page ➔"
            )
        }
    }
}


@Composable
fun DetailsPageScreen(
    onNavigateBack: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "📄 Details Page",
            fontSize = 24.sp
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Text(
            text = "MainPage is now unmounted.",
            fontSize = 16.sp
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Button(
            onClick = onNavigateBack
        ) {

            Text(
                text = "⬅️ Return to Main Page"
            )
        }
    }
}


/*
 * Compose Preview
 */
@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Combined Effect Preview"
)
@Composable
fun CombinedEffectScreenPreview() {

    CombinedEffectScreen()
}