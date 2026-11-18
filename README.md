#Student Directory App
Student Directory App
An Android student-records application built with Kotlin,
Jetpack Compose, Room Database, and Kotlin Flow.

Overview
Student Directory is a native Android project for representing and
displaying student records. The data model stores each student's ID,
name, and course. Room provides the local database layer, the
DAO exposes student data as a Flow, and a ViewModel makes that data
available to the Compose UI.

The source package is:
com.example.myapplicationint226
Key capabilities
- Represents student records with a Room entity.
- Uses an auto-generated primary key for each student.
- Defines database access through a Room DAO.
- Exposes the student list as Flow<List<StudentEntity>> so the UI
  can observe database changes.
- Separates database access from UI presentation with a ViewModel.
- Displays student records using a Jetpack Compose screen.
Scope note: The source shared for this project confirms the entity
fields (id, name, course), the DAO read query and insert
operation, and the Flow/ViewModel relationship. Do not describe edit,
delete, search, authentication, cloud sync, or a complete add-student
form as implemented unless those features are added to the code.

Technology stack
  Technology           Role in this project
  Kotlin               Main application language
Jetpack Compose      Declarative Android UI
Room                 Local SQLite database abstraction
Kotlin Flow          Observable stream of student records
AndroidX ViewModel   Holds and exposes UI data
ViewModel Factory    Creates the ViewModel with its DAO dependency
Architecture
Data flow, step by step
1. App entry: MainActivity.kt is the Android activity entry point
   and hosts the Compose UI.
2. UI layer: StudentScreen.kt is responsible for presenting
   student records.
3. ViewModel: StudentViewModel.kt receives a StudentDao and
   exposes students as Flow<List<StudentEntity>>.
4. DAO: StudentDao.kt defines the database operations:
   - getStudents() runs SELECT * FROM students and returns a
     Flow.
   - insertStudent(student) inserts a student record.
5. Database: StudentDatabase.kt declares StudentEntity as a
   Room entity and exposes studentDao().
6. Entity: StudentEntity.kt defines the students table with
   id, name, and course.
7. Reactive update: When a record is inserted, Room can emit the
   updated query result through the Flow; the ViewModel exposes that
   stream to the UI.
Source-code guide
All main Kotlin files are under:
app/src/main/java/com/example/myapplicationint226/
  File                                Responsibility
  MainActivity.kt                   Android activity and Compose
content entry point

  StudentEntity.kt                  Room table model: id, name,
course

  StudentDao.kt                     Declares getStudents() and
insertStudent()

  StudentDatabase.kt                Room database declaration and DAO
provider

  StudentViewModel.kt               Exposes Flow<List<StudentEntity>>
to the UI

  StudentViewModelFactory.kt        Supplies the DAO when creating
StudentViewModel
StudentScreen.kt                  Compose presentation of student
records
Important implementation details
Student entity
@Entity(tableName = "students")
data class StudentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val course: String
)
DAO contract
@Dao
interface StudentDao {
    @Query("SELECT * FROM students")
    fun getStudents(): Flow<List<StudentEntity>>

    @Insert
    suspend fun insertStudent(student: StudentEntity)
}
ViewModel data stream
class StudentViewModel(private val studentDao: StudentDao) : ViewModel() {
    val students: Flow<List<StudentEntity>> = studentDao.getStudents()
}
These snippets describe the core data contract. The database instance,
dependency creation, and Compose collection should be checked in the
corresponding project files when extending the app.
Screenshots
Add actual screenshots captured from the running app to the
screenshots/ folder. Suggested filenames:

 ### Home Screen
![Home Screen](screenshots/home.png)

### Student Directory
![Student Directory](screenshots/student-directory.png)

### Student Form
![Student Form](screenshots/student-form.png)

After adding the image files, use these embeds (remove any that do not
apply):
Home screen

Student directory

Student form

The image paths above are placeholders. Replace them with screenshots
from your own app; no screenshots were included with the project
details shared here.

Run the project
1. Clone the repository:
   git clone https://github.com/sachin-gautam-12/Student-Directory-App.git
2. Open the cloned project in Android Studio.
3. Wait for the Gradle sync to finish.
4. Select an Android emulator or connect an Android device.
5. Click Run in Android Studio.
Possible future enhancements
- Add a Compose form to create student records.
- Add update and delete operations.
- Add student search and course filtering.
- Add input validation and empty-state messaging.
- Add UI and database tests.
Repository
Student-Directory-App on
GitHub
