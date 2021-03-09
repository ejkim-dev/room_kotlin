package com.example.room_kotlin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class JavaMainActivity extends AppCompatActivity {
    private EditText mTodoEditText;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_main);

        mTodoEditText = findViewById(R.id.todo_edit);
        mResultTextView = findViewById(R.id.result_text);

        // 원래 DB가 background에서 동작하지 않으면 에러가 나지만 테스트 용이니 메인스레드에서 작성
        JavaAppDatabase javaAppDatabase = Room.databaseBuilder(this, JavaAppDatabase.class, "java-todo-db")
                .allowMainThreadQueries() // 메인 스레드에서 DB 동작하게 함
                .build();

        //todoDao() 를 통해 데이터를 얻어올 수 있다. / getAll() : 모든 데이
        mResultTextView.setText(javaAppDatabase.todoDao().getAll().toString());

        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                javaAppDatabase.todoDao().insert(new JavaTodo(mTodoEditText.getText().toString()));
                mResultTextView.setText(javaAppDatabase.todoDao().getAll().toString());

            }
        });

    }
}