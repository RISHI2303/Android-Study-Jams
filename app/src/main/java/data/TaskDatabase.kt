package data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
//import com.example.roomdatabaseexample.TagTypeConverter
import com.example.roomdatabaseexample.Task

//here we put all the things related to database together

@Database(entities = [Task::class], version = 1)
//@TypeConverters(TagTypeConverter::class)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskListDao(): TaskListDao
    abstract fun taskDetailDao(): TaskDetailDao

    //for having single instance for the object we use companion object
    companion object {
        @Volatile
        private var instance: TaskDatabase? = null

        fun getDatabase(context: Context) = instance
            ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_database"
                ).build().also { instance = it }
            }
    }
}