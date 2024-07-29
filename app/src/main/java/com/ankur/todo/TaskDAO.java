package com.ankur.todo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDAO {
    @Insert
    void insert(Tasks tasks);
    @Delete
    void delete(Tasks tasks);
    @Update
    void update(Tasks task);
    @Query("SELECT*FROM tasks")
    LiveData<List<Tasks>>getAllTasks();
}