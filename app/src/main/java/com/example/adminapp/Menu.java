package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Menu extends AppCompatActivity {
    Button logoutBtn, vetList, addVet, addDog;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mAuth = FirebaseAuth.getInstance();

        logoutBtn = findViewById(R.id.Btnlogout);
        vetList = findViewById(R.id.vetList);
        addVet = findViewById(R.id.addVet);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();
                signOutUser();
            }
        });

        vetList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Menu.this,AddVeterinarian.class);
                startActivity(i);
            }
        });

        addVet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Menu.this, AddVeterinarian.class);
                startActivity(intent);

            }
        });
    }

    private void signOutUser() {
        Intent mainActivity = new Intent(Menu.this,MainActivity.class);
        mainActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Toast.makeText(Menu.this, "Logged Out", Toast.LENGTH_SHORT).show();
        startActivity(mainActivity);
        finish();
    }
}