package unj.cs.student

import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import unj.cs.student.Data.StudentDao
import unj.cs.student.Data.Student

class StudentViewModel(private val studentDao: StudentDao) : ViewModel() {
    val allStudents: LiveData<List<Student>> = studentDao.getStudentList().asLiveData()

    fun addStudent(student: Student) {
        viewModelScope.launch {
            studentDao.insert(student)
        }
    }

    fun updateStudent(student: Student){
        viewModelScope.launch {
            studentDao.update(student)
        }
    }

    fun deleteStudent(student: Student){
        viewModelScope.launch {
            studentDao.delete(student)
        }
    }

    fun getStudentById(id: Int): Flow<Student> = studentDao.getStudentById(id)
    fun getStudentByIds(ids: Int): Flow<Student> = studentDao.getStudentByIds(ids)

}

class StudentViewModelFactory(private val studentDao: StudentDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudentViewModel(studentDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}