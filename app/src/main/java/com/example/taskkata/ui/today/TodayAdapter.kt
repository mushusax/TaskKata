package com.example.taskkata.ui.today

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.taskkata.R
import com.example.taskkata.database.Task

class TodayAdapter: RecyclerView.Adapter<TodayAdapter.ViewHolder>() {

    //Hold the data of all tasks
    var data = listOf<Task>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val card: CardView = itemView.findViewById(R.id.recycler_card)
        val checkBox: CheckBox = itemView.findViewById(R.id.card_checkbox)
        val textView: TextView = itemView.findViewById(R.id.card_task_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //Inflate list layout and return viewholder
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.checkBox.isChecked = item.isCompleted
        holder.textView.text = item.description
    }

    override fun getItemCount(): Int {
        return data.size
    }
}