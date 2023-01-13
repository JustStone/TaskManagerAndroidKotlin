package com.example.taskmanagerapp

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.taskmanagerapp.databinding.ActivityCreateTaskBinding

class CreateTaskActivity : AppCompatActivity() {
    lateinit var createTaskActBinding : ActivityCreateTaskBinding
    var colorDrawable = ColorDrawable(Color.parseColor("#ffffff"))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createTaskActBinding = ActivityCreateTaskBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_create_task)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Create Task"
        supportActionBar?.setBackgroundDrawable(colorDrawable)



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) finish()
        return true
    }
}