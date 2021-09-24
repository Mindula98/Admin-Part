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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddDogGroup extends AppCompatActivity {

    EditText groupName, groupImage, groupDetails;
    Button btnAddgroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dog_group);

        groupName = (EditText)findViewById(R.id.group_name);
        groupImage = (EditText)findViewById(R.id.group_image);
        groupDetails = (EditText)findViewById(R.id.group_details);

        btnAddgroup = (Button)findViewById(R.id.create_group);

        btnAddgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               insertGroup();
               clearAll();
            }
        });
    }

    private void insertGroup(){

        Map<String,Object> map = new HashMap<>();
        map.put("Name",groupName.getText().toString());
        map.put("Image",groupImage.getText().toString());
        map.put("Details",groupDetails.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Groups").child(groupName.getText().toString())
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

    private void clearAll(){

        groupName.setText("");
        groupImage.setText("");
        groupDetails.setText("");

    }
}