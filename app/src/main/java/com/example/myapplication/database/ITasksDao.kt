package com.example.myapplication.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.database.model.TasksListModel

@Dao
interface ITasksDao {
    @Insert
    suspend fun addTask(tasksListModel: TasksListModel)
    @Query("SELECT * FROM taskslistmodel")
    fun getTask():LiveData<List<TasksListModel>>
    @Update
    suspend fun updateTask(tasksListModel: TasksListModel)
    @Delete
    suspend fun daleteTask(tasksListModel: TasksListModel)

}