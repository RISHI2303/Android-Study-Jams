package data

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.roomdatabaseexample.SortColumn
import com.example.roomdatabaseexample.Task
import com.example.roomdatabaseexample.TaskStatus

// Activity/fragment -> ViewModel -> Repository -> Dao
class TaskListRepository(context: Application) {
    private  val taskListDao: TaskListDao = TaskDatabase.getDatabase(context).taskListDao()

    //to check the sorting feature
    //the default is sort by priority
    //this function returns list of task
    //and checks whether the sort is priority one or title  one
    fun getTasks(sort: SortColumn = SortColumn.Priority): LiveData<List<Task>> {
        return if(sort == SortColumn.Priority){
            taskListDao.getTasksByPriority(TaskStatus.Open.ordinal)
        }else{
            taskListDao.getTasksByTitle(TaskStatus.Open.ordinal)
        }
    }
}