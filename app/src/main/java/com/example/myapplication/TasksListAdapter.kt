package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.database.model.TasksListModel
import com.example.myapplication.view.TitleFragment

class TasksListAdapter
    (val tasks:List<TasksListModel>, val viewModel: TascksViewModel,val manager:FragmentManager):
    RecyclerView.Adapter<TasksListAdapter.TasksViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):TasksListAdapter.TasksViewHolder {
        return TasksViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent,false
            )
        )
    }
    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val task = tasks[position]
        //------------------------------------------------------------------------
        holder.deleteButton.setOnClickListener{
       viewModel.deleteTask(task)
        }

        holder.daySelect.text = task.calendar
      //---------------------------------------------
//         holder.editButton.setOnClickListener{ view ->
//             view.findNavController().navigate(R.id.action_listFragment_to_titleFragment)
//         viewModel.updateTask(task)

        //-----------------------------------------------
     val hassan = TitleFragment()
        holder.titleTextView.text = task.title
        holder.titleTextView.setOnClickListener{
        viewModel.selectedTasks.postValue(task)
       hassan.show(manager,"")
        }
        //------------------------------------------------------------------------------
        holder.checkBox.isChecked != task.seeit

        holder.checkBox.setOnClickListener {
            task.seeit = holder.checkBox.isChecked
            viewModel.updateTask(task)
        }
      //-------------------------------------------------------------------------------------



    }

    override fun getItemCount(): Int {
        return tasks.size
    }
    class TasksViewHolder(view: View):RecyclerView.ViewHolder(view){
        val daySelect:TextView =  view.findViewById(R.id.day_textView)
        var doneSpinner:Spinner = view.findViewById(R.id.done_spinner)
        val titleTextView:TextView = view.findViewById(R.id.title_textView)
        val deleteButton:ImageButton = view.findViewById(R.id.delete_Button)
        val editButton:ImageButton = view.findViewById(R.id.edit_Button)
        var checkBox: CheckBox = view.findViewById(R.id.checkBox)
    }

}