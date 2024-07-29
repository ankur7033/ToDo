package com.ankur.todo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class Tasks {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String taskName;
    private boolean isChecked;

    public Tasks(String taskName) {
        this.taskName = taskName;
    }

    public Tasks(String taskName, boolean isChecked) {
        this.taskName = taskName;
        this.isChecked = isChecked;
    }

    public Tasks() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
