package com.ankur.todo;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class MainActivityClickHandlers {
    Context context;

    public MainActivityClickHandlers(Context context) {
        this.context = context;
    }
    public void onFABClicked(View view){

        Intent i = new Intent(view.getContext(), AddNewTaskActivity.class);
        context.startActivity(i);
    }
}