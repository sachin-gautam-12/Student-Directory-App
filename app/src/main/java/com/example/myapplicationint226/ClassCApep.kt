@file:OptIn(
    androidx.compose.material3.ExperimentalMaterial3Api::class
)

package com.example.myapplicationint226

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding

import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.rememberModalBottomSheetState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import kotlinx.coroutines.delay


class ClassCApep : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            AdvancedComposeApp()
        }
    }
}


// ============================================================
// MAIN APPLICATION
// ============================================================

@Composable
fun AdvancedComposeApp() {

    // --------------------------------------------------------
    // APP STATE
    // --------------------------------------------------------

    var selectedTab by remember {
        mutableIntStateOf(0)
    }

    var counter by remember {
        mutableIntStateOf(0)
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    var animationVisible by remember {
        mutableStateOf(true)
    }

    var darkMode by remember {
        mutableStateOf(false)
    }

    var isLoading by remember {
        mutableStateOf(true)
    }


    // ========================================================
    // LAUNCHED EFFECT
    // ========================================================

    /*
       Runs when the screen starts.

       Here we simulate a 2-second loading process.
    */

    LaunchedEffect(Unit) {

        delay(2000L)

        isLoading = false
    }


    // ========================================================
    // DISPOSABLE EFFECT
    // ========================================================

    /*
       Setup happens when screen enters composition.

       Cleanup happens when screen leaves composition.
    */

    DisposableEffect(Unit) {

        println("Advanced Compose Screen Started")

        onDispose {

            println("Advanced Compose Screen Destroyed")
        }
    }


    // ========================================================
    // SIDE EFFECT
    // ========================================================

    /*
       Runs after every successful recomposition.
    */

    SideEffect {

        println("UI Recomposition Completed")
    }


    // ========================================================
    // MATERIAL 3 THEME
    // ========================================================

    MaterialTheme(

        colorScheme = if (darkMode) {

            darkColorScheme()

        } else {

            lightColorScheme()
        }

    ) {

        // ====================================================
        // SCAFFOLD
        // ====================================================

        Scaffold(

            // ------------------------------------------------
            // TOP APP BAR
            // ------------------------------------------------

            topBar = {

                TopAppBar(

                    title = {

                        Text(
                            text = "Advanced Compose UI"
                        )
                    },

                    actions = {

                        IconButton(

                            onClick = {

                                showDialog = true
                            }

                        ) {

                            Icon(

                                imageVector =
                                    Icons.Default.Info,

                                contentDescription =
                                    "Information"
                            )
                        }
                    }
                )
            },


            // ------------------------------------------------
            // BOTTOM NAVIGATION
            // ------------------------------------------------

            bottomBar = {

                NavigationBar {

                    NavigationBarItem(

                        selected =
                            selectedTab == 0,

                        onClick = {

                            selectedTab = 0
                        },

                        icon = {

                            Icon(

                                imageVector =
                                    Icons.Default.Home,

                                contentDescription =
                                    "Home"
                            )
                        },

                        label = {

                            Text(
                                text = "Home"
                            )
                        }
                    )


                    NavigationBarItem(

                        selected =
                            selectedTab == 1,

                        onClick = {

                            selectedTab = 1
                        },

                        icon = {

                            Icon(

                                imageVector =
                                    Icons.Default.Settings,

                                contentDescription =
                                    "Settings"
                            )
                        },

                        label = {

                            Text(
                                text = "Settings"
                            )
                        }
                    )
                }
            },


            // ------------------------------------------------
            // FLOATING ACTION BUTTON
            // ------------------------------------------------

            floatingActionButton = {

                FloatingActionButton(

                    onClick = {

                        counter++
                    }

                ) {

                    Icon(

                        imageVector =
                            Icons.Default.Add,

                        contentDescription =
                            "Increase Counter"
                    )
                }
            }

        ) { paddingValues ->


            // =================================================
            // HOME
            // =================================================

            if (selectedTab == 0) {

                HomeScreen(

                    paddingValues = paddingValues,

                    isLoading = isLoading,

                    counter = counter,

                    animationVisible =
                        animationVisible,

                    onIncrease = {

                        counter++
                    },

                    onAnimationToggle = {

                        animationVisible =
                            !animationVisible
                    },

                    onOpenBottomSheet = {

                        showBottomSheet = true
                    },

                    onOpenDialog = {

                        showDialog = true
                    }
                )

            } else {


                // =============================================
                // SETTINGS
                // =============================================

                SettingsScreen(

                    paddingValues =
                        paddingValues,

                    darkMode = darkMode,

                    onDarkModeChange = {

                        darkMode = it
                    }
                )
            }
        }


        // ====================================================
        // ALERT DIALOG
        // ====================================================

        if (showDialog) {

            AlertDialog(

                onDismissRequest = {

                    showDialog = false
                },

                icon = {

                    Icon(

                        imageVector =
                            Icons.Default.Info,

                        contentDescription = null
                    )
                },

                title = {

                    Text(
                        text = "Alert Dialog"
                    )
                },

                text = {

                    Text(
                        text =
                            "This is a Material 3 AlertDialog created using Jetpack Compose."
                    )
                },

                confirmButton = {

                    TextButton(

                        onClick = {

                            showDialog = false
                        }

                    ) {

                        Text(
                            text = "OK"
                        )
                    }
                },

                dismissButton = {

                    TextButton(

                        onClick = {

                            showDialog = false
                        }

                    ) {

                        Text(
                            text = "Cancel"
                        )
                    }
                }
            )
        }


        // ====================================================
        // MODAL BOTTOM SHEET
        // ====================================================

        if (showBottomSheet) {

            ModalBottomSheet(

                onDismissRequest = {

                    showBottomSheet = false
                },

                sheetState =
                    rememberModalBottomSheetState()

            ) {

                BottomSheetContent(

                    onClose = {

                        showBottomSheet = false
                    }
                )
            }
        }
    }
}


// ============================================================
// HOME SCREEN
// ============================================================

@Composable
fun HomeScreen(

    paddingValues: PaddingValues,

    isLoading: Boolean,

    counter: Int,

    animationVisible: Boolean,

    onIncrease: () -> Unit,

    onAnimationToggle: () -> Unit,

    onOpenBottomSheet: () -> Unit,

    onOpenDialog: () -> Unit

) {

    /*
       BoxWithConstraints is used for responsive UI.

       Instead of LocalConfiguration.screenWidthDp,
       we use the actual available width.
    */

    BoxWithConstraints(

        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {

        if (maxWidth < 600.dp) {

            // =================================================
            // PHONE UI
            // =================================================

            LazyColumn(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),

                verticalArrangement =
                    Arrangement.spacedBy(16.dp)

            ) {

                // ---------------------------------------------
                // WELCOME
                // ---------------------------------------------

                item {

                    if (isLoading) {

                        LoadingView()

                    } else {

                        WelcomeCard()
                    }
                }


                // ---------------------------------------------
                // COUNTER
                // ---------------------------------------------

                item {

                    CounterCard(

                        counter = counter,

                        onIncrease = onIncrease
                    )
                }


                // ---------------------------------------------
                // ANIMATION
                // ---------------------------------------------

                item {

                    AnimationCard(

                        visible =
                            animationVisible,

                        onToggle =
                            onAnimationToggle
                    )
                }


                // ---------------------------------------------
                // BOTTOM SHEET
                // ---------------------------------------------

                item {

                    Button(

                        modifier =
                            Modifier.fillMaxWidth(),

                        onClick =
                            onOpenBottomSheet

                    ) {

                        Text(
                            text =
                                "Open Bottom Sheet"
                        )
                    }
                }


                // ---------------------------------------------
                // DIALOG
                // ---------------------------------------------

                item {

                    Button(

                        modifier =
                            Modifier.fillMaxWidth(),

                        onClick =
                            onOpenDialog

                    ) {

                        Text(
                            text =
                                "Open Alert Dialog"
                        )
                    }
                }
            }


        } else {


            // =================================================
            // TABLET / LARGE SCREEN
            // =================================================

            Row(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),

                horizontalArrangement =
                    Arrangement.spacedBy(20.dp)
            ) {

                CounterCard(

                    modifier =
                        Modifier.weight(1f),

                    counter =
                        counter,

                    onIncrease =
                        onIncrease
                )


                AnimationCard(

                    modifier =
                        Modifier.weight(1f),

                    visible =
                        animationVisible,

                    onToggle =
                        onAnimationToggle
                )
            }
        }
    }
}


