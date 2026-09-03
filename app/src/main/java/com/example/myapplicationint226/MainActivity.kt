@file:OptIn(ExperimentalMaterial3Api::class)

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

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.myapplicationint226.ui.theme.MyApplicationINT226Theme


// ==================================================
// MAIN ACTIVITY
// ==================================================

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            MyApplicationINT226Theme {

                MainNavigationScreen()
            }
        }
    }
}


// ==================================================
// MAIN NAVIGATION
// ==================================================

@Composable
fun MainNavigationScreen() {

    var selectedScreen by remember {
        mutableStateOf("Home")
    }

    when (selectedScreen) {

        // --------------------------------------------------
        // HOME
        // --------------------------------------------------

        "Home" -> {

            HomeScreen(
                onScreenSelected = { screen ->
                    selectedScreen = screen
                }
            )
        }


        // --------------------------------------------------
        // 1. COMBINED EFFECT
        // --------------------------------------------------

        "Combined Effect" -> {

            DemoScreenContainer(
                title = "Combined Effect",
                onBack = {
                    selectedScreen = "Home"
                }
            ) {
                CombinedEffectScreen()
            }
        }


        // --------------------------------------------------
        // 2. DIALOG DEMO
        // --------------------------------------------------

        "Dialog Demo" -> {

            DemoScreenContainer(
                title = "Dialog Demo",
                onBack = {
                    selectedScreen = "Home"
                }
            ) {
                DialogDemoScreen()
            }
        }


        // --------------------------------------------------
        // 3. DISPOSABLE EFFECT
        // --------------------------------------------------

        "Disposable Effect" -> {

            DemoScreenContainer(
                title = "Disposable Effect",
                onBack = {
                    selectedScreen = "Home"
                }
            ) {
                DisposableEffectScreen()
            }
        }


        // --------------------------------------------------
        // 4. LAUNCHED EFFECT
        // --------------------------------------------------

        "Launched Effect" -> {

            DemoScreenContainer(
                title = "Launched Effect",
                onBack = {
                    selectedScreen = "Home"
                }
            ) {
                LaunchedEffectScreen()
            }
        }


        // --------------------------------------------------
        // 5. MODAL BOTTOM SHEET
        // --------------------------------------------------

        "Modal Bottom Sheet" -> {

            DemoScreenContainer(
                title = "Modal Bottom Sheet",
                onBack = {
                    selectedScreen = "Home"
                }
            ) {
                PlaceScreen()
            }
        }


        // --------------------------------------------------
        // 6. PERSISTENT BOTTOM SHEET
        // --------------------------------------------------

        "Persistent Bottom Sheet" -> {

            DemoScreenContainer(
                title = "Persistent Bottom Sheet",
                onBack = {
                    selectedScreen = "Home"
                }
            ) {
                PersistentBottomSheetScreen()
            }
        }


        // --------------------------------------------------
        // 7. SIDE EFFECT
        // --------------------------------------------------

        "Side Effect" -> {

            DemoScreenContainer(
                title = "Side Effect",
                onBack = {
                    selectedScreen = "Home"
                }
            ) {
                SideEffectScreen()
            }
        }


        // --------------------------------------------------
        // 8. SIMPLE DIALOG
        // --------------------------------------------------

        "Simple Dialog" -> {

            DemoScreenContainer(
                title = "Simple Dialog",
                onBack = {
                    selectedScreen = "Home"
                }
            ) {
                SimpleDialogScreen()
            }
        }
    }
}


// ==================================================
// HOME SCREEN
// ==================================================

@Composable
fun HomeScreen(
    onScreenSelected: (String) -> Unit
) {

    Scaffold(

        topBar = {

            TopAppBar(
                title = {
                    Text(
                        text = "CSE226 Android Demos"
                    )
                }
            )
        }

    ) { innerPadding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
                .verticalScroll(
                    rememberScrollState()
                ),

            horizontalAlignment =
                Alignment.CenterHorizontally,

            verticalArrangement =
                Arrangement.spacedBy(12.dp)

        ) {

            // --------------------------------------------------
            // TITLE
            // --------------------------------------------------

            Text(
                text = "Jetpack Compose Demo App",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )


            Spacer(
                modifier = Modifier.height(8.dp)
            )


            // --------------------------------------------------
            // DESCRIPTION
            // --------------------------------------------------

            Text(
                text = "Select any topic to open its demo",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )


            Spacer(
                modifier = Modifier.height(12.dp)
            )


            // --------------------------------------------------
            // DEMO BUTTONS
            // --------------------------------------------------

            DemoButton(
                text = "1. Combined Effect",
                onClick = {
                    onScreenSelected("Combined Effect")
                }
            )


            DemoButton(
                text = "2. Dialog Demo",
                onClick = {
                    onScreenSelected("Dialog Demo")
                }
            )


            DemoButton(
                text = "3. DisposableEffect",
                onClick = {
                    onScreenSelected("Disposable Effect")
                }
            )


            DemoButton(
                text = "4. LaunchedEffect",
                onClick = {
                    onScreenSelected("Launched Effect")
                }
            )


            DemoButton(
                text = "5. Modal Bottom Sheet",
                onClick = {
                    onScreenSelected("Modal Bottom Sheet")
                }
            )


            DemoButton(
                text = "6. Persistent Bottom Sheet",
                onClick = {
                    onScreenSelected("Persistent Bottom Sheet")
                }
            )


            DemoButton(
                text = "7. SideEffect",
                onClick = {
                    onScreenSelected("Side Effect")
                }
            )


            DemoButton(
                text = "8. Simple Dialog",
                onClick = {
                    onScreenSelected("Simple Dialog")
                }
            )


            Spacer(
                modifier = Modifier.height(20.dp)
            )


            Text(
                text = "CSE226 • Jetpack Compose",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.outline,
                textAlign = TextAlign.Center
            )
        }
    }
}


// ==================================================
// COMMON DEMO BUTTON
// ==================================================

@Composable
fun DemoButton(
    text: String,
    onClick: () -> Unit
) {

    Button(

        onClick = onClick,

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)

    ) {

        Text(
            text = text,
            fontSize = 16.sp
        )
    }
}


// ==================================================
// DEMO SCREEN CONTAINER
// ==================================================

@Composable
fun DemoScreenContainer(
    title: String,
    onBack: () -> Unit,
    content: @Composable () -> Unit
) {

    Scaffold(

        topBar = {

            TopAppBar(
                title = {
                    Text(
                        text = title
                    )
                }
            )
        }

    ) { innerPadding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)

        ) {

            // --------------------------------------------------
            // BACK BUTTON
            // --------------------------------------------------

            Button(

                onClick = onBack,

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    )

            ) {

                Text(
                    text = "← Back to Main Menu"
                )
            }


            // --------------------------------------------------
            // SELECTED SCREEN
            // --------------------------------------------------

            content()
        }
    }
}


// ==================================================
// PREVIEW
// ==================================================

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "CSE226 Main Dashboard"
)
@Composable
fun MainNavigationScreenPreview() {

    MyApplicationINT226Theme {

        MainNavigationScreen()
    }
}