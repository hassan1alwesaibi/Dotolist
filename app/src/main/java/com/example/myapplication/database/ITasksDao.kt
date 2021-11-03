package com.example.myapplication.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.database.model.TasksListModel
//Data Access Objects are the main classes where you define your database interactions
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
@Query("SELECT * FROM taskslistmodel WHERE category= :categry") //1
fun filtercategry(categry:String):LiveData<List<TasksListModel>> //1


}

