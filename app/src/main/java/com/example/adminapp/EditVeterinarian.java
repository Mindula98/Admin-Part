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

import java.util.HashMap;
import java.util.Map;

public class EditVeterinarian extends AppCompatActivity {
    private EditText veteName, vetAddress, vetContact, vetEmail;
    private Button EdtVetBtn, DeleteVetBtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private VeterinarianModel veterinarianModel;
    private String VetName;
    private String vetID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_veterinarian);
        firebaseDatabase = FirebaseDatabase.getInstance();
        veteName = findViewById(R.id.Name_EV);
        vetAddress = findViewById(R.id.address_EV);
        vetContact = findViewById(R.id.contact_EV);
        vetEmail = findViewById(R.id.Email_EV);
        EdtVetBtn = findViewById(R.id.updateBtn_EV);
        DeleteVetBtn = findViewById(R.id.deleteBtn_EV);
        veterinarianModel = getIntent().getParcelableExtra("veterinarian");

        if(veterinarianModel!=null){
            veteName.setText(veterinarianModel.getVetName());
            vetAddress.setText(veterinarianModel.getVetAddress());
            vetContact.setText(veterinarianModel.getVetContact());
            vetEmail.setText(veterinarianModel.getVetEmail());
            VetName = veterinarianModel.getVetName();
            vetID = veterinarianModel.getVetID();
        }

        databaseReference = firebaseDatabase.getReference("Veterinarians").child(vetID);
        EdtVetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String VetName = veteName.getText().toString();
                String Address = vetAddress.getText().toString();
                String Contact = vetContact.getText().toString();
                String Email = vetEmail.getText().toString();

                Map<String,Object> map = new HashMap<>();
                map.put("vetName",VetName);
                map.put("vetAddress",Address);
                map.put("vetContact",Contact);
                map.put("vetEmail",Email);

                databaseReference.updateChildren(map);
                        Toast.makeText(EditVeterinarian.this, "Details Updated Succesfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditVeterinarian.this, vetList.class));

//                databaseReference.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                       databaseReference.updateChildren(map);
//                        Toast.makeText(EditVeterinarian.this, "Details Updated Succesfully", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(EditVeterinarian.this, vetList.class));
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        Toast.makeText(EditVeterinarian.this, "Failed to Update", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
            }
        });

        DeleteVetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteVeterinarian();

            }
        });
//
    }

    private void deleteVeterinarian(){
        databaseReference.removeValue();
        Toast.makeText(EditVeterinarian.this, "Veterinarian Deleted", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(EditVeterinarian.this, vetList.class));
    }
}