// ============================================================
// WELCOME CARD
// ============================================================

@Composable
fun WelcomeCard() {

    Card(

        modifier =
            Modifier.fillMaxWidth(),

        colors = CardDefaults.cardColors(

            containerColor =
                MaterialTheme
                    .colorScheme
                    .primaryContainer
        )

    ) {

        Column(

            modifier =
                Modifier.padding(20.dp)

        ) {

            Text(

                text =
                    "Welcome 👋",

                style =
                    MaterialTheme
                        .typography
                        .headlineSmall
            )


            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )


            Text(

                text =
                    "Advanced Jetpack Compose UI Demo"
            )
        }
    }
}


// ============================================================
// COUNTER CARD
// ============================================================

@Composable
fun CounterCard(

    modifier: Modifier = Modifier,

    counter: Int,

    onIncrease: () -> Unit

) {

    Card(

        modifier =
            modifier.fillMaxWidth(),

        shape =
            RoundedCornerShape(20.dp),

        elevation =
            CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )

    ) {

        Column(

            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),

            horizontalAlignment =
                Alignment.CenterHorizontally

        ) {

            Text(

                text =
                    "Counter",

                style =
                    MaterialTheme
                        .typography
                        .titleLarge
            )


            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )


            Text(

                text =
                    "$counter",

                fontSize =
                    40.sp
            )


            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )


            Button(

                onClick =
                    onIncrease

            ) {

                Text(
                    text =
                        "Increase"
                )
            }
        }
    }
}


