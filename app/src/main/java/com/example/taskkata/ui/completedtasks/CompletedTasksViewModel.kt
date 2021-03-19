package com.example.taskkata.ui.completedtasks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.taskkata.database.TaskDao

class CompletedTasksViewModel(
    val dao: TaskDao,
    application: Application
) : AndroidViewModel(application) {

    val tasks = dao.getCompletedTasks()

}