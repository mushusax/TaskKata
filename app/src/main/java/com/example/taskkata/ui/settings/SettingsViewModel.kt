package com.example.taskkata.ui.settings

import android.app.Application
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskkata.database.TaskDao
import com.example.taskkata.ui.MainActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class SettingsViewModel(
    val dao: TaskDao,
    application: Application
) : AndroidViewModel(application)  {



    fun onClear() {
        viewModelScope.launch {
            dao.clear()
        }
    }

    fun onDeleteAccount() {
        val context = getApplication<Application>().applicationContext
        AuthUI.getInstance().delete(context)
    }

}