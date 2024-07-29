package com.ankur.todo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private Repository myRepository;
    private LiveData<List<Tasks>> allTasks;

    public ViewModel(@NonNull Application application) {
        super(application);
        myRepository = new Repository(application);
        allTasks = myRepository.getAllTasks();
    }

    public LiveData<List<Tasks>> getAllTasks() {
        return allTasks;
    }

    public void addNewTask(Tasks tasks) {
        myRepository.addTask(tasks);
    }

    public void updateTask(Tasks task) {
        myRepository.updateTask(task);
    }

    public void deleteTask(Tasks tasks) {
        myRepository.deleteTask(tasks);
    }
}
