package com.example.room_kotlin;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import java.util.List;

public class JavaMainViewModel extends AndroidViewModel {
    //데이터들을 다 넣을 예정
    // 원래 DB가 background에서 동작하지 않으면 에러가 나지만 테스트 용이니 메인스레드에서 작성
    private JavaAppDatabase db;

    public JavaMainViewModel(@NonNull Application application) {
        super(application);

        this.db = Room.databaseBuilder(application, JavaAppDatabase.class, "todo-db")
                    .build();
    }

    public void insert(JavaTodo todo){
        new InsertAsyncTask(db.todoDao()).execute(todo);
    }

    //db를 직접 조작하지 못하도록 감싸
    public LiveData<List<JavaTodo>> getAll(){
        return db.todoDao().getAll();
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
