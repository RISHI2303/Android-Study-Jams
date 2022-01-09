package ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.roomdatabaseexample.SortColumn
import com.example.roomdatabaseexample.Task
import data.TaskListRepository

//we r inheriting from android viewmodel instead of viewmodel
//to get the context of application
class TaskListViewModel (application: Application): AndroidViewModel(application){
    private val repo: TaskListRepository = TaskListRepository(application)

    //we pass the default value
    private val _sortOrder = MutableLiveData<SortColumn>(SortColumn.Priority)

    //tranformations observe the _sortOrder whenever the changes happens we get a new value as it inside it
    val tasks: LiveData<List<Task>> = Transformations.switchMap(_sortOrder){
        repo.getTasks(_sortOrder.value!!)
    }

    fun ChangeSortOrder(sortOrder : SortColumn){
        _sortOrder.value = sortOrder
    }


}