package com.example.taskkata

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.taskkata.database.Task
import com.example.taskkata.database.TaskDao
import com.example.taskkata.database.TaskDatabase
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var dao: TaskDao
    private lateinit var db: TaskDatabase

    @Before
    fun initialize() {
        //Get Application Context instead of activity
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        //Use in memory database. Allow Main Thread queries only for testing
        db = Room.inMemoryDatabaseBuilder(context, TaskDatabase::class.java).allowMainThreadQueries().build()
        dao = db.taskDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun destroy() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun test1() {
        val task1 = Task(description = "Clean Room", isCompleted = false)
        dao.insertTask(task1)
        assertEquals(dao.getRandomTask()?.description, task1.description)
    }



}