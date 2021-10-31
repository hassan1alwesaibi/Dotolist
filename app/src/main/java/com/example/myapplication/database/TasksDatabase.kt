package com.example.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.database.model.TasksListModel

@Database(entities = [TasksListModel::class],version = 5)
abstract class TasksDatabase:RoomDatabase() {
    abstract fun iTasksDao():ITasksDao

}