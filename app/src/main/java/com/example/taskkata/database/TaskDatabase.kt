package com.example.taskkata.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1, exportSchema = true)
abstract class TaskDatabase : RoomDatabase() {
    abstract val taskDatabaseDao: TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getInstance(context: Context): TaskDatabase {
            //Only 1 thread of execution runs
            synchronized(this) {
                var instance = INSTANCE

                //If there is no instance, build one
                if(instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, TaskDatabase::class.java, "task_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}