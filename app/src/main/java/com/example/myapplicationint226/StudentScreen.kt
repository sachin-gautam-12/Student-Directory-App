
package com.example.myapplicationint226

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StudentScreen(students: List<StudentEntity>) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Student Directory",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (students.isEmpty()) {
            Text("No student records found.")
        } else {
            LazyColumn {

                items(students) { student ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text("ID: ${student.id}")
                            Text("Name: ${student.name}")
                            Text("Course: ${student.course}")
                        }
                    }
                }
            }
        }
    }
}


// ======================================
// PREVIEW
// ======================================

@Preview(showBackground = true)
@Composable
fun StudentScreenPreview() {

    MaterialTheme {

        StudentScreen(
            students = listOf(
                StudentEntity(
                    id = 1,
                    name = "Rahul",
                    course = "BCA"
                ),
                StudentEntity(
                    id = 2,
                    name = "Priya",
                    course = "BBA"
                ),
                StudentEntity(
                    id = 3,
                    name = "Aman",
                    course = "B.Tech"
                )
            )
        )
    }
}
