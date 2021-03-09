package com.example.room_kotlin;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// JavaTodo 객체를 관찰하고 있다가 변경 사항이 생기면 자동으로 업뎃이 되게 할 예정
@Dao
public interface JavaTodoDao {
    @Query("SELECT * FROM JavaTodo")
    LiveData<List<JavaTodo>> getAll(); // LiveData로 감싸준 getAll은 관찰 가능한 객체가 됨

    @Insert
    void insert(JavaTodo javaTodo);

    @Update
    void update(JavaTodo javaTodo);

    @Delete
    void delete(JavaTodo javaTodo);
}
