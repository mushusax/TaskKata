package com.example.taskkata.ui.addtask

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskkata.database.TaskDao
import java.lang.IllegalArgumentException

class AddItemViewModelFactory(
    private val dao: TaskDao,
    private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddItemViewModel::class.java)) {
            return AddItemViewModel(dao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}