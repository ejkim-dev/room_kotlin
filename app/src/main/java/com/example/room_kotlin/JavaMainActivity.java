package com.example.room_kotlin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;

public class JavaMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_main);

        JavaAppDatabase javaAppDatabase = Room.databaseBuilder(this, JavaAppDatabase.class, "java-todo-db").build();
    }
}