package com.example.taskkata.ui.settings

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskkata.database.TaskDao
import com.example.taskkata.ui.addtask.AddItemViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class SettingsViewModelFactory (private val dao: TaskDao, private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
                return SettingsViewModel(dao, application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}