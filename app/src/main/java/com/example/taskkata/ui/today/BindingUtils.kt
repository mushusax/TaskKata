package com.example.taskkata.ui.today

import android.widget.CheckBox
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.taskkata.database.Task

@BindingAdapter("description")
fun TextView.setDescription(item: Task) {
    text = item.description
}

@BindingAdapter("complete")
fun CheckBox.setComplete(item: Task) {
    isChecked = item.isCompleted
}