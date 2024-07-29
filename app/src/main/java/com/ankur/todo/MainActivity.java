package com.ankur.todo;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ankur.todo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final ArrayList<Tasks> contactsArrayList = new ArrayList<>();
    // ADAPTER
    private MyAdapter myAdapter;
    // BINDING
    private ActivityMainBinding mainBinding;
    private MainActivityClickHandlers handlers;
    private ViewModel myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handlers = new MainActivityClickHandlers(this);
        mainBinding.setClickHandler(handlers);

        // RECYCLER VIEW
        RecyclerView recyclerView = mainBinding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // VIEWMODEL
        myViewModel = new ViewModelProvider(this).get(ViewModel.class);

        // ADAPTER
        myAdapter = new MyAdapter(contactsArrayList, myViewModel);
        recyclerView.setAdapter(myAdapter);

        myViewModel.getAllTasks().observe(this, new Observer<List<Tasks>>() {
            @Override
            public void onChanged(List<Tasks> contacts) {
                contactsArrayList.clear();
                if (contacts != null) {
                    contactsArrayList.addAll(contacts);
                    for (Tasks c : contacts) {
                        Log.v("TAGY", c.getTaskName());
                    }
                }
                myAdapter.notifyDataSetChanged();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Tasks c = contactsArrayList.get(viewHolder.getAdapterPosition());
                myViewModel.deleteTask(c);
            }
        }).attachToRecyclerView(recyclerView);

    }
}
