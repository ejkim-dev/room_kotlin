package com.example.room_kotlin

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.room_kotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding // xml 파일명이 CamelCase 표기로 바뀌고 Binding이 붙

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
        ).build()

        db.todoDao().getAll().observe(this, Observer {
            activityMainBinding.resultText.text = it.toString()
        })


        activityMainBinding.addButton.setOnClickListener{
            //Dispatchers.IO -> 백그라운드 스레드
            lifecycleScope.launch(Dispatchers.IO){
                db.todoDao().insert(Todo(activityMainBinding.todoEdit.text.toString()))
            }
        }



    }
}