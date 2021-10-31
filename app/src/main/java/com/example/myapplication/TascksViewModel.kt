package com.example.myapplication

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
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
    //----------------------------------------------------------------------------------------------------------
    fun addTask(title: String, subtitle: String, calendar: String ){// , inStock: Boolean, sprinner: String, day: String) {
        viewModelScope.launch {
            tasksRepository.addTasks(TasksListModel(title, subtitle ,true,"no yet",calendar))
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
}

