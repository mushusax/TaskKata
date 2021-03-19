package com.example.taskkata.ui.completedtasks

import android.widget.CheckBox
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.taskkata.database.Task

@BindingAdapter("description")
fun TextView.setDescription(item: Task) {
    text = item.description
}