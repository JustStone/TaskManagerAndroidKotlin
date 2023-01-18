package com.example.taskmanagerapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanagerapp.databinding.ActivityMainBinding



const val APP_PREFERENCES = "APP_PREFERENCES"
const val PREF_TASK = "TASK"
const val PREF_LIST = "LIST"
const val CURRENT_L = "CURRENT_L"
const val CURRENT_T = "CURRENT_T"


class MainActivity : AppCompatActivity(), TaskAdapter.InterfaceTask, ListAdapter.InterfaceList {
    lateinit var mActBinding : ActivityMainBinding
    private val adapterList = ListAdapter(this)
    private val adapterTask = TaskAdapter(this)
    val listsAlreadyExist = ArrayList<String>()
    val Array = ArrayList<String>()

    private var createTaskLauncher: ActivityResultLauncher<Intent>? = null
    private var createListLauncher: ActivityResultLauncher<Intent>? = null

    private lateinit var preferences: SharedPreferences




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mActBinding.root)

        preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)

        initRcvLists()
        initRcvTasks()



        createTaskLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                adapterTask.AddTask(it.data?.getSerializableExtra("tp_task" ) as TaskData)
            }
        }

        //IMPORTANT ADD ------------------------------------------------------------
//        preferences.edit()
//            .clear()
//            .apply()

        if (!preferences.contains("$PREF_LIST IMPORTANT")) {
            preferences.edit()
                .putString("$PREF_LIST IMPORTANT", "IMPORTANT")
                .putString(CURRENT_L, "IMPORTANT")
                .apply()
        }
        //IMPORTANT ADD ------------------------------------------------------------

        //Draw  lists ------------------------------------------------------------

        var arr_map = preferences.all
        arr_map.forEach { entry ->
            if (PREF_LIST in entry.key.toString()) {
                adapterList.AddList(ListData(entry.value.toString()))
            }
        }
        //Draw lists ------------------------------------------------------------

        createListLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                adapterList.AddList(it.data?.getSerializableExtra("tp_list" ) as ListData)
            }
        }

        mActBinding.bottomMenu.setOnItemSelectedListener {
            when(it.itemId){
                R.id.addList -> {
                    createListLauncher?.launch(Intent(this@MainActivity, CreateLstActivity::class.java))
                }
                R.id.addTask -> {
                    createTaskLauncher?.launch(Intent(this@MainActivity, CreateTaskActivity::class.java))
                }
                R.id.deleteList -> {}
            }
            true
        }

    }

    private fun initRcvLists(){
        mActBinding.apply {
            rcLists.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            rcLists.adapter = adapterList
        }
    }

    private fun initRcvTasks(){
        mActBinding.apply {
            rcTasks.layoutManager = GridLayoutManager(this@MainActivity,1 )
            rcTasks.adapter = adapterTask
        }
    }

    override fun clickTaskListener(task: TaskData) {
        Toast.makeText(this, "go ${task.taskTitle}", Toast.LENGTH_LONG).show()
    }

    override fun clickListListener(list : ListData){
        //Change current list ------------------------------------------------------------
        preferences.edit()
            .remove(CURRENT_L)
            .putString(CURRENT_L, list.title)
            .apply()
        //Change current list ------------------------------------------------------------
        val cur = preferences.getString(CURRENT_L, "")
        Toast.makeText(this, "Choosen $cur", Toast.LENGTH_LONG).show()


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

    override fun onDestroy() {
        super.onDestroy()
    }
}