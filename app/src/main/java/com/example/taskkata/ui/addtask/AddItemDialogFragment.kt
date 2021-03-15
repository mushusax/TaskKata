package com.example.taskkata.ui.addtask

import android.app.Application
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.taskkata.database.Task
import com.example.taskkata.database.TaskDao
import com.example.taskkata.database.TaskDatabase
import com.example.taskkata.databinding.FragmentAddItemDialogBinding
import com.example.taskkata.ui.today.TodayViewModel

// TODO: Customize parameter argument names
const val ARG_ITEM_COUNT = "item_count"

/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    TodoItemListDialogFragment.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 */
class AddItemDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentAddItemDialogBinding
    private lateinit var editTextTask: EditText
    private lateinit var btnAddItem: Button
    private lateinit var viewModel: AddItemViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentAddItemDialogBinding.inflate(inflater, container, false)

        //Get references
        val application: Application = requireNotNull(this.activity).application
        val dao: TaskDao = TaskDatabase.getInstance(application).taskDatabaseDao
        val viewModelFactory = AddItemViewModelFactory(dao, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AddItemViewModel::class.java)

        //Set lifecycle owner
        binding.lifecycleOwner = this

        //Set up data binding
        binding.viewModel = viewModel

        //Update value of textDescription in the viewmodel
        editTextTask = binding.editTextTask
        editTextTask.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.editTextDescription.value = s.toString()
            }
        })

        btnAddItem = binding.btnAddTask
        btnAddItem.setOnClickListener { onAddClick() }

        return binding.root
    }

    private fun onAddClick() {
        viewModel.onCreateTask()
        dismiss()
    }


    private fun launchDateDialog() {
        val dateFragment = DatePickerFragment()
        dateFragment.show(parentFragmentManager, "datePicker")
    }

    companion object {
        // TODO: Customize parameters
        fun newInstance(itemCount: Int): AddItemDialogFragment =
                AddItemDialogFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_ITEM_COUNT, itemCount)
                    }
                }
    }
}