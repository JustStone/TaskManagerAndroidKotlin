package com.example.taskmanagerapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.taskmanagerapp.databinding.ActivityEditLstBinding
import com.example.taskmanagerapp.databinding.ActivityCreateTaskBinding

class EditTaskActivity : AppCompatActivity() {
    lateinit var editTaskActBinding : ActivityCreateTaskBinding
    private lateinit var preferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editTaskActBinding = ActivityCreateTaskBinding.inflate(layoutInflater)
        setContentView(editTaskActBinding.root)

        preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Edit Task"

//
//        var result = preferences.getString("$PREF_TASK $CURRENT_T", "")
//        with(editTaskActBinding){
//            preferences.edit()
//                .remove("$PREF_TASK $CURRENT_T")
//                .putString("$PREF_TASK $CURRENT_T", )
//        }

//        preferences.edit()
//            .putString()
//        allPrefs2.forEach { entry ->
//            if (PREF_TASK in entry.key.toString()) {
//                val arr = entry.value.toString().split("^")
//                if (arr[0] == "IMPORTANT" && arr[1] == preferences.getString(CURRENT_L,"").toString()){
//                    adapterTask.AddTask(TaskData(
//                        true,
//                        arr[1],
//                        arr[2],
//                        arr[3],
//                        arr[4]
//                    ))
//                }
//                if (arr[0] == "NOTIMPORTANT"&& arr[1] == preferences.getString(CURRENT_L,"").toString()){
//                    adapterTask.AddTask(TaskData(
//                        false,
//                        arr[1],
//                        arr[2],
//                        arr[3],
//                        arr[4]
//                    ))
//                }
//            }
//        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) finish()
        return true
    }
}