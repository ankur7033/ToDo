package com.ankur.todo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Tasks.class},version = 1)
public abstract class TasksDataBase extends RoomDatabase {
    public abstract TaskDAO getTasksDAO();

    private static TasksDataBase tasksDataBase;
    public static synchronized TasksDataBase getInstance(Context context){
        if (tasksDataBase==null){
            tasksDataBase = Room.databaseBuilder(context.getApplicationContext(),
                    TasksDataBase.class,
                    "tasks").fallbackToDestructiveMigration().build();
        }
        return tasksDataBase;
    }
}