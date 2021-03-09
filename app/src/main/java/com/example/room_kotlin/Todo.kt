package com.example.room_kotlin

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    // data class 는 getter setter 재정의를 안해도 되게 함

    var title: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}