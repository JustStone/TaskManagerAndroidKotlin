package com.example.taskmanagerapp

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.HorizontalScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskmanagerapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var mActBinding : ActivityMainBinding
//    var colorDrawable = ColorDrawable(Color.parseColor("#ffffff"))
    private val adapter = ListAdapter()

    private var idOfList = 1;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mActBinding.root)

//        supportActionBar?.setBackgroundDrawable(colorDrawable)

        initRcv()



//        pref = getSharedPreferences("TABLE", Context.MODE_PRIVATE)
//        hLst = pref?.getString("hlst", "noData")!!
    }

    private fun initRcv(){
        mActBinding.apply {
            rcLists.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            rcLists.adapter = adapter
            btnAddList.setOnClickListener{
                val list1 = ListData(idOfList, "Star")
                adapter.AddList(list1)
                idOfList++
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