package ui

import android.app.Application
import androidx.lifecycle.*
import com.example.roomdatabaseexample.Task
import data.TaskDetailRepository
import kotlinx.coroutines.launch

class TaskDetailViewModel(application: Application) : AndroidViewModel(application){

    //we are passing the application to the taskDetailRepository constructor and saving its value in repo
    private val repo: TaskDetailRepository = TaskDetailRepository(application)

    private val _taskId = MutableLiveData<Long>(0)

    val taskId: LiveData<Long>
    get() = _taskId

    val task: LiveData<Task> = Transformations.switchMap(_taskId) { id->
        repo.getTask(id)
    }

    fun setTaskId (id:Long){

        if(_taskId.value!=id){
            _taskId.value = id
        }
    }

    //insert Task is the suspended function
    //we can call the suspended function only from other suspended function or a coroutinescope

    fun saveTask(task: Task){
        viewModelScope.launch {
            //if value is 0 that basically means that this task is not added it is updated
            if(_taskId.value == 0L){
                _taskId.value = repo.insertTask(task)
            }else{
                repo.updateTask(task)
            }
        }
    }

    fun deleteTask(){
        viewModelScope.launch {
            task.value?.let{
                repo.deleteTask(it)
            }
        }
    }





}