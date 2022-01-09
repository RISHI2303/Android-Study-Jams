package data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdatabaseexample.Task

//this dao consist of
//query used in the detail screen
//:id means the id which passed into the function during the runtime
@Dao
interface TaskDetailDao{
    @Query("SELECT * FROM task WHERE `id` = :id")
    fun getTask(id: Long): LiveData<Task>

    //any conflicting id will be ignored automatically
    //same id will be ignored
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: Task): Long

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
    //room does not allow the transactions like delete,insert or update to be done
    //on main thread
    //by using suspend let room to run query in the background

}