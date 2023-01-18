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
const val PREF_SUBT = "SUBT"
const val CURRENT_L = "CURRENT_L"
const val CURRENT_T = "CURRENT_T"

const val TASKSTAR = "TASKSTAR"
const val TASKLST = "TASKLST"
const val TASKTITLE = "TASKTITLE"
const val TASKINFO = "TASKINFO"
const val TASKDATE = "TASKDATE"






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

        if (!preferences.contains("$PREF_LIST Tasks")) {
            preferences.edit()
//                .putString("$PREF_LIST Tasks", "Tasks")
                .putString(CURRENT_L, "Tasks")
                .apply()
        }
        //IMPORTANT ADD ------------------------------------------------------------

        //Draw  lists ------------------------------------------------------------

        adapterList.AddList(ListData("IMPORTANT"))
        adapterList.AddList(ListData("Tasks"))


        val allPrefs = preferences.all
        allPrefs.forEach { entry ->
            if (PREF_LIST in entry.key.toString()) {
                adapterList.AddList(ListData(entry.value.toString()))
            }
        }
        //Draw lists ------------------------------------------------------------

        //Draw  tasks ------------------------------------------------------------
        val allPrefs2 = preferences.all
        allPrefs2.forEach { entry ->
            if (PREF_TASK in entry.key.toString()) {
                val arr = entry.value.toString().split("^")
                if (arr[0] == "IMPORTANT" && arr[1] == preferences.getString(CURRENT_L,"").toString()){
                    adapterTask.AddTask(TaskData(
                        true,
                        arr[1],
                        arr[2],
                        arr[3],
                        arr[4]
                    ))
                }
                if (arr[0] == "NOTIMPORTANT"&& arr[1] == preferences.getString(CURRENT_L,"").toString()){
                    adapterTask.AddTask(TaskData(
                        false,
                        arr[1],
                        arr[2],
                        arr[3],
                        arr[4]
                    ))
                }
            }
        }
        //Draw tasks ------------------------------------------------------------

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
                R.id.deleteList -> {
                    if (preferences.getString(CURRENT_L, "") != "IMPORTANT" && preferences.getString(CURRENT_L, "") != "Tasks"){
                        //Clear  tasks ------------------------------------------------------------
                        val allPrefs5 = preferences.all
                        allPrefs5.forEach { entry ->
                            if (PREF_TASK in entry.key.toString()) {
                                val arr = entry.value.toString().split("^")
                                if (arr[0] == "IMPORTANT"){
                                    adapterTask.DeleteTask(TaskData(
                                        true,
                                        arr[1],
                                        arr[2],
                                        arr[3],
                                        arr[4]
                                    ))
                                }
                                else{
                                    adapterTask.DeleteTask(TaskData(
                                        false,
                                        arr[1],
                                        arr[2],
                                        arr[3],
                                        arr[4]
                                    ))
                                }
                            }
                        }
                        //Clear  tasks ------------------------------------------------------------
                        preferences.edit()
                            .remove(PREF_LIST+" "+preferences.getString(CURRENT_L, ""))
                            .apply()
                        adapterList.DeleteList(ListData(preferences.getString(CURRENT_L, "").toString()))
                        preferences.edit()
                            .remove(CURRENT_L)
                            .putString(CURRENT_L, "Tasks")
                            .apply()
                        //Draw  tasks ------------------------------------------------------------
                        val allPrefs3 = preferences.all
                        allPrefs3.forEach { entry ->
                            if (PREF_TASK in entry.key.toString()) {
                                val arr = entry.value.toString().split("^")
                                if (arr[0] == "IMPORTANT" && arr[1] == preferences.getString(CURRENT_L,"").toString()){
                                    adapterTask.AddTask(TaskData(
                                        true,
                                        arr[1],
                                        arr[2],
                                        arr[3],
                                        arr[4]
                                    ))
                                }
                                if (arr[0] == "NOTIMPORTANT"&& arr[1] == preferences.getString(CURRENT_L,"").toString()){
                                    adapterTask.AddTask(TaskData(
                                        false,
                                        arr[1],
                                        arr[2],
                                        arr[3],
                                        arr[4]
                                    ))
                                }
                            }
                        }
                        //Draw  tasks ------------------------------------------------------------

                    }
                    else{
                        Toast.makeText(this, "You cannot remove the base list", Toast.LENGTH_LONG).show()
                    }
                }
            }
            true
        }

    }
    //Clear tasks ------------------------------------------------------------
    //Clear tasks ------------------------------------------------------------




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
        //Change current task ------------------------------------------------------------
        if (preferences.contains(CURRENT_T)){
            preferences.edit()
                .remove(CURRENT_T)
                .putString(CURRENT_T, task.taskTitle)
                .apply()
        }
//        Change current task ------------------------------------------------------------
        Toast.makeText(this, task.taskTitle, Toast.LENGTH_LONG).show()
    }

    override fun clickListListener(list : ListData){
        //Clear tasks ------------------------------------------------------------
        val allPrefs4 = preferences.all
        allPrefs4.forEach { entry ->
            if (PREF_TASK in entry.key.toString()) {
                val arr = entry.value.toString().split("^")
                if (arr[0] == "IMPORTANT"){
                    adapterTask.DeleteTask(TaskData(
                        true,
                        arr[1],
                        arr[2],
                        arr[3],
                        arr[4]
                    ))
                }
                else{
                    adapterTask.DeleteTask(TaskData(
                        false,
                        arr[1],
                        arr[2],
                        arr[3],
                        arr[4]
                    ))
                }
            }
        }
        //Clear tasks ------------------------------------------------------------


        //Change current list ------------------------------------------------------------
        if (list.title != "IMPORTANT") {
            preferences.edit()
                .remove(CURRENT_L)
                .putString(CURRENT_L, list.title)
                .apply()
        //Change current list ------------------------------------------------------------

        //Draw  tasks ------------------------------------------------------------
            val allPrefs6 = preferences.all
            allPrefs6.forEach { entry ->
                if (PREF_TASK in entry.key.toString()) {
                    val arr = entry.value.toString().split("^")
                    if (arr[0] == "IMPORTANT" && arr[1] == preferences.getString(CURRENT_L,"").toString()){
                        adapterTask.AddTask(TaskData(
                            true,
                            arr[1],
                            arr[2],
                            arr[3],
                            arr[4]
                        ))
                    }
                    if (arr[0] == "NOTIMPORTANT" && arr[1] == preferences.getString(CURRENT_L,"").toString()){
                            adapterTask.AddTask(TaskData(
                                false,
                                arr[1],
                                arr[2],
                                arr[3],
                                arr[4]
                            ))
                        }
                }
            }
        //Draw  tasks ------------------------------------------------------------

        }
        else{
//      выводим только избранные ----------------------
            val allPrefs5 = preferences.all
            allPrefs5.forEach { entry ->
                if (PREF_TASK in entry.key.toString()) {
                    val arr = entry.value.toString().split("^")
                    if (arr[0] == "IMPORTANT"){
                        adapterTask.AddTask(TaskData(
                            true,
                            arr[1],
                            arr[2],
                            arr[3],
                            arr[4]
                        ))
                    }
                }
            }
        }

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