package com.example.room_kotlin;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class JavaTodo {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;

    public JavaTodo(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // toStrig을 재정의 해서 내용을 확인 할 수 있도록
    @Override
    public String toString() {
        return "TodoJava{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
