package com.example.room_kotlin;

import androidx.room.PrimaryKey;

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

    @Override
    public String toString() {
        return "TodoJava{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
