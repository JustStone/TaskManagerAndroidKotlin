package com.example.taskmanagerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class TaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
    }

    fun onClickToMain(viev : View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}