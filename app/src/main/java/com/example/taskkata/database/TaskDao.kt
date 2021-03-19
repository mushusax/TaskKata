package com.example.taskkata.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Query("SELECT * FROM tasks WHERE :id = taskId")
    suspend fun getTask(id: Int): Task?

    @Query("SELECT * FROM tasks ORDER BY taskId")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM tasks WHERE is_completed = 1")
    fun getCompletedTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM tasks WHERE is_completed = 0")
    fun getIncompletedTasks(): LiveData<List<Task>>

    @Query("DELETE FROM tasks")
    suspend fun clear()
}