package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddDogGroup extends AppCompatActivity {

    EditText groupName, iconUrl;
    Button btnAddgroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dog_group);

        groupName = (EditText)findViewById(R.id.group_name);
        iconUrl = (EditText)findViewById(R.id.icon_url);

        btnAddgroup = (Button)findViewById(R.id.create_group);

        btnAddgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertGroup();
            }
        });
    }

    private void insertGroup(){

        Map<String,Object> map = new HashMap<>();
        map.put("groupName",groupName.getText().toString());
        map.put("iconUrl",iconUrl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Dog Groups").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddDogGroup.this, "Group Inserted Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AddDogGroup.this, "Error while Insertion", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}