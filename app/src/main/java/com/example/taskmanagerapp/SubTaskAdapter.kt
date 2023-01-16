package com.example.taskmanagerapp

import android.annotation.SuppressLint
import android.location.GnssAntennaInfo.Listener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanagerapp.databinding.SubtaskItemBinding
import com.example.taskmanagerapp.databinding.TaskItemBinding

class SubTaskAdapter : RecyclerView.Adapter<SubTaskAdapter.TaskHolder>() {
    val ArrayOfTasks = ArrayList<TaskData>()

    class TaskHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = TaskItemBinding.bind(item)

        fun bind(task: TaskData) = with(binding) {
            taskTitle.text = task.taskTitle
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskHolder(view)
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind(ArrayOfTasks[position])
    }

    override fun getItemCount(): Int {
        return ArrayOfTasks.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun AddTask(task : TaskData){
        ArrayOfTasks.add(task)
        notifyDataSetChanged()
    }


}