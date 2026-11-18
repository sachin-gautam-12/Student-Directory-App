//
//package com.example.myapplicationint226
//
//import android.os.Bundle
//
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//import com.example.myapplicationint226.ui.theme.MyApplicationINT226Theme
//
//
//// ==========================================
//// MAIN ACTIVITY
//// ==========================================
//
//class MainActivity : ComponentActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        enableEdgeToEdge()
//
//        setContent {
//            MyApplicationINT226Theme {
//                HomeScreen()
//            }
//        }
//    }
//}
//
//
//// ==========================================
//// HOME SCREEN
//// ==========================================
//
//@Composable
//fun HomeScreen() {
//
//    Scaffold(
//
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text("CSE226 Android Demos")
//                }
//            )
//        }
//
//    ) { innerPadding ->
//
//        Column(
//
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//                .padding(20.dp)
//                .verticalScroll(rememberScrollState()),
//
//            horizontalAlignment = Alignment.CenterHorizontally,
//
//            verticalArrangement = Arrangement.spacedBy(12.dp)
//
//        ) {
//
//            Text(
//                text = "Jetpack Compose Demo App",
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold,
//                textAlign = TextAlign.Center
//            )
//
//            Spacer(
//                modifier = Modifier.height(8.dp)
//            )
//
//            Text(
//                text = "Select any topic to open its demo",
//                fontSize = 15.sp,
//                color = MaterialTheme.colorScheme.secondary,
//                textAlign = TextAlign.Center
//            )
//
//            Spacer(
//                modifier = Modifier.height(12.dp)
//            )
//
//            DemoButton("1. Combined Effect")
//            DemoButton("2. Dialog Demo")
//            DemoButton("3. DisposableEffect")
//            DemoButton("4. LaunchedEffect")
//            DemoButton("5. Modal Bottom Sheet")
//            DemoButton("6. Persistent Bottom Sheet")
//            DemoButton("7. SideEffect")
//            DemoButton("8. Simple Dialog")
//            DemoButton("9. Reactive Flow")
//            DemoButton("10. MVVM")
//            DemoButton("11. Structured Concurrency")
//            DemoButton("12. Scope & Context")
//            DemoButton("13. Lifecycle State Management")
//            DemoButton("14. Real GPS Location")
//            DemoButton("15. Student Directory")
//
//            Spacer(
//                modifier = Modifier.height(20.dp)
//            )
//
//            Text(
//                text = "CSE226 • Jetpack Compose",
//                fontSize = 13.sp,
//                color = MaterialTheme.colorScheme.outline,
//                textAlign = TextAlign.Center
//            )
//        }
//    }
//}
//
//
//// ==========================================
//// DEMO BUTTON
//// ==========================================
//
//@Composable
//fun DemoButton(text: String) {
//
//    Button(
//
//        onClick = {
//            // Demo screens temporarily disabled
//        },
//
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 8.dp)
//
//    ) {
//
//        Text(
//            text = text,
//            fontSize = 16.sp
//        )
//    }
//}
//
//
//// ==========================================
//// PREVIEW
//// ==========================================
//
//@Preview(
//    showBackground = true,
//    showSystemUi = true,
//    name = "CSE226 Main Dashboard"
//)
//@Composable
//fun MainNavigationScreenPreview() {
//
//    MyApplicationINT226Theme {
//        HomeScreen()
//    }
//}
//
//
//
//
//
//
//
//@file:OptIn(
//    androidx.compose.material3.ExperimentalMaterial3Api::class
//)
//
//package com.example.myapplicationint226
//
//import android.os.Bundle
//
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.foundation.verticalScroll
//
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
//
//import androidx.room.Room
//
//import com.example.myapplicationint226.ui.theme.MyApplicationINT226Theme
//
//
//// ==================================================
//// MAIN ACTIVITY
//// ==================================================
//
//class MainActivity : ComponentActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        enableEdgeToEdge()
//
//        setContent {
//            MyApplicationINT226Theme {
//                MainNavigationScreen()
//            }
//        }
//    }
//}
//
//
//// ==================================================
//// MAIN NAVIGATION
//// ==================================================
//
//@Composable
//fun MainNavigationScreen() {
//
//    var selectedScreen by remember {
//        mutableStateOf("Home")
//    }
//
//    when (selectedScreen) {
//
//        // ==========================================
//        // HOME
//        // ==========================================
//
//        "Home" -> {
//            HomeScreen(
//                onScreenSelected = { screen ->
//                    selectedScreen = screen
//                }
//            )
//        }
//
//
//        // ==========================================
//        // 1. COMBINED EFFECT
//        // ==========================================
//
//        "Combined Effect" -> {
//            DemoScreenContainer(
//                title = "Combined Effect",
//                onBack = {
//                    selectedScreen = "Home"
//                }
//            ) {
//                CombinedEffectScreen()
//            }
//        }
//
//
//        // ==========================================
//        // 2. DIALOG DEMO
//        // ==========================================
//
//        "Dialog Demo" -> {
//            DemoScreenContainer(
//                title = "Dialog Demo",
//                onBack = {
//                    selectedScreen = "Home"
//                }
//            ) {
//                DialogDemoScreen()
//            }
//        }
//
//
//        // ==========================================
//        // 3. DISPOSABLE EFFECT
//        // ==========================================
//
//        "Disposable Effect" -> {
//            DemoScreenContainer(
//                title = "Disposable Effect",
//                onBack = {
//                    selectedScreen = "Home"
//                }
//            ) {
//                DisposableEffectScreen()
//            }
//        }
//
//
//        // ==========================================
//        // 4. LAUNCHED EFFECT
//        // ==========================================
//
//        "Launched Effect" -> {
//            DemoScreenContainer(
//                title = "Launched Effect",
//                onBack = {
//                    selectedScreen = "Home"
//                }
//            ) {
//                LaunchedEffectScreen()
//            }
//        }
//
//
//        // ==========================================
//        // 5. MODAL BOTTOM SHEET
//        // ==========================================
//
//        "Modal Bottom Sheet" -> {
//            DemoScreenContainer(
//                title = "Modal Bottom Sheet",
//                onBack = {
//                    selectedScreen = "Home"
//                }
//            ) {
//                PlaceScreen()
//            }
//        }
//
//
//        // ==========================================
//        // 6. PERSISTENT BOTTOM SHEET
//        // ==========================================
//
//        "Persistent Bottom Sheet" -> {
//            DemoScreenContainer(
//                title = "Persistent Bottom Sheet",
//                onBack = {
//                    selectedScreen = "Home"
//                }
//            ) {
//                PersistentBottomSheetScreen()
//            }
//        }
//
//
//        // ==========================================
//        // 7. SIDE EFFECT
//        // ==========================================
//
//        "Side Effect" -> {
//            DemoScreenContainer(
//                title = "Side Effect",
//                onBack = {
//                    selectedScreen = "Home"
//                }
//            ) {
//                SideEffectScreen()
//            }
//        }
//
//
//        // ==========================================
//        // 8. SIMPLE DIALOG
//        // ==========================================
//
//        "Simple Dialog" -> {
//            DemoScreenContainer(
//                title = "Simple Dialog",
//                onBack = {
//                    selectedScreen = "Home"
//                }
//            ) {
//                SimpleDialogScreen()
//            }
//        }
//
//
//        // ==========================================
//        // 9. REACTIVE FLOW
//        // ==========================================
//
//        "Reactive Flow" -> {
//            DemoScreenContainer(
//                title = "Reactive Flow",
//                onBack = {
//                    selectedScreen = "Home"
//                }
//            ) {
//                ReactiveFlowScreen()
//            }
//        }
//
//
//        // ==========================================
//        // 10. MVVM
//        // ==========================================
//
//        "MVVM" -> {
//            DemoScreenContainer(
//                title = "MVVM",
//                onBack = {
//                    selectedScreen = "Home"
//                }
//            ) {
//                MVVMScreen()
//            }
//        }
//
//
//        // ==========================================
//        // 11. STRUCTURED CONCURRENCY
//        // ==========================================
//
//        "Structured Concurrency" -> {
//            DemoScreenContainer(
//                title = "Structured Concurrency",
//                onBack = {
//                    selectedScreen = "Home"
//                }
//            ) {
//                StructuredConcurrencyScreen()
//            }
//        }
//
//
//        // ==========================================
//        // 12. SCOPE & CONTEXT
//        // ==========================================
//
//        "Scope & Context" -> {
//            DemoScreenContainer(
//                title = "Scope & Context",
//                onBack = {
//                    selectedScreen = "Home"
//                }
//            ) {
//                ScopeContextScreen()
//            }
//        }
//
//
//        // ==========================================
//        // 13. LIFECYCLE STATE MANAGEMENT
//        // ==========================================
//
//        "Lifecycle State Management" -> {
//            DemoScreenContainer(
//                title = "Lifecycle State Management",
//                onBack = {
//                    selectedScreen = "Home"
//                }
//            ) {
//                LifecycleStateManagementScreen()
//            }
//        }
//
//
//        // ==========================================
//        // 14. REAL GPS LOCATION
//        // ==========================================
//
//        "Real GPS Location" -> {
//            DemoScreenContainer(
//                title = "Real GPS Location",
//                onBack = {
//                    selectedScreen = "Home"
//                }
//            ) {
//                RealGpsScreen()
//            }
//        }
//
//
//        // ==========================================
//        // 15. STUDENT DIRECTORY
//        // ==========================================
//
//        "Student Directory" -> {
//            DemoScreenContainer(
//                title = "Student Directory",
//                onBack = {
//                    selectedScreen = "Home"
//                }
//            ) {
//                StudentDirectoryRoute()
//            }
//        }
//    }
//}
//
//
//// ==================================================
//// HOME SCREEN
//// ==================================================
//
//@Composable
//fun HomeScreen(
//    onScreenSelected: (String) -> Unit
//) {
//
//    Scaffold(
//
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text("CSE226 Android Demos")
//                }
//            )
//        }
//
//    ) { innerPadding ->
//
//        Column(
//
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//                .padding(20.dp)
//                .verticalScroll(rememberScrollState()),
//
//            horizontalAlignment =
//                Alignment.CenterHorizontally,
//
//            verticalArrangement =
//                Arrangement.spacedBy(12.dp)
//
//        ) {
//
//            // ======================================
//            // TITLE
//            // ======================================
//
//            Text(
//                text = "Jetpack Compose Demo App",
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold,
//                textAlign = TextAlign.Center
//            )
//
//
//            Spacer(
//                modifier = Modifier.height(8.dp)
//            )
//
//
//            Text(
//                text = "Select any topic to open its demo",
//                fontSize = 15.sp,
//                color = MaterialTheme.colorScheme.secondary,
//                textAlign = TextAlign.Center
//            )
//
//
//            Spacer(
//                modifier = Modifier.height(12.dp)
//            )
//
//
//            // ======================================
//            // 1
//            // ======================================
//
//            DemoButton(
//                text = "1. Combined Effect",
//                onClick = {
//                    onScreenSelected("Combined Effect")
//                }
//            )
//
//
//            // ======================================
//            // 2
//            // ======================================
//
//            DemoButton(
//                text = "2. Dialog Demo",
//                onClick = {
//                    onScreenSelected("Dialog Demo")
//                }
//            )
//
//
//            // ======================================
//            // 3
//            // ======================================
//
//            DemoButton(
//                text = "3. DisposableEffect",
//                onClick = {
//                    onScreenSelected("Disposable Effect")
//                }
//            )
//
//
//            // ======================================
//            // 4
//            // ======================================
//
//            DemoButton(
//                text = "4. LaunchedEffect",
//                onClick = {
//                    onScreenSelected("Launched Effect")
//                }
//            )
//
//
//            // ======================================
//            // 5
//            // ======================================
//
//            DemoButton(
//                text = "5. Modal Bottom Sheet",
//                onClick = {
//                    onScreenSelected("Modal Bottom Sheet")
//                }
//            )
//
//
//            // ======================================
//            // 6
//            // ======================================
//
//            DemoButton(
//                text = "6. Persistent Bottom Sheet",
//                onClick = {
//                    onScreenSelected("Persistent Bottom Sheet")
//                }
//            )
//
//
//            // ======================================
//            // 7
//            // ======================================
//
//            DemoButton(
//                text = "7. SideEffect",
//                onClick = {
//                    onScreenSelected("Side Effect")
//                }
//            )
//
//
//            // ======================================
//            // 8
//            // ======================================
//
//            DemoButton(
//                text = "8. Simple Dialog",
//                onClick = {
//                    onScreenSelected("Simple Dialog")
//                }
//            )
//
//
//            // ======================================
//            // 9
//            // ======================================
//
//            DemoButton(
//                text = "9. Reactive Flow",
//                onClick = {
//                    onScreenSelected("Reactive Flow")
//                }
//            )
//
//
//            // ======================================
//            // 10
//            // ======================================
//
//            DemoButton(
//                text = "10. MVVM",
//                onClick = {
//                    onScreenSelected("MVVM")
//                }
//            )
//
//
//            // ======================================
//            // 11
//            // ======================================
//
//            DemoButton(
//                text = "11. Structured Concurrency",
//                onClick = {
//                    onScreenSelected("Structured Concurrency")
//                }
//            )
//
//
//            // ======================================
//            // 12
//            // ======================================
//
//            DemoButton(
//                text = "12. Scope & Context",
//                onClick = {
//                    onScreenSelected("Scope & Context")
//                }
//            )
//
//
//            // ======================================
//            // 13
//            // ======================================
//
//            DemoButton(
//                text = "13. Lifecycle State Management",
//                onClick = {
//                    onScreenSelected(
//                        "Lifecycle State Management"
//                    )
//                }
//            )
//
//
//            // ======================================
//            // 14
//            // ======================================
//
//            DemoButton(
//                text = "14. Real GPS Location",
//                onClick = {
//                    onScreenSelected(
//                        "Real GPS Location"
//                    )
//                }
//            )
//
//
//            // ======================================
//            // 15. STUDENT DIRECTORY
//            // ======================================
//
//            DemoButton(
//                text = "15. Student Directory",
//                onClick = {
//                    onScreenSelected(
//                        "Student Directory"
//                    )
//                }
//            )
//
//
//            Spacer(
//                modifier = Modifier.height(20.dp)
//            )
//
//
//            // ======================================
//            // FOOTER
//            // ======================================
//
//            Text(
//                text = "CSE226 • Jetpack Compose",
//                fontSize = 13.sp,
//                color = MaterialTheme.colorScheme.outline,
//                textAlign = TextAlign.Center
//            )
//        }
//    }
//}
//
//
//// ==================================================
//// DEMO BUTTON
//// ==================================================
//
//@Composable
//fun DemoButton(
//    text: String,
//    onClick: () -> Unit
//) {
//
//    Button(
//
//        onClick = onClick,
//
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 8.dp)
//
//    ) {
//
//        Text(
//            text = text,
//            fontSize = 16.sp
//        )
//    }
//}
//
//
//// ==================================================
//// DEMO SCREEN CONTAINER
//// ==================================================
//
//@Composable
//fun DemoScreenContainer(
//    title: String,
//    onBack: () -> Unit,
//    content: @Composable () -> Unit
//) {
//
//    Scaffold(
//
//        topBar = {
//
//            TopAppBar(
//
//                title = {
//                    Text(title)
//                }
//            )
//        }
//
//    ) { innerPadding ->
//
//        Column(
//
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//
//        ) {
//
//            Button(
//
//                onClick = onBack,
//
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(
//                        horizontal = 16.dp,
//                        vertical = 8.dp
//                    )
//
//            ) {
//
//                Text("← Back to Main Menu")
//            }
//
//
//            content()
//        }
//    }
//}
//
//
//// ==================================================
//// STUDENT DIRECTORY ROUTE
//// ==================================================
//
//@Composable
//fun StudentDirectoryRoute() {
//
//    val context = LocalContext.current
//
//    val owner = LocalViewModelStoreOwner.current
//        ?: error("ViewModelStoreOwner not found")
//
//    // Create Room Database
//    val database = remember {
//        Room.databaseBuilder(
//            context.applicationContext,
//            StudentDatabase::class.java,
//            "student_database"
//        ).build()
//    }
//
//    // Create ViewModel
//    val viewModel = remember(owner, database) {
//        ViewModelProvider(
//            owner,
//            StudentViewModelFactory(
//                database.studentDao()
//            )
//        )[StudentViewModel::class.java]
//    }
//
//    // Collect database Flow
//    val students by viewModel.students.collectAsState(
//        initial = emptyList()
//    )
//
//    // Display student list
//    StudentScreen(
//        students = students
//    )
//}
//
//
//// ==================================================
//// PREVIEW
//// ==================================================
//
//@Preview(
//    showBackground = true,
//    showSystemUi = true,
//    name = "CSE226 Main Dashboard"
//)
//@Composable
//fun MainNavigationScreenPreview() {
//
//    MyApplicationINT226Theme {
//
//        MainNavigationScreen()
//    }
//}




@file:OptIn(
    androidx.compose.material3.ExperimentalMaterial3Api::class
)

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.myapplicationint226.ui.theme.MyApplicationINT226Theme


// ==========================================
// MAIN ACTIVITY
// ==========================================

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MyApplicationINT226Theme {
                HomeScreen()
            }
        }
    }
}


