package com.example.room_kotlin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.os.AsyncTask;
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
        final JavaAppDatabase db = Room.databaseBuilder(this, JavaAppDatabase.class, "java-todo-db")
                .build();

        // 관찰하다가 UI 갱신
        // livedata 관찰자 설정 -> 람다로 변경(alt + enter)
        db.todoDao().getAll().observe(this, javaTodos -> {

            //getAll()을 했을 때 결과 변경될 때마다 javaTodos 인자로 들어오고 아래 코드를 수행하면 됨
            //todoDao() 를 통해 데이터를 얻어올 수 있다. / getAll() : 모든 데이터
            binding.resultText.setText(javaTodos.toString()); // 이 코드는 계속 사용될 수 있으니 livedata를 사용하여 자동으로 갱신되게 할 예정
        });

        //버튼 클릭시 DB 에 insert -> Backgoroud에서 처리하도록 수정할 예정
        // 자바에서 백그라운드로 돌리는 방법 1. thread 사용 / 2. AsyncTask 가 있는데 AsyncTask를 사용할 예정
        findViewById(R.id.add_button).setOnClickListener(v -> {
                    new InsertAsyncTask(db.todoDao())
                            .execute(new JavaTodo(binding.todoEdit.getText().toString()));
                });

    }

    // 추후 Update & Delete도 이렇게 만들면
    //JavaTodo 객체를 넘겨받아서 insert를 해야함. 중간에 처리할 데이터, 프로그래에 처리할 값 필요 없으면 'Void', 리턴할 결과물 없으면 'Void'
    private static class InsertAsyncTask extends AsyncTask<JavaTodo, Void, Void> {

        //생성자를 만들어서 Dao 객체를 받도록 함
        private JavaTodoDao mTodoDao;

        //생성자에서 TodoDao를 받음
        public InsertAsyncTask(JavaTodoDao mTodoDao) {
            this.mTodoDao = mTodoDao;
        }

        // 여기서 비동기 처리를 함
        // javaTodos로 넘어온거 ... 스프레드 연산자로 넘어오면 배열로 담겨져서 넘어오고,
        @Override
        protected Void doInBackground(JavaTodo... javaTodos) {
            mTodoDao.insert(javaTodos[0]); // 우리는 하나만 보낼 것이기 때문에 0번 인덱스 인서트
            return null;
        }
    }
}