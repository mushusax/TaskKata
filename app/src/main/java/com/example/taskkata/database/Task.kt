package com.example.taskkata.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val taskId: Int = 0,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "is_completed")
    var isCompleted: Boolean
)
