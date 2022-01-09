package ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_task_list.*
import androidx.navigation.fragment.findNavController
import com.example.roomdatabaseexample.R
import com.example.roomdatabaseexample.SortColumn
import com.example.roomdatabaseexample.TaskAdapter


class TaskListFragment : Fragment() {

private lateinit var viewModel: TaskListViewModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        //this is set as true to make android studio know that we are setting up the menu options in the fragment
        setHasOptionsMenu(true)

        //setting instance of viewModel
        viewModel = ViewModelProviders.of(this).get(TaskListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(task_list){
            layoutManager = LinearLayoutManager(activity)
            adapter = TaskAdapter{
                findNavController().navigate(
                    TaskListFragmentDirections.actionTaskListFragmentToTaskDetailFragment(it))
                //here it denotes the id which we got from the task adapter after clicking the item
                //and using safeargs we are passing the id to the next fragment of that particular item
            }
        }

        add_task.setOnClickListener{
            findNavController().navigate(
                TaskListFragmentDirections.actionTaskListFragmentToTaskDetailFragment(0))
            //here we pass 0 beacuse we are going to make new task in the detail fragment

        }

        //we are observing the value of live data from the viewmodel
        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            //this will show the list of task using recycler view
            (task_list.adapter as TaskAdapter).submitList(it)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_sort_priority -> {
                viewModel.ChangeSortOrder(SortColumn.Priority)
                item.isChecked = true
                true
            }
            R.id.menu_sort_title -> {
                viewModel.ChangeSortOrder(SortColumn.Title)
                item.isChecked = true
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
