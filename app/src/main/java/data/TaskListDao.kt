package data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.roomdatabaseexample.Task

//we have created two dao one fro detail and other for list
//so that we can display the seperate queries for them
//we can also have a single dao

@Dao
interface TaskListDao{
    //:status will have the value that will be passed in the function gettaskbypriority
    @Query("SELECT * FROM task WHERE status = :status ORDER BY priority DESC")
    //order by DESC means sorting the data in  descending order
    fun getTasksByPriority(status: Int): LiveData<List<Task>>

    @Query("SELECT * FROM task WHERE status = :status ORDER BY title")
    //order by title means sorting the data acc to title
    fun getTasksByTitle(status: Int): LiveData<List<Task>>
}