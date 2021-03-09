package com.example.room_kotlin

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.room_kotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding // xml 파일명이 CamelCase 표기로 바뀌고 Binding이 붙

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries()
            .build()


        activityMainBinding.resultText.text = db.todoDao().getAll().toString()

        activityMainBinding.addButton.setOnClickListener{
           db.todoDao().insert(Todo(activityMainBinding.todoEdit.text.toString()))
            activityMainBinding.resultText.text = db.todoDao().getAll().toString()
        }



    }
}