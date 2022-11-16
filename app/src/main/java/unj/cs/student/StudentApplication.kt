package unj.cs.student

import android.app.Application
import unj.cs.student.Data.StudentDatabase

class StudentApplication : Application() {
    val database: StudentDatabase by lazy { StudentDatabase.getDatabase(this) }
}