package com.example.taskmanagerapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.GridLayoutManager
import com.example.taskmanagerapp.databinding.ActivityCreateTaskBinding

class CreateTaskActivity : AppCompatActivity(), TaskAdapter.InterfaceTask{
    lateinit var createTaskActBinding : ActivityCreateTaskBinding
    private val adapterSubTask = TaskAdapter(this)
    private var arraySubtasks = ArrayList<String>()

    private lateinit var preferences: SharedPreferences


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createTaskActBinding = ActivityCreateTaskBinding.inflate(layoutInflater)
        setContentView(createTaskActBinding.root)

        initButtons()
        initRcvSubTasks()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Create Task"

        preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) finish()
        return true
    }

    private fun initRcvSubTasks(){
        createTaskActBinding.apply {
            rcSubTasks.layoutManager = GridLayoutManager(this@CreateTaskActivity,1 )
            rcSubTasks.adapter = adapterSubTask
            addSubtaskBtn.setOnClickListener{
                if (titleSubtask.text.toString().isNotEmpty() &&
                    !arraySubtasks.contains(titleSubtask.text.toString())&&
                    titleSubtask.text.toString().length <= 25){
                    val subTask = TaskData (
                        false,
                        "some",
                        titleSubtask.text.toString(),
                        createTaskInfo.text.toString(),
                        "LocalDate.now()",
                    )
                    adapterSubTask.AddTask(subTask)
                    arraySubtasks.add(titleSubtask.text.toString())
                }
            }


        }
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
            if (createTaskTitle.text.toString().isNotEmpty() &&
                createTaskTitle.text.toString().length <= 30 &&
                !preferences.contains(PREF_TASK+" "+createTaskTitle.text.toString()))
            {
                //SAVE TASK ------------------------------------------------------------
                if (createTaskFavorBtn.text.toString() == "IMPORTANT") {
                    preferences.edit()
                        .putString(
                            PREF_TASK+" "+createTaskTitle.text.toString(),
                            "IMPORTANT"+"^"+
                            preferences.getString(CURRENT_L,"").toString()+"^"+
                            createTaskTitle.text.toString()+"^"+
                            createTaskInfo.text.toString()+"^"+
                            createTaskDate.text.toString())
                        .apply()
                }
                else{
                    preferences.edit()
                        .putString(
                            PREF_TASK+" "+createTaskTitle.text.toString(),
                            "NOTIMPORTANT"+"^"+
                            preferences.getString(CURRENT_L,"").toString()+"^"+
                            createTaskTitle.text.toString()+"^"+
                            createTaskInfo.text.toString()+"^"+
                            createTaskDate.text.toString())
                        .apply()
                }


//                    .putString(PREF_TASK+" "+createTaskTitle.text.toString()+" "+TASKTITLE, createTaskTitle.text.toString())
//                    .putBoolean(PREF_TASK+" "+createTaskTitle.text.toString()+" "+TASKSTAR, createTaskFavorBtn.text == "IMPORTANT")
//                    .putString(PREF_TASK+" "+createTaskTitle.text.toString()+" "+TASKLST, preferences.getString(CURRENT_L,"").toString())
//                    .putString(PREF_TASK+" "+createTaskTitle.text.toString()+" "+TASKINFO, createTaskInfo.text.toString())
//                    .putString(PREF_TASK+" "+createTaskTitle.text.toString()+" "+TASKDATE, createTaskDate.text.toString())
                //SAVE TASK ------------------------------------------------------------
                val task : TaskData
                if (createTaskFavorBtn.text == "IMPORTANT"){
                    task = TaskData (
                        true,
                        preferences.getString(CURRENT_L,"").toString(),
                        createTaskTitle.text.toString(),
                        createTaskInfo.text.toString(),
                        createTaskDate.text.toString(),
                    )
                }
                else{
                    task = TaskData (
                        false,
                        preferences.getString(CURRENT_L,"").toString(),
                        createTaskTitle.text.toString(),
                        createTaskInfo.text.toString(),
                        createTaskDate.text.toString(),
                    )
                }

                //+не добавлять существующие


                val createTaskIntent = Intent().apply {
                    putExtra("tp_task", task)
                }

                setResult(RESULT_OK, createTaskIntent)
                finish()
            }



        }
    }

    override fun clickTaskListener(task : TaskData){
        Toast.makeText(this, "goz ${task.taskTitle}", Toast.LENGTH_LONG).show()

    }



}