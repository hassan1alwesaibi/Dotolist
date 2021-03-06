package com.example.myapplication.repositories

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.room.Room
import com.example.myapplication.database.ITasksDao
import com.example.myapplication.database.TasksDatabase
import com.example.myapplication.database.model.TasksListModel
import java.lang.Exception
// single source of truth for all app data, clean API for UI to
//communicate with
private const val DATABASE_NAME = "tasks-database"
class TasksRepository(context: Context) {
    private val database: TasksDatabase =
        Room.databaseBuilder(
            context,
            TasksDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    private val itasksDao = database.iTasksDao()
    fun getTasks() = itasksDao.getTask() // to get task
    fun filter(category:String) = itasksDao.filtercategry(category) //1
    suspend fun addTasks(tasksListModel: TasksListModel) = itasksDao.addTask(tasksListModel) // this function to add value to database
    suspend fun updateTasks(tasksListModel: TasksListModel) = itasksDao.updateTask(tasksListModel) //this function to update value to database
    suspend fun deleteTasks(tasksListModel: TasksListModel) = itasksDao.daleteTask(tasksListModel) // //this function to delete value from database

    //----------------------------------------------------------------------------------------------------
    companion object {
        private var instace: TasksRepository? = null
        fun init(context: Context) {
            if (instace == null)
                instace = TasksRepository(context)
        }

        fun get(): TasksRepository {
            return instace ?: throw Exception("Tasks Repository must be initialized ")

        }
    }

}


