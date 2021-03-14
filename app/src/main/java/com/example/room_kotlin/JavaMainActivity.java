package com.example.room_kotlin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.room_kotlin.databinding.ActivityJavaMainBinding;
import com.example.room_kotlin.databinding.ActivityMainBinding;

import java.util.List;

public class JavaMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_java_main);를 아래와 같이 바꿈. binding 객체는 xml에 대한 정보를 다 갖고있
        ActivityJavaMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_java_main);

        // 원래 DB가 background에서 동작하지 않으면 에러가 나지만 테스트 용이니 메인스레드에서 작성
        final JavaAppDatabase javaAppDatabase = Room.databaseBuilder(this, JavaAppDatabase.class, "java-todo-db")
                .allowMainThreadQueries() // 메인 스레드에서 DB 동작하게 함
                .build();

        // 관찰하다가 UI 갱신
        // livedata 관찰자 설정 -> 람다로 변경(alt + enter)
        javaAppDatabase.todoDao().getAll().observe(this, javaTodos -> {

            //getAll()을 했을 때 결과 변경될 때마다 javaTodos 인자로 들어오고 아래 코드를 수행하면 됨
            //todoDao() 를 통해 데이터를 얻어올 수 있다. / getAll() : 모든 데이터
            binding.resultText.setText(javaTodos.toString()); // 이 코드는 계속 사용될 수 있으니 livedata를 사용하여 자동으로 갱신되게 할 예정
        });

        //버튼 클릭시 DB 에 insert
        findViewById(R.id.add_button).setOnClickListener(v -> javaAppDatabase.todoDao().insert(new JavaTodo(binding.todoEdit.getText().toString())));

    }
}