// ==========================================
// HOME SCREEN
// ==========================================

@Composable
fun HomeScreen() {

    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Text("CSE226 Android Demos")
                }
            )
        }

    ) { innerPadding ->

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),

            horizontalAlignment = Alignment.CenterHorizontally,

            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {

            Text(
                text = "Jetpack Compose Demo App",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Select any topic to open its demo",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            DemoButton("1. Combined Effect")
            DemoButton("2. Dialog Demo")
            DemoButton("3. DisposableEffect")
            DemoButton("4. LaunchedEffect")
            DemoButton("5. Modal Bottom Sheet")
            DemoButton("6. Persistent Bottom Sheet")
            DemoButton("7. SideEffect")
            DemoButton("8. Simple Dialog")
            DemoButton("9. Reactive Flow")
            DemoButton("10. MVVM")
            DemoButton("11. Structured Concurrency")
            DemoButton("12. Scope & Context")
            DemoButton("13. Lifecycle State Management")
            DemoButton("14. Real GPS Location")
            DemoButton("15. Student Directory")

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


@Composable
fun DemoButton(text: String) {

    Button(

        onClick = {
            // Demo screens temporarily disabled
        },

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

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "CSE226 Main Dashboard"
)
@Composable
fun MainNavigationScreenPreview() {

    MyApplicationINT226Theme {
        HomeScreen()
    }
}
