package com.example.taskkata.ui.addtask

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.taskkata.database.Task
import com.example.taskkata.database.TaskDao
import kotlinx.coroutines.launch

class AddItemViewModel (
    val dao: TaskDao,
    application: Application
) : AndroidViewModel(application) {


    var editTextDescription: MutableLiveData<String> = MutableLiveData<String>()

    fun onCreateTask() {
        editTextDescription.value?.let { Log.i("VM", it) }
        viewModelScope.launch {
            insertTask(editTextDescription.value)
        }
    }

    private suspend fun insertTask(_description: String?) {
        if(_description == null) return;
        val task = Task(description=_description, isCompleted = false)
        dao.insertTask(task)
    }

}