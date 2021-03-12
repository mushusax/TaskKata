package com.example.taskkata.ui.completedtasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CompletedTasksViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is CompletedTasks Fragment"
    }
    val text: LiveData<String> = _text
}