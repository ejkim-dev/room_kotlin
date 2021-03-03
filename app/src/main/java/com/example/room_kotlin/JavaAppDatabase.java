package com.example.room_kotlin;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {JavaTodo.class}, version = 1)
public abstract class JavaAppDatabase extends RoomDatabase {
  public abstract JavaTodoDao todoDao();
}
