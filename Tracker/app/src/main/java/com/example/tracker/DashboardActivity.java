package com.example.tracker;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import java.util.LinkedList;
import java.util.Random;

public class DashboardActivity extends AppCompatActivity {
    RecyclerView cRecyclerView;
    private CategoryListAdapter cAdapter;
    public static LinkedList<String> categoryList = new LinkedList<>();
    public static int cardColor;
    ConstraintLayout dashboardLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Setting animated background for the dashboard
        dashboardLayout = findViewById(R.id.dashboard_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable)dashboardLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1500);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();

        FloatingActionButton categoryFAB = findViewById(R.id.category_fab);
        categoryFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //To generate random card color
                Random random = new Random();
                cardColor = Color.argb(255,random.nextInt(256),random.nextInt(256),random.nextInt(256));

                int categoryLastSize = categoryList.size();
                categoryList.add(new String());
                // Notify the adapter, that the data has changed.
                cRecyclerView.getAdapter().notifyItemInserted(categoryLastSize);
                // Scroll to the bottom.
                cRecyclerView.smoothScrollToPosition(categoryLastSize);
            }
        });

        // Get a handle to the RecyclerView.
        cRecyclerView = findViewById(R.id.category_recycler_view);
        // Create an adapter and supply the data to be displayed.
        cAdapter = new CategoryListAdapter(this,categoryList);
        // Connect the adapter with the RecyclerView.
        cRecyclerView.setAdapter(cAdapter);
        // Give the RecyclerView a default layout manager.
        cRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}

