package com.example.room_kotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Room

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "database-name"
    ).build()

    fun getAll(): LiveData<List<Todo>> {
        return db.todoDao().getAll();
    }

    // MainActivity.kt 에서 코루틴 밖으로 빼면 안되게 만들기
    // suspend가 붙은 아이들은 반드시 코루틴 스코프안에서 실행되어야함
    suspend fun insert(todo: Todo) {
        db.todoDao().insert(todo)
    }
}