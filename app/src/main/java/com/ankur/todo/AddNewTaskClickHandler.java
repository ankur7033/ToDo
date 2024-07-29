package com.ankur.todo;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

public class AddNewTaskClickHandler {
    Tasks tasks;
    Context context;
    ViewModel viewModel;

    public AddNewTaskClickHandler(Tasks tasks, Context context, ViewModel viewModel) {
        this.tasks = tasks;
        this.context = context;
        this.viewModel = viewModel;
    }

    public void onSubmitBtnClicked(View view) {
        if (tasks.getTaskName() == null) {
            Toast.makeText(context, "Field cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            Intent i = new Intent(context, MainActivity.class);
            Tasks T = new Tasks(tasks.getTaskName());
            viewModel.addNewTask(T);
            context.startActivity(i);
        }
    }
}
