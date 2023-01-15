package com.example.taskmanagerapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanagerapp.databinding.ActivityMainBinding
import java.time.LocalDate


class MainActivity : AppCompatActivity() {
    lateinit var mActBinding : ActivityMainBinding
    private val adapterList = ListAdapter()
    private var idOfList = 1;
    private val adapterTask = TaskAdapter()
    private var idOfTask = 1;
    val Array = ArrayList<String>()

    private var createTaskLauncher: ActivityResultLauncher<Intent>? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mActBinding.root)

        initRcvLists()
        initRcvTasks()

        createTaskLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                adapterTask.AddTask(it.data?.getSerializableExtra("tp_task" ) as TaskData)
            }
        }

        mActBinding.bottomMenu.setOnItemSelectedListener {
            when(it.itemId){
                R.id.addList -> {}
                R.id.addTask -> {
                createTaskLauncher?.launch(Intent(this@MainActivity, CreateTaskActivity::class.java))
                }
                R.id.deleteList -> {}
            }
            true
        }



//        pref = getSharedPreferences("TABLE", Context.MODE_PRIVATE)
//        hLst = pref?.getString("hlst", "noData")!!
    }

    private fun initRcvLists(){
        mActBinding.apply {
            rcLists.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            rcLists.adapter = adapterList
            val list1 = ListData(idOfList, "Star")
            adapterList.AddList(list1)
//                idOfList++
        }
    }

    private fun initRcvTasks(){
        mActBinding.apply {
            rcTasks.layoutManager = GridLayoutManager(this@MainActivity,1 )
            rcTasks.adapter = adapterTask
//            btnAddList.setOnClickListener{
////                val task1 = TaskData(true, "Buying", "Buy a car",
////                "add info", L, Array
////                )
////                adapterTask.AddTask(task1)
////                idOfTask++
//                createTaskLauncher?.launch(Intent(this@MainActivity, CreateTaskActivity::class.java))
//            }
        }
    }

//
//    fun saveData(hLst: String){
//        val db_lst = pref?.edit()
//        db_lst?.putString(hLst, hLst)
//        db_lst?.apply()
//    }
//
//    fun deleteItem(hLst: String){
//        val db_lst = pref?.edit()
//        db_lst?.remove(hLst)
//        db_lst?.apply()
//    }











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