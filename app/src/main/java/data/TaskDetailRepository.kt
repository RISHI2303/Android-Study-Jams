package data

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.roomdatabaseexample.Task

class TaskDetailRepository(context: Application) {
    private val taskDetailDao: TaskDetailDao = TaskDatabase.getDatabase(context).taskDetailDao()

    fun getTask(id:Long): LiveData<Task> {
        return taskDetailDao.getTask(id)
    }

    //this is suspend because we have used the insert task in the Dao as suspend
    suspend fun insertTask(task: Task): Long{
        return taskDetailDao.insertTask(task)
    }


    suspend fun updateTask(task: Task){
        taskDetailDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task){
        taskDetailDao.deleteTask(task)
    }
}