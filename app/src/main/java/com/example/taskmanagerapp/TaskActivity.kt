package com.example.taskmanagerapp

import android.R
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.taskmanagerapp.databinding.ActivityTaskBinding


class TaskActivity : AppCompatActivity() {
    lateinit var taskActBinding : ActivityTaskBinding
    var colorDrawable = ColorDrawable(Color.parseColor("#ffffff"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskActBinding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(taskActBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Task"
        supportActionBar?.setBackgroundDrawable(colorDrawable)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.home) finish()
        return true
    }

    fun onClickToMain(viev : View){
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
        finish()
    }
}