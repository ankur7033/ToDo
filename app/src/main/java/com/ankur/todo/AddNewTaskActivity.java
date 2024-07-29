package com.ankur.todo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.ankur.todo.databinding.ActivityAddNewTaskBinding;

public class AddNewTaskActivity extends AppCompatActivity {

    private ActivityAddNewTaskBinding binding;
    private AddNewTaskClickHandler handler;
    private Tasks tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_task);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tasks = new Tasks();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_task);
        ViewModel viewModel = new ViewModelProvider(this).get(ViewModel.class);
        handler = new AddNewTaskClickHandler(tasks, this, viewModel);
        binding.setTasks(tasks);
        binding.setClickHandler(handler);
    }
}
