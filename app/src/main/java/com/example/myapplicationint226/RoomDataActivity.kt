//
//package com.example.myapplicationint226
//
//import android.content.Context
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Delete
//import androidx.compose.material3.Button
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.viewModelScope
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.room.Dao
//import androidx.room.Database
//import androidx.room.Delete
//import androidx.room.Entity
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.PrimaryKey
//import androidx.room.Query
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.SharingStarted
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.stateIn
//import kotlinx.coroutines.launch
//
//// ROOM ENTITY
//
//@Entity(tableName = "students")
//data class StudentEntity(
//    @PrimaryKey(autoGenerate = true)
//    val id: Int = 0,
//    val name: String,
//    val rollNumber: String,
//    val courseCode: String
//)
//
//// DATA ACCESS OBJECT
//
//@Dao
//interface StudentDao {
//
//    @Query("SELECT * FROM students ORDER BY name ASC")
//    fun getAllStudents(): Flow<List<StudentEntity>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertStudent(student: StudentEntity)
//
//    @Delete
//    suspend fun deleteStudent(student: StudentEntity)
//
//    @Query("DELETE FROM students")
//    suspend fun deleteAllStudents()
//}
//
//// ROOM DATABASE
//
//@Database(
//    entities = [StudentEntity::class],
//    version = 1,
//    exportSchema = false
//)
//abstract class StudentDatabase : RoomDatabase() {
//
//    abstract fun studentDao(): StudentDao
//
//    companion object {
//        @Volatile
//        private var INSTANCE: StudentDatabase? = null
//
//        fun getDatabase(context: Context): StudentDatabase {
//            return INSTANCE ?: synchronized(this) {
//                INSTANCE ?: Room.databaseBuilder(
//                    context.applicationContext,
//                    StudentDatabase::class.java,
//                    "student_database"
//                ).build().also {
//                    INSTANCE = it
//                }
//            }
//        }
//    }
//}
//
//// VIEWMODEL
//
//class StudentViewModel(
//    private val dao: StudentDao
//) : ViewModel() {
//
//    val studentList: StateFlow<List<StudentEntity>> =
//        dao.getAllStudents().stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5000),
//            initialValue = emptyList()
//        )
//
//    fun addStudent(
//        name: String,
//        rollNumber: String,
//        courseCode: String
//    ) {
//        if (name.isBlank() || rollNumber.isBlank()) return
//
//        viewModelScope.launch {
//            dao.insertStudent(
//                StudentEntity(
//                    name = name.trim(),
//                    rollNumber = rollNumber.trim(),
//                    courseCode = courseCode.trim().ifEmpty {
//                        "CSE226"
//                    }
//                )
//            )
//        }
//    }
//
//    fun deleteStudent(student: StudentEntity) {
//        viewModelScope.launch {
//            dao.deleteStudent(student)
//        }
//    }
//}
//
//// VIEWMODEL FACTORY
//
//class StudentViewModelFactory(
//    private val dao: StudentDao
//) : ViewModelProvider.Factory {
//
//    override fun <T : ViewModel> create(
//        modelClass: Class<T>
//    ): T {
//        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return StudentViewModel(dao) as T
//        }
//
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
//
//// ACTIVITY
//
//class RoomDataActivity : ComponentActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        val database =
//            StudentDatabase.getDatabase(applicationContext)
//
//        val factory =
//            StudentViewModelFactory(database.studentDao())
//
//        setContent {
//            MaterialTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    val studentViewModel: StudentViewModel =
//                        viewModel(factory = factory)
//
//                    StudentScreen(viewModel = studentViewModel)
//                }
//            }
//        }
//    }
//}
//
//// STUDENT SCREEN
//
//@Composable
//fun StudentScreen(viewModel: StudentViewModel) {
//
//    val students by viewModel.studentList.collectAsState()
//
//    var nameInput by remember { mutableStateOf("") }
//    var rollInput by remember { mutableStateOf("") }
//    var courseInput by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        Text(
//            text = "Room DB: Student Directory",
//            style = MaterialTheme.typography.headlineMedium
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Card(
//            modifier = Modifier.fillMaxWidth(),
//            colors = CardDefaults.cardColors(
//                containerColor =
//                    MaterialTheme.colorScheme.surfaceVariant
//            )
//        ) {
//            Column(
//                modifier = Modifier.padding(16.dp)
//            ) {
//                Text(
//                    text = "Add New Student Record",
//                    style = MaterialTheme.typography.titleMedium
//                )
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                OutlinedTextField(
//                    value = nameInput,
//                    onValueChange = { nameInput = it },
//                    label = { Text("Student Name") },
//                    modifier = Modifier.fillMaxWidth(),
//                    singleLine = true
//                )
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                OutlinedTextField(
//                    value = rollInput,
//                    onValueChange = { rollInput = it },
//                    label = { Text("Roll Number") },
//                    modifier = Modifier.fillMaxWidth(),
//                    singleLine = true
//                )
//
//                Spacer(modifier = Modifier.height(8.dp))
//
//                OutlinedTextField(
//                    value = courseInput,
//                    onValueChange = { courseInput = it },
//                    label = {
//                        Text("Course Code (e.g., CSE226)")
//                    },
//                    modifier = Modifier.fillMaxWidth(),
//                    singleLine = true
//                )
//
//                Spacer(modifier = Modifier.height(12.dp))
//
//                Button(
//                    onClick = {
//                        if (
//                            nameInput.isNotBlank() &&
//                            rollInput.isNotBlank()
//                        ) {
//                            viewModel.addStudent(
//                                nameInput,
//                                rollInput,
//                                courseInput
//                            )
//
//                            nameInput = ""
//                            rollInput = ""
//                            courseInput = ""
//                        }
//                    },
//                    modifier = Modifier.align(Alignment.End)
//                ) {
//                    Text("Save to Room DB")
//                }
//            }
//        }
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        Text(
//            text = "Stored Records (${students.size})",
//            style = MaterialTheme.typography.titleLarge
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        if (students.isEmpty()) {
//            Text(
//                text = "No student records found.",
//                style = MaterialTheme.typography.bodyMedium
//            )
//        } else {
//            LazyColumn(
//                modifier = Modifier.weight(1f),
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                items(
//                    items = students,
//                    key = { it.id }
//                ) { student ->
//                    StudentRowItem(
//                        student = student,
//                        onDelete = {
//                            viewModel.deleteStudent(student)
//                        }
//                    )
//                }
//            }
//        }
//    }
//}
//
//// STUDENT ROW
//
//@Composable
//fun StudentRowItem(
//    student: StudentEntity,
//    onDelete: () -> Unit
//) {
//    Card(
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Column(
//                modifier = Modifier.weight(1f)
//            ) {
//                Text(
//                    text = student.name,
//                    style = MaterialTheme.typography.titleMedium
//                )
//
//                Text(
//                    text = "Roll: ${student.rollNumber} | Course: ${student.courseCode}",
//                    style = MaterialTheme.typography.bodyMedium,
//                    color = MaterialTheme.colorScheme.onSurfaceVariant
//                )
//            }
//
//            IconButton(onClick = onDelete) {
//                Icon(
//                    imageVector = Icons.Default.Delete,
//                    contentDescription = "Delete Record",
//                    tint = MaterialTheme.colorScheme.error
//                )
//            }
//        }
//    }
//}
//
//// PREVIEW
//
//@Preview(showBackground = true)
//@Composable
//fun StudentRowItemPreview() {
//    MaterialTheme {
//        StudentRowItem(
//            student = StudentEntity(
//                id = 1,
//                name = "Arsalan",
//                rollNumber = "01",
//                courseCode = "CSE226"
//            ),
//            onDelete = {}
//        )
//    }
//}
