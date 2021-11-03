package com.example.myapplication.database.model

import android.widget.Spinner
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
// our table
@Entity
data class TasksListModel(
    var title : String,   // place to save task title
    var subtitle:String,  // save description
    var spinner : String, // save chosen value from list of ( no yet , complete, parser completed)
    var calendar: String, // place to save date Selection from calendar
    var category:String, // save selection category
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0



    )
