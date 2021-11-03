package com.example.myapplication

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.database.model.TasksListModel
import com.example.myapplication.view.EditFragment
import com.example.myapplication.view.TitleFragment
import java.text.SimpleDateFormat
import java.util.*

class TasksListAdapter(
    val tasks: List<TasksListModel>,
    val viewModel: TascksViewModel,
    val manager: FragmentManager,
    val context: Context
):
    RecyclerView.Adapter<TasksListAdapter.TasksViewHolder>() {
//------------------------------------------------------------------------------------------for view
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TasksListAdapter.TasksViewHolder {
        return TasksViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val task = tasks[position]
//-----------------------------------------------------------------------------------for delete tack
        holder.deleteButton.setOnClickListener {
            val alertDialog = AlertDialog.Builder(context).setTitle("Delete")
                .setMessage("Are you sure you want to delete this tassk?")
            alertDialog.setPositiveButton("yes") { _, _ ->
                viewModel.deleteTask(task)
            }
            alertDialog.setNegativeButton("no") { dialog, _ ->
                dialog.cancel()
            }
            alertDialog.show()
        }
//--------------------------------------------------------------------------------
        holder.daySelect.text = task.calendar   //comparison current_date with chosen date
        val current_date = Date()
        val current_year = current_date.year
        val current_month = current_date.month
        val current_day = current_date.day

        val form = SimpleDateFormat("yyyy/MM/dd")
        val date = form.parse(task.calendar)
        val date_year = date.year
        val date_month = date.month
        val date_day = date.day
        if (current_year == date_year && current_month == date_month && current_day == date_day) {
            holder.divider2.visibility = View.VISIBLE
        }
        if (current_year > date_year){
            holder.divider.visibility = View.VISIBLE
        }
        if(current_year == date_year){
            if(current_month > date_month){
                holder.divider.visibility = View.VISIBLE
            }
        }
        if(current_year == date_year) {
            if (current_month == date_month) {
                if ( date_day< current_day){
                    holder.divider.visibility = View.VISIBLE
                }
            }
        }


//--------------------------------------------------------- case for spinner
        when(task.spinner) {
            "complete" -> holder.layout.setBackgroundResource(R.color.green)
            "parser completed" ->holder.layout.setBackgroundResource(R.color.red)
            "no yet" ->holder.layout.setBackgroundResource(R.color.no)
        }
//---------------------------------------------------------------------------------------- for edit

        val editFragment = EditFragment()

        holder.editButton.setOnClickListener {

            viewModel.selectedTasks.postValue(task)
            editFragment.show(manager, "")
        }
//-------------------------------------------------------------------------------- for show Subject
        val titleFragment = TitleFragment()
        holder.titleTextView.text = task.title
        holder.titleTextView.setOnClickListener {
            viewModel.selectedTasks.postValue(task)
            titleFragment.show(manager, "")
        }
        //-----------------------------------------------------------------------------for checkBox
//        holder.checkBox.isChecked = task.seeit
//
//        holder.checkBox.setOnClickListener {
//            task.seeit = holder.checkBox.isChecked
//            viewModel.updateTask(task)
//        }
//--------------------------------------------------------------------------------------for spinner
      val adapter =  ArrayAdapter.createFromResource( //spinner adapter
                context,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        holder.doneSpinner.adapter = adapter

        holder.doneSpinner.setSelection(context.resources.getStringArray(R.array.planets_array).indexOf(task.spinner))

        var selected = false

        holder.doneSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (selected) {
                    task.spinner = adapter.getItem(position).toString()
                    Log.d("SELECTED_TASK", task.spinner)
                    viewModel.updateTask(task)
                }
                selected = true
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
//-----------------------------------------------------------------------------------for radioButton
        when(task.category) {    // to save Selection category when get out from app

            "All" ->  holder.categoryRadio.check(R.id.Regular_radioButton)
            "Home" -> holder.categoryRadio.check(R.id.home_radioButton)
            "Work" -> holder.categoryRadio.check(R.id.work_radioButton)
            "Shopping" -> holder.categoryRadio.check(R.id.shopping_radioButton)
        }

// update category value when user Selection it to database
holder.categoryRadio.setOnCheckedChangeListener { group, checkedId ->
    if(checkedId == R.id.Regular_radioButton) {
       task.category = "All"
       viewModel.updateTask(task)
    }

    if(checkedId == R.id.home_radioButton) {
    task.category = "Home"
        viewModel.updateTask(task)

    }
    if(checkedId == R.id.work_radioButton) {
        task.category = "Work"
        viewModel.updateTask(task)
    }
    if(checkedId == R.id.shopping_radioButton) {
        task.category = "Shopping"
       viewModel.updateTask(task)

    }




}

    //------------------------------------------------------------------------------------
}
    override fun getItemCount(): Int {
        return tasks.size
    }
 //------------------------------------------------------------------------------------// view holder
    class TasksViewHolder(view: View):RecyclerView.ViewHolder(view){
        val daySelect:TextView =  view.findViewById(R.id.day_textView)
        var divider:View = view.findViewById(R.id.divider)
        var divider2:View = view.findViewById(R.id.divider2)
        val deleteButton:ImageButton = view.findViewById(R.id.delete_Button)
        var doneSpinner:Spinner = view.findViewById(R.id.done_spinner)
        val editButton:ImageButton = view.findViewById(R.id.edit_Button)
        val titleTextView:TextView = view.findViewById(R.id.title_textView)
        var categoryRadio: RadioGroup = view.findViewById(R.id.category_Radio)
        var layout:LinearLayout = view.findViewById(R.id.layout)


    }

}