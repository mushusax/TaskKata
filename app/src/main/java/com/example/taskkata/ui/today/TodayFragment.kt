package com.example.taskkata.ui.today

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.taskkata.database.TaskDao
import com.example.taskkata.database.TaskDatabase
import com.example.taskkata.databinding.FragmentTodayBinding

class TodayFragment : Fragment() {

    private lateinit var binding: FragmentTodayBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodayBinding.inflate(inflater)

        //Get references to Application, Database, and viewmodel
        val application: Application = requireNotNull(this.activity).application
        val dao: TaskDao = TaskDatabase.getInstance(application).taskDatabaseDao
        val viewModelFactory = TodayViewModelFactory(dao, application)
        val viewModel: TodayViewModel = ViewModelProvider(this, viewModelFactory).get(TodayViewModel::class.java)

        //Set lifecycle owner of binding to the activity
        binding.lifecycleOwner = this

        //set up databinding
        binding.viewModel = viewModel


        //RecyclerView
        val adapter = TodayAdapter(TaskListener( {
            viewModel.onTaskClicked()
        }, {
            viewModel.onCheckBoxClicked(it)
        }))

        binding.todayRecyclerView.adapter = adapter
        //Set observer
        viewModel.tasks.observe(viewLifecycleOwner, {
            //When a change is made, update the data in adapter
            it?.let {
                adapter.submitList(it)
            }
        })

        return binding.root
    }
}