package com.example.myapplication.database.model

import android.widget.Spinner
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class TasksListModel(
    val title : String,
    val subtitle:String,
    var seeit:Boolean,
    val spinner : String,
    val calendar: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0



    )
