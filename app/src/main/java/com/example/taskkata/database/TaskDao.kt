package com.example.taskkata.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Insert
    fun insertTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Update
    fun updateTask(task: Task)

    @Query("SELECT * FROM tasks ORDER BY taskId DESC LIMIT 1")
    fun getRandomTask(): Task?

    @Query("SELECT * FROM tasks ORDER BY taskId")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("DELETE FROM tasks")
    fun clear()
}