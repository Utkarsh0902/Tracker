package com.example.tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;

public class TrackerActivity extends AppCompatActivity {
    String itemCategory = "";
    RecyclerView mRecyclerView;
    private ItemListAdapter mAdapter;
    public static LinkedList<ItemViewModel> ItemList = new LinkedList<>();
    LinkedList<ItemViewModel> categorisedList = new LinkedList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get the calling intent
        Intent intent = getIntent();
        if(intent.hasExtra("itemCategory")) {
            itemCategory = getIntent().getStringExtra("itemCategory");
        }

        setContentView(R.layout.activity_tracker);
        //get the items belonging to a particular category only
        for (ItemViewModel item : ItemList) {
            if (item.getCategory().toLowerCase().equals(itemCategory.toLowerCase())) {
                categorisedList.add(item);
            }
        }

        FloatingActionButton itemFAB= findViewById(R.id.itemFAB);
        itemFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemLastSize = ItemList.size();
                ItemViewModel item = new ItemViewModel();
                item.setCategory(itemCategory);
                ItemList.add(item);
                categorisedList.add(item);
                // Notify the adapter, that the data has changed.
                mRecyclerView.getAdapter().notifyItemInserted(itemLastSize);
                // Scroll to the bottom.
                mRecyclerView.smoothScrollToPosition(itemLastSize);
            }
        });

        EditText editText = findViewById(R.id.searchItem);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
               filter(s.toString());
            }

            private void filter(String text) {
                LinkedList<ItemViewModel> filteredList = new LinkedList<>();
                for (ItemViewModel item : ItemList) {
                    if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                        filteredList.add(item);
                    }
                }
                mAdapter.filteredList(filteredList);
            }
        });



        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.item_recycler_view);

        // Create an adapter and supply the data to be displayed.
        mAdapter = new ItemListAdapter(this,categorisedList);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

}


