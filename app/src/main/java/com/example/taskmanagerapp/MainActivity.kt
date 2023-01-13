package com.example.taskmanagerapp

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.taskmanagerapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var mActBinding : ActivityMainBinding
    var colorDrawable = ColorDrawable(Color.parseColor("#ffffff"))



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mActBinding.root)
        supportActionBar?.setBackgroundDrawable(colorDrawable)


        //Активируем homebutton

//        pref = getSharedPreferences("TABLE", Context.MODE_PRIVATE)
//        hLst = pref?.getString("hlst", "noData")!!
    }



    fun onClickToTask(viev : View){
        val intent = Intent(this, CreateLstActivity::class.java)
        startActivity(intent)
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