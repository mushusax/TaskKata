package com.example.taskkata.ui.today

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskkata.R
import com.example.taskkata.database.Task

class TodayAdapter: ListAdapter<Task, TodayAdapter.ViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Inflate list layout and return viewholder
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(holder, item)
    }



    class ViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
        val card: CardView = itemView.findViewById(R.id.recycler_card)
        private val checkBox: CheckBox = itemView.findViewById(R.id.card_checkbox)
        private val textView: TextView = itemView.findViewById(R.id.card_task_description)

        fun bind(
                holder: ViewHolder,
                item: Task
        ) {
            holder.checkBox.isChecked = item.isCompleted
            holder.textView.text = item.description
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.recycler_view_item, parent, false)
                return ViewHolder(view)
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



}