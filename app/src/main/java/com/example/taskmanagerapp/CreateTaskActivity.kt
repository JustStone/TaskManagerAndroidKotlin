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

        if (preferences.getString(TED, "") == "1"){
            val temp = preferences.getString("$PREF_TASK ${preferences.getString(CURRENT_T,"")}", "")
            val ara = temp?.split("^")
            if(ara?.get(0).toString() != "IMPORTANT") {
                createTaskActBinding.createTaskFavorBtn.text = "NOT IMPORTANT"
                createTaskActBinding.createTaskFavorBtn.backgroundTintList = ColorStateList.valueOf(0xAAAAAA)
            }
            else{
                createTaskActBinding.createTaskFavorBtn.text = "IMPORTANT"
                createTaskActBinding.createTaskFavorBtn.backgroundTintList = ColorStateList.valueOf(Color.YELLOW)
            }
            createTaskActBinding.createTaskTitle.setText(ara?.get(2).toString())
            createTaskActBinding.createTaskInfo.setText(ara?.get(3).toString())
            createTaskActBinding.createTaskDate.setText(ara?.get(4).toString())

        }


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

        createTaskDeleteBtn.setOnClickListener{
            preferences.edit()
                .remove("$PREF_TASK ${preferences.getString(CURRENT_T,"")}")
                .apply()
            finish()


        }

        createTaskSaveBtn.setOnClickListener {
            if (createTaskTitle.text.toString().isNotEmpty() &&
                createTaskTitle.text.toString().length <= 30 &&
                (!preferences.contains(PREF_TASK+" "+createTaskTitle.text.toString()) or
                        (preferences.getString(TED, "") == "1")))
            {
                if(preferences.getString(TED, "") == "1"){
                    preferences.edit()
                        .remove("$PREF_TASK ${preferences.getString(CURRENT_T,"")}")
                        .apply()
                }
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