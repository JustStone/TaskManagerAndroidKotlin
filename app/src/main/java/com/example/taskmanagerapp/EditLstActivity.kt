package com.example.taskmanagerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.taskmanagerapp.databinding.ActivityCreateLstBinding
import com.example.taskmanagerapp.databinding.ActivityEditLstBinding

class EditLstActivity : AppCompatActivity() {
    lateinit var editLstActBinding : ActivityEditLstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editLstActBinding = ActivityEditLstBinding.inflate(layoutInflater)
        setContentView(editLstActBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Edit List"

        val clickedList = intent.getSerializableExtra("tp_click_list") as ListData

        editLstActBinding.apply {
            newListText.setText(clickedList.title)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) finish()
        return true
    }
}