package com.ankur.todo;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private final TaskDAO taskDAO;


    public Repository(Application application) {
        TasksDataBase db = TasksDataBase.getInstance(application);
        taskDAO = db.getTasksDAO();
    }

    public void addTask(Tasks tasks) {
        ExecutorService excutor=Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        excutor.execute(new Runnable() {
            @Override
            public void run() {
                taskDAO.insert(tasks);
            }
        });
    }

    public void updateTask(Tasks tasks) {
        ExecutorService excutor=Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        excutor.execute(new Runnable() {
            @Override
            public void run() {
                taskDAO.update(tasks);
            }
        });
    }

    public void deleteTask(Tasks tasks) {
        ExecutorService excutor=Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        excutor.execute(new Runnable() {
            @Override
            public void run() {
                taskDAO.delete(tasks);
            }
        });
    }

    public LiveData<List<Tasks>> getAllTasks() {
        return taskDAO.getAllTasks();
    }
}
