package com.example.taskkata.ui.completedtasks

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskkata.database.TaskDao
import com.example.taskkata.ui.today.TodayViewModel
import java.lang.IllegalArgumentException
import kotlin.jvm.Throws

@Suppress("UNCHECKED_CAST")
class CompletedTasksViewModelFactory(val dao: TaskDao, val application:Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CompletedTasksViewModel::class.java)) {
            return CompletedTasksViewModel(dao, application) as T
        }
        throw IllegalArgumentException("Not a valid ViewModel")
    }

}

