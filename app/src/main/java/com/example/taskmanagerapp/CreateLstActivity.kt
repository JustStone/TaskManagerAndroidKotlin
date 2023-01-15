package com.example.taskmanagerapp

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.taskmanagerapp.databinding.ActivityCreateLstBinding

class CreateLstActivity : AppCompatActivity() {
    lateinit var createLstActBinding : ActivityCreateLstBinding
//    var colorDrawable = ColorDrawable(Color.parseColor("#ffffff"))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createLstActBinding = ActivityCreateLstBinding.inflate(layoutInflater)
        setContentView(createLstActBinding.root)
        initButtons()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Create List"

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) finish()
        return true
    }

    private fun initButtons() = with(createLstActBinding){
        createListBtn.setOnClickListener{
            val list = ListData(newListText.text.toString())

            val createListIntent = Intent().apply {
                putExtra("tp_list", list)
            }

            setResult(RESULT_OK, createListIntent)
            finish()
        }
    }
}