package ui

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.roomdatabaseexample.PriorityLevel
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.Task
import com.example.roomdatabaseexample.TaskStatus
import kotlinx.android.synthetic.main.fragment_task_detail.*

//val hobbies: ArrayList<String> = ArrayList()

class TaskDetailFragment : Fragment() {

 private lateinit var viewmodel: TaskDetailViewModel

 override fun onCreate( savedInstanceState: Bundle?){
     super.onCreate(savedInstanceState)

     //creating instance of viewModel
     viewmodel = ViewModelProviders.of(this).get(TaskDetailViewModel::class.java)



 }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_detail, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val arraylist: ArrayList<String> = ArrayList()
        val listAdapter: ArrayAdapter<String> = ArrayAdapter(requireActivity(),android.R.layout.simple_list_item_1,arraylist)


        //this code os for drop down
        //we make a mutable list name priority
        //then we call the priority level enum
        //in this mutable list we get the priority of various item clicked
        val priorities = mutableListOf<String>()
        PriorityLevel.values().forEach { priorities.add(it.name)}
        //spinner uses a arrayadapter to show the items
        val arrayAdapter = ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_dropdown_item, priorities)
        task_priority.adapter = arrayAdapter

        task_priority?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                updateTaskPriorityView(position)
            }
        }
        val id = TaskDetailFragmentArgs.fromBundle(requireArguments()).id
        viewmodel.setTaskId(id)

        viewmodel.task.observe(viewLifecycleOwner, Observer {
            it?.let { setData(it) }
        })

        save_task.setOnClickListener{
            saveTask()
            Toast.makeText(context, "Task saved successfully", Toast.LENGTH_SHORT).show()
        }

        delete_task.setOnClickListener{
            deleteTask()
            Toast.makeText(context, "Task deleted successfully", Toast.LENGTH_SHORT).show()
        }
    }

    private  fun setData(task: Task){
        updateTaskPriorityView(task.priority)
        task_title.setText(task.title)
        task_detail.setText(task.detail)
        if(task.status == TaskStatus.Open.ordinal){
            status_open.isChecked = true
        }else{
            status_closed.isChecked = true
        }
        //this is for the drop down spinner for selecting the priority
        task_priority.setSelection(task.priority)

    }

    private fun saveTask(){
        val title = task_title.text.toString()
        val detail = task_detail.text.toString()
        val priority = task_priority.selectedItemPosition

        val selectedStatusButton = status_group.findViewById<RadioButton>(status_group.checkedRadioButtonId)
        var status = TaskStatus.Open.ordinal

        if(selectedStatusButton.text == TaskStatus.Closed.name){
            status = TaskStatus.Closed.ordinal
        }

        val task = Task(viewmodel.taskId.value!!, title,detail,priority,status)
        viewmodel.saveTask(task)

        requireActivity().onBackPressed()

    }

    private fun deleteTask(){
        viewmodel.deleteTask()

        requireActivity().onBackPressed()
    }


    private fun updateTaskPriorityView(priority: Int){
        when(priority){
            PriorityLevel.High.ordinal ->{
                task_priority_view.setBackgroundColor(
                    ContextCompat.getColor(requireActivity(), R.color.colorPriorityHigh))
            }
            PriorityLevel.Medium.ordinal ->{
                task_priority_view.setBackgroundColor(
                    ContextCompat.getColor(requireActivity(), R.color.colorPriorityMedium))
            }
            else ->  task_priority_view.setBackgroundColor(
                ContextCompat.getColor(requireActivity(), R.color.colorPriorityLow))
        }
    }


}
