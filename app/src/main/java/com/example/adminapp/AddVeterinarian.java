package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddVeterinarian extends AppCompatActivity {

    private EditText vetName, vetAddress, vetContact, vetEmail;
    private Button addVetBtn,menuBtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_veterinarian);
        vetName = findViewById(R.id.Name_AV);
        vetAddress = findViewById(R.id.address_AV);
        vetContact = findViewById(R.id.editTextPhone);
        vetEmail = findViewById(R.id.Email_AV);
        addVetBtn = findViewById(R.id.button_AV);
        menuBtn = findViewById(R.id.BtnMenu);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Veterinarians");

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(AddVeterinarian.this,Menu.class);
                startActivity(i);
            }
        });

        addVetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String VetName = vetName.getText().toString();
                String Address = vetAddress.getText().toString();
                String Contact = vetContact.getText().toString();
                String Email = vetEmail.getText().toString();

                VeterinarianModel veterinarianModel = new VeterinarianModel(VetName, Address, Contact, Email);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child(VetName).setValue(veterinarianModel);
                        Toast.makeText(AddVeterinarian.this, "Veterinarian Added", Toast.LENGTH_SHORT).show();
                        Clear();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(AddVeterinarian.this, "Error is " + error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

    }

    private void Clear() {
        vetName.setText("");
        vetAddress.setText("");
        vetContact.setText("");
        vetEmail.setText("");
    }
}