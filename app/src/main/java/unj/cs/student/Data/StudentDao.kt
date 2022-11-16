package unj.cs.student.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(student: Student)

    @Update
    suspend fun update(student: Student)

    @Delete
    suspend fun delete(student: Student)

    @Query("SELECT * FROM student WHERE id = :id")
    abstract fun getStudentById(id: Int): Flow<Student>

    @Query("SELECT * FROM student WHERE ids = :ids")
    abstract fun getStudentByIds(ids: Int): Flow<Student>

    @Query("SELECT * FROM student")
    abstract fun getStudentList(): Flow<List<Student>>
}