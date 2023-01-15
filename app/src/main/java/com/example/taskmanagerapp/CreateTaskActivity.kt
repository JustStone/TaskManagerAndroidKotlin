package com.example.taskmanagerapp

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.RequiresApi
import com.example.taskmanagerapp.databinding.ActivityCreateTaskBinding
import java.time.LocalDate

class CreateTaskActivity : AppCompatActivity() {
    lateinit var createTaskActBinding : ActivityCreateTaskBinding
    private var color = Color.YELLOW

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createTaskActBinding = ActivityCreateTaskBinding.inflate(layoutInflater)
        setContentView(createTaskActBinding.root)
        initButtons()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Create Task"


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) finish()
        return true
    }


    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun initButtons() = with(createTaskActBinding){
        createTaskFavorBtn.setOnClickListener{
            if(createTaskFavorBtn.text == "IMPORTANT") {
                createTaskFavorBtn.text = "NOT IMPORTANT"
                createTaskFavorBtn.backgroundTintList = ColorStateList.valueOf(0xAAAAAA)
            }
            else{
                createTaskFavorBtn.text = "IMPORTANT"
                createTaskFavorBtn.backgroundTintList = ColorStateList.valueOf(Color.YELLOW)
            }
        }
        createTaskSaveBtn.setOnClickListener {
            val task : TaskData
            if (createTaskFavorBtn.text == "IMPORTANT"){
                task = TaskData (
                    true,
                    "some",
                    createTaskTitle.text.toString(),
                    createTaskInfo.text.toString(),
                    "LocalDate.now()",
                    ArrayList<String>()
                )
            }
            else{
                task = TaskData (
                    false,
                    "some",
                    createTaskTitle.text.toString(),
                    createTaskInfo.text.toString(),
                    "LocalDate.now()",
                    ArrayList<String>()
                )
            }
            val createTaskIntent = Intent().apply {
                putExtra("tp_task", task)
            }

            setResult(RESULT_OK, createTaskIntent)
            finish()
        }

    }



}