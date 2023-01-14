package com.example.taskmanagerapp

import android.os.Build
import android.os.Bundle
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
    val Array = ArrayList<TaskData>()



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mActBinding.root)
        initRcvLists()
        initRcvTasks()



//        pref = getSharedPreferences("TABLE", Context.MODE_PRIVATE)
//        hLst = pref?.getString("hlst", "noData")!!
    }

    private fun initRcvLists(){
        mActBinding.apply {
            rcLists.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            rcLists.adapter = adapterList
//            btnAddList.setOnClickListener{
//                val list1 = ListData(idOfList, "Star")
//                adapterList.AddList(list1)
//                idOfList++
//            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initRcvTasks(){
        mActBinding.apply {
            rcTasks.layoutManager = GridLayoutManager(this@MainActivity,1 )
            rcTasks.adapter = adapterTask
            btnAddList.setOnClickListener{
                val task1 = TaskData(idOfTask, true, "Buying", "Buy a car",
                "add info", LocalDate.now(), Array
                )
                adapterTask.AddTask(task1)
                idOfTask++
            }
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