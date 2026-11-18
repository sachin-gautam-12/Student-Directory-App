package com.example.myapplicationint226

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

class StudentViewModel(
    private val studentDao: StudentDao
) : ViewModel() {

    val students: Flow<List<StudentEntity>> =
        studentDao.getStudents()
}
