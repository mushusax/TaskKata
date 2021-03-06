package com.example.taskkata

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.taskkata.databinding.FragmentTodoItemListDialogBinding

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
class TodoItemListDialogFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentTodoItemListDialogBinding
    private lateinit var editTextTask: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentTodoItemListDialogBinding.inflate(inflater, container, false)
        editTextTask = binding.editTextTask
        return binding.root
    }

    private fun launchDateDialog() {
        val dateFragment = DatePickerFragment()
        dateFragment.show(parentFragmentManager, "datePicker")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    companion object {
        // TODO: Customize parameters
        fun newInstance(itemCount: Int): TodoItemListDialogFragment =
                TodoItemListDialogFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_ITEM_COUNT, itemCount)
                    }
                }
    }
}