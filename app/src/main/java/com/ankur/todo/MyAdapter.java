package com.ankur.todo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ankur.todo.databinding.MyTasksBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.TaskViewHolder> {
    private ArrayList<Tasks> tasks;
    private ViewModel viewModel;

    public MyAdapter(ArrayList<Tasks> tasks, ViewModel viewModel) {
        this.tasks = tasks;
        this.viewModel = viewModel;
    }

    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyTasksBinding myTasksBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.my_tasks,
                parent,
                false
        );
        return new TaskViewHolder(myTasksBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Tasks currentTask = tasks.get(position);
        holder.myTasksBinding.setTask(currentTask);

        holder.myTasksBinding.checkBox.setOnCheckedChangeListener(null);
        holder.myTasksBinding.checkBox.setChecked(currentTask.isChecked());

        holder.myTasksBinding.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                currentTask.setChecked(isChecked);
                viewModel.updateTask(currentTask);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (tasks != null) return tasks.size();
        else return 0;
    }

    public void setTasks(ArrayList<Tasks> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        private MyTasksBinding myTasksBinding;

        public TaskViewHolder(MyTasksBinding myTasksBinding) {
            super(myTasksBinding.getRoot());
            this.myTasksBinding = myTasksBinding;
        }
    }
}
