package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class DogsGroups extends AppCompatActivity {

    RecyclerView recyclerView;
    GrouAdapter grouAdapter;

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs_groups);

        recyclerView = (RecyclerView)findViewById(R.id.rvGroups);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<GroupsModel> options =
                new FirebaseRecyclerOptions.Builder<GroupsModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Groups"), GroupsModel.class)
                        .build();
        grouAdapter = new GrouAdapter(options);
        recyclerView.setAdapter(grouAdapter);

        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddDogGroup.class));
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        grouAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        grouAdapter.startListening();
    }
}