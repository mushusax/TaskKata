package com.example.taskkata.ui.completedtasks

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskkata.database.Task
import com.example.taskkata.databinding.FragmentCompletedRecyclerItemBinding
import com.example.taskkata.databinding.RecyclerViewItemBinding
import com.example.taskkata.ui.today.TodayAdapter

class CompletedTasksAdapter : ListAdapter<Task, CompletedTasksAdapter.ViewHolder>(TaskDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    class ViewHolder(val binding: FragmentCompletedRecyclerItemBinding): RecyclerView.ViewHolder(binding.root) {
        private val textDescription: TextView = binding.fragmentCompletedTaskDescription

        fun bind(item: Task) {
            binding.task = item
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = FragmentCompletedRecyclerItemBinding.inflate(inflater, parent, false)
                return ViewHolder(view)
            }
        }
    }

}

class TaskDiffCallback() : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.taskId == newItem.taskId
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}