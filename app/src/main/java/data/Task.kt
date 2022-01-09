package com.example.roomdatabaseexample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//for refering the the variables below we use enum classes
enum class SortColumn{
    Title, Priority
}

enum class TaskStatus{
    Open, Closed
}

enum class PriorityLevel{
    Low, Medium, High
}

//make id as Primary key and set it to auto generate
//and declare other variables
//columnInfo is used to display that variable in database

@Entity(tableName = "task")
data class Task(@PrimaryKey(autoGenerate = true) val id: Long,
                val title: String,
                val detail: String,
                //@ColumnInfo(name = "priority") val priority: Int,
                val priority: Int,
                val status: Int)
                 //val tags: ArrayList<String>)

/*class TagTypeConverter {
    @TypeConverter
    fun fromString(value: String?): ArrayList<String>{
        val listType = object : TypeToken<ArrayList<String>>(){}.type
        return Gson().fromJson(value,listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String?>): String{
        return Gson().toJson(list)
    }
}*/