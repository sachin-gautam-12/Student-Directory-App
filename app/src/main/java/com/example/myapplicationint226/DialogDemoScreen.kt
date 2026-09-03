package com.example.myapplicationint226

import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

// --------------------------------------------------
// ACTIVITY
// --------------------------------------------------

class DialogDemo : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                DialogDemoScreen()
            }
        }
    }
}

// --------------------------------------------------
// MAIN SCREEN
// --------------------------------------------------

@Composable
fun DialogDemoScreen() {

    val context = LocalContext.current

    var showAlertDialog by remember {
        mutableStateOf(false)
    }

    var showCustomDialog by remember {
        mutableStateOf(false)
    }

    var inputText by remember {
        mutableStateOf("")
    }

    Scaffold { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Text(
                    text = "CSE226 - Dialogs Demo",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                // Alert Dialog Button
                Button(
                    onClick = {
                        showAlertDialog = true
                    },
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {
                    Text("Show Alert Dialog")
                }

                // Custom Dialog Button
                OutlinedButton(
                    onClick = {
                        showCustomDialog = true
                    },
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {
                    Text("Show Custom Dialog")
                }
            }
        }
    }

    // --------------------------------------------------
    // STANDARD ALERT DIALOG
    // --------------------------------------------------

    if (showAlertDialog) {

        AlertDialog(

            onDismissRequest = {
                showAlertDialog = false
            },

            title = {
                Text(
                    text = "⚠️ Confirm Deletion"
                )
            },

            text = {
                Text(
                    text = "Are you sure you want to delete " +
                            "this lecture note? This action cannot be undone."
                )
            },

            confirmButton = {

                TextButton(
                    onClick = {

                        showAlertDialog = false

                        Toast.makeText(
                            context,
                            "Item Deleted",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                ) {
                    Text("Delete")
                }
            },

            dismissButton = {

                TextButton(
                    onClick = {
                        showAlertDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }

    // --------------------------------------------------
    // CUSTOM DIALOG
    // --------------------------------------------------

    if (showCustomDialog) {

        Dialog(
            onDismissRequest = {
                showCustomDialog = false
            }
        ) {

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(28.dp),
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 6.dp
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),

                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "New Project",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    OutlinedTextField(

                        value = inputText,

                        onValueChange = {
                            inputText = it
                        },

                        label = {
                            Text("Enter Project Name")
                        },

                        placeholder = {
                            Text("Example: My Android App")
                        },

                        singleLine = true,

                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        TextButton(
                            onClick = {
                                showCustomDialog = false
                                inputText = ""
                            }
                        ) {
                            Text("Cancel")
                        }

                        Spacer(
                            modifier = Modifier.width(8.dp)
                        )

                        Button(
                            onClick = {

                                if (inputText.isBlank()) {

                                    Toast.makeText(
                                        context,
                                        "Please enter a project name",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                } else {

                                    Toast.makeText(
                                        context,
                                        "Project Created: $inputText",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    inputText = ""
                                    showCustomDialog = false
                                }
                            }
                        ) {
                            Text("Submit")
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
fun DialogDemoScreenPreview() {

    MaterialTheme {
        DialogDemoScreen()
    }
}