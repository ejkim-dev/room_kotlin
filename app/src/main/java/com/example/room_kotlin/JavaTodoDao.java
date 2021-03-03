package com.example.room_kotlin;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface JavaTodoDao {
    @Query("SELECT * FROM JavaTodo")
    List<JavaTodo> getAll();

    @Insert
    void insert(JavaTodo javaTodo);

    @Update
    void update(JavaTodo javaTodo);

    @Delete
    void delete(JavaTodo javaTodo);
}
