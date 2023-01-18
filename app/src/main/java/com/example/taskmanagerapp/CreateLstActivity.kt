package com.example.taskmanagerapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import com.example.taskmanagerapp.databinding.ActivityCreateLstBinding

class CreateLstActivity : AppCompatActivity() {
    lateinit var createLstActBinding : ActivityCreateLstBinding
    private lateinit var preferences: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createLstActBinding = ActivityCreateLstBinding.inflate(layoutInflater)
        setContentView(createLstActBinding.root)

        preferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Create List"

        initButtons()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) finish()
        return true
    }

    private fun initButtons() = with(createLstActBinding){
        createListBtn.setOnClickListener{
            if (!preferences.contains(PREF_LIST+" "+newListText.text.toString()) &&
                    newListText.text.toString().isNotEmpty()){

                //SHARED ------------------------------------------------------------
                preferences.edit()
                    .putString(PREF_LIST+" "+newListText.text.toString(), newListText.text.toString())
                    .remove(CURRENT_L)
                    .putString(CURRENT_L, newListText.text.toString())
                    .apply()
                //SHARED ------------------------------------------------------------

                val list = ListData(newListText.text.toString())

                val createListIntent = Intent().apply {
                    putExtra("tp_list", list)
                }

                setResult(RESULT_OK, createListIntent)

                finish()
            }

        }
    }
}