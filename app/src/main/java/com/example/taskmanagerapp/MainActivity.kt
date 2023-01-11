package com.example.taskmanagerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.example.taskmanagerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var MActBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MActBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(MActBinding.root)
    }
    fun onClickToTask(viev : View){
        val intent = Intent(this, TaskActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }
}