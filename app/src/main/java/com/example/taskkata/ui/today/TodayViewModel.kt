package com.example.taskkata.ui.today

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.taskkata.database.TaskDao
import kotlinx.coroutines.launch

class TodayViewModel(
        val dao: TaskDao,
        application: Application
) : AndroidViewModel(application) {

    //This is a LiveData of tasks from RoomDB
    val tasks = dao.getIncompletedTasks()

    fun onTaskClicked() {
        Log.i("TodayVM", "Task Clicked")
    }

    fun onCheckBoxClicked(taskId: Int) {
        Log.i("TodayVM", "Checkbox Clicked")

        //mark task as complete
        viewModelScope.launch {
           markComplete(taskId)
        }

        
    }

    private suspend fun markComplete(taskId: Int) {
        val t = dao.getTask(taskId)
        if (t != null) {
            t.isCompleted = true
            dao.updateTask(t)
        }
    }
}