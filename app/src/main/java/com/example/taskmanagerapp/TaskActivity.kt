package com.example.taskmanagerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.taskmanagerapp.databinding.ActivityMainBinding
import com.example.taskmanagerapp.databinding.ActivityTaskBinding

class TaskActivity : AppCompatActivity() {
    lateinit var TaskActBinding : ActivityTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TaskActBinding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(TaskActBinding.root)

        val header_task = intent.getStringExtra("header_task")
        TaskActBinding.HTask.text = header_task
    }

    fun onClickToMain(viev : View){
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)

        val i = Intent()
        i.putExtra("callback", "Sucess!")
        setResult(RESULT_OK, i)
        finish()
    }
}