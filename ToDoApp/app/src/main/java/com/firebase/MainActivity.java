package com.firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import static com.firebase.R.*;
import static com.firebase.R.layout.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main);
    }

    RecyclerView task  = (RecyclerView)findViewById(id.task);
    programmingList.setAdapter(new ProgrammingAdapter)
}