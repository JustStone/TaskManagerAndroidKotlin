package com.example.taskmanagerapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanagerapp.databinding.ListItemBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListHolder>() {
    val ArrayOfLists = ArrayList<ListData>()
    class ListHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ListItemBinding.bind(item)
        fun bind(list : ListData) = with(binding){
            ListText.text =  list.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ListHolder(view)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        holder.bind(ArrayOfLists[position])
    }

    override fun getItemCount(): Int {
        return ArrayOfLists.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun AddList(list : ListData){
        ArrayOfLists.add(list)
        notifyDataSetChanged()
    }

}