package com.example.taskkata.ui.completedtasks

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.taskkata.R
import com.example.taskkata.database.TaskDao
import com.example.taskkata.database.TaskDatabase
import com.example.taskkata.databinding.FragmentCompletedBinding

class CompletedTasksFragment : Fragment() {

    private lateinit var binding: FragmentCompletedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompletedBinding.inflate(inflater)

        //Get references
        val application: Application = requireNotNull(this.activity).application
        val dao: TaskDao = TaskDatabase.getInstance(application).taskDatabaseDao
        val viewModelFactory = CompletedTasksViewModelFactory(dao, application)
        val viewModel: CompletedTasksViewModel = ViewModelProvider(this, viewModelFactory).get(CompletedTasksViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        //RecyclerView
        val adapter = CompletedTasksAdapter()
        binding.completedRecyclerView.adapter = adapter

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