// ============================================================
// ANIMATION CARD
// ============================================================

@Composable
fun AnimationCard(

    modifier: Modifier = Modifier,

    visible: Boolean,

    onToggle: () -> Unit

) {

    // ========================================================
    // animateFloatAsState
    // ========================================================

    val alpha by animateFloatAsState(

        targetValue =
            if (visible) 1f else 0f,

        animationSpec =
            tween(
                durationMillis = 700
            ),

        label =
            "Opacity Animation"
    )


    Card(

        modifier =
            modifier.fillMaxWidth()

    ) {

        Column(

            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),

            horizontalAlignment =
                Alignment.CenterHorizontally

        ) {

            Text(

                text =
                    "Compose Animations",

                style =
                    MaterialTheme
                        .typography
                        .titleLarge
            )


            Spacer(
                modifier =
                    Modifier.height(15.dp)
            )


            // =================================================
            // ANIMATED VISIBILITY
            // =================================================

            AnimatedVisibility(

                visible =
                    visible,

                enter =
                    fadeIn(
                        animationSpec =
                            tween(700)
                    ),

                exit =
                    fadeOut(
                        animationSpec =
                            tween(700)
                    )

            ) {

                Box(

                    modifier =
                        Modifier
                            .size(120.dp)
                            .alpha(alpha)
                            .background(

                                color =
                                    MaterialTheme
                                        .colorScheme
                                        .primary,

                                shape =
                                    RoundedCornerShape(20.dp)
                            ),

                    contentAlignment =
                        Alignment.Center

                ) {

                    Text(

                        text =
                            "Animated",

                        color =
                            MaterialTheme
                                .colorScheme
                                .onPrimary
                    )
                }
            }


            Spacer(
                modifier =
                    Modifier.height(15.dp)
            )


            // =================================================
            // ANIMATED CONTENT
            // =================================================

            AnimatedContent(

                targetState =
                    visible,

                label =
                    "Animated Content"

            ) { state ->

                Text(

                    text =
                        if (state) {

                            "Animation ON"

                        } else {

                            "Animation OFF"
                        }
                )
            }


            Spacer(
                modifier =
                    Modifier.height(10.dp)
            )


            // =================================================
            // CROSSFADE
            // =================================================

            Crossfade(

                targetState =
                    visible,

                label =
                    "Crossfade"

            ) { state ->

                Text(

                    text =
                        if (state) {

                            "Visible"

                        } else {

                            "Hidden"
                        }
                )
            }


            Spacer(
                modifier =
                    Modifier.height(15.dp)
            )


            Button(

                onClick =
                    onToggle

            ) {

                Text(

                    text =
                        if (visible) {

                            "Hide Animation"

                        } else {

                            "Show Animation"
                        }
                )
            }
        }
    }
}


// ============================================================
// BOTTOM SHEET CONTENT
// ============================================================

@Composable
fun BottomSheetContent(

    onClose: () -> Unit

) {

    Column(

        modifier =
            Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .windowInsetsPadding(
                    WindowInsets.navigationBars
                ),

        horizontalAlignment =
            Alignment.CenterHorizontally

    ) {

        Text(

            text =
                "Bottom Sheet",

            style =
                MaterialTheme
                    .typography
                    .headlineSmall
        )


        Spacer(
            modifier =
                Modifier.height(12.dp)
        )


        Text(

            text =
                "This is a Material 3 Modal Bottom Sheet."
        )


        Spacer(
            modifier =
                Modifier.height(20.dp)
        )


        Button(

            onClick =
                onClose

        ) {

            Text(
                text =
                    "Close"
            )
        }


        Spacer(
            modifier =
                Modifier.height(20.dp)
        )
    }
}


// ============================================================
// SETTINGS SCREEN
// ============================================================

@Composable
fun SettingsScreen(

    paddingValues: PaddingValues,

    darkMode: Boolean,

    onDarkModeChange:
        (Boolean) -> Unit

) {

    Column(

        modifier =
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp),

        horizontalAlignment =
            Alignment.CenterHorizontally

    ) {

        Text(

            text =
                "Settings",

            style =
                MaterialTheme
                    .typography
                    .headlineSmall
        )


        Spacer(
            modifier =
                Modifier.height(20.dp)
        )


        Card(

            modifier =
                Modifier.fillMaxWidth()

        ) {

            Row(

                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp),

                horizontalArrangement =
                    Arrangement.SpaceBetween,

                verticalAlignment =
                    Alignment.CenterVertically

            ) {

                Column {

                    Text(

                        text =
                            "Dark Mode",

                        style =
                            MaterialTheme
                                .typography
                                .titleMedium
                    )


                    Text(
                        text =
                            "Change application theme"
                    )
                }


                Switch(

                    checked =
                        darkMode,

                    onCheckedChange =
                        onDarkModeChange
                )
            }
        }
    }
}


// ============================================================
// LOADING VIEW
// ============================================================

@Composable
fun LoadingView() {

    Card(

        modifier =
            Modifier.fillMaxWidth()

    ) {

        Box(

            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),

            contentAlignment =
                Alignment.Center

        ) {

            Text(
                text =
                    "Loading..."
            )
        }
    }
}


// ============================================================
// PREVIEW
// ============================================================

@Preview(

    showBackground = true,

    showSystemUi = true,

    name =
        "Advanced Compose UI"
)
@Composable
fun AdvancedComposeAppPreview() {

    MaterialTheme {

        PreviewScreen()
    }
}


// ============================================================
// PREVIEW SCREEN
// ============================================================

@Composable
fun PreviewScreen() {

    Scaffold(

        topBar = {

            TopAppBar(

                title = {

                    Text(
                        text =
                            "Advanced Compose UI"
                    )
                },

                actions = {

                    IconButton(
                        onClick = {}
                    ) {

                        Icon(

                            imageVector =
                                Icons.Default.Info,

                            contentDescription =
                                "Information"
                        )
                    }
                }
            )
        },


        bottomBar = {

            NavigationBar {

                NavigationBarItem(

                    selected = true,

                    onClick = {},

                    icon = {

                        Icon(

                            imageVector =
                                Icons.Default.Home,

                            contentDescription =
                                "Home"
                        )
                    },

                    label = {

                        Text(
                            text =
                                "Home"
                        )
                    }
                )


                NavigationBarItem(

                    selected = false,

                    onClick = {},

                    icon = {

                        Icon(

                            imageVector =
                                Icons.Default.Settings,

                            contentDescription =
                                "Settings"
                        )
                    },

                    label = {

                        Text(
                            text =
                                "Settings"
                        )
                    }
                )
            }
        },


        floatingActionButton = {

            FloatingActionButton(

                onClick = {}

            ) {

                Icon(

                    imageVector =
                        Icons.Default.Add,

                    contentDescription =
                        "Add"
                )
            }
        }

    ) { paddingValues ->

        LazyColumn(

            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),

            verticalArrangement =
                Arrangement.spacedBy(16.dp)

        ) {


            // -----------------------------------------------
            // Welcome
            // -----------------------------------------------

            item {

                WelcomeCard()
            }


            // -----------------------------------------------
            // Counter
            // -----------------------------------------------

            item {

                CounterCard(

                    counter = 5,

                    onIncrease = {}
                )
            }


            // -----------------------------------------------
            // Animation
            // -----------------------------------------------

            item {

                AnimationCard(

                    visible = true,

                    onToggle = {}
                )
            }


            // -----------------------------------------------
            // Bottom Sheet Button
            // -----------------------------------------------

            item {

                Button(

                    modifier =
                        Modifier.fillMaxWidth(),

                    onClick = {}

                ) {

                    Text(
                        text =
                            "Open Bottom Sheet"
                    )
                }
            }


            // -----------------------------------------------
            // Dialog Button
            // -----------------------------------------------

            item {

                Button(

                    modifier =
                        Modifier.fillMaxWidth(),

                    onClick = {}

                ) {

                    Text(
                        text =
                            "Open Alert Dialog"
                    )
                }
            }
        }
    }
}