package com.example.taskkata.ui.today

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskkata.database.TaskDao

class TodayViewModel(
        dao: TaskDao,
        application: Application
) : AndroidViewModel(application) {

    //This is a LiveData of tasks from RoomDB
    val tasks = dao.getAllTasks()

}