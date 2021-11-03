package com.example.myapplication

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.database.model.TasksListModel
import com.example.myapplication.repositories.TasksRepository
import kotlinx.coroutines.launch
import java.util.*

class TascksViewModel:ViewModel() {

    private val tasksRepository = TasksRepository.get()
    var taskslist = tasksRepository.getTasks()
    var selectedTasks = MutableLiveData<TasksListModel>()
   var category:String = "All"
    //----------------------------------------------------------------------------------------------------------

    fun addTask(title: String, subtitle: String, calendar: String ){
        viewModelScope.launch {
            tasksRepository.addTasks(TasksListModel(title, subtitle ,"no yet",calendar,"All"))
        }
    }
//--------------------------------------------------------------------------------------------------------
    fun updateTask(tasksListModel: TasksListModel) {
        viewModelScope.launch {
            tasksRepository.updateTasks(tasksListModel)
        }
    }
//------------------------------------------------------------------------------
    fun deleteTask(tasksListModel: TasksListModel) {
        viewModelScope.launch {
            tasksRepository.deleteTasks(tasksListModel)
        }
//----------------------------------------------------------------------------------
    }
    fun getTasks() : LiveData<List<TasksListModel>> {
        if (category.equals("All"))
            return tasksRepository.getTasks()
        else
            return tasksRepository.filter(category)

    }
}

