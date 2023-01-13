package com.example.taskmanagerapp

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.taskmanagerapp.databinding.ActivityCreateLstBinding

class CreateLstActivity : AppCompatActivity() {
    lateinit var createLstActBinding : ActivityCreateLstBinding
    var colorDrawable = ColorDrawable(Color.parseColor("#ffffff"))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createLstActBinding = ActivityCreateLstBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_create_lst)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Create List"
        supportActionBar?.setBackgroundDrawable(colorDrawable)



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) finish()
        return true
    }
}