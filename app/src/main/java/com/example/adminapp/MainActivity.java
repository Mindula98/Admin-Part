package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText emailLogin, pwdLogin;
    private Button loginBtn;
    private TextView regtxt;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailLogin = findViewById(R.id.loginEmail);
        pwdLogin = findViewById(R.id.loginPassword);
        loginBtn = findViewById(R.id.bttn_loginPge);
        regtxt = findViewById(R.id.logintxt);
        mAuth = FirebaseAuth.getInstance();

        regtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,adminRegister.class);
                startActivity(i);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailLogin.getText().toString();
                String pwd = pwdLogin.getText().toString();
                if (TextUtils.isEmpty(email) && TextUtils.isEmpty(pwd)){
                    Toast.makeText(MainActivity.this, "Please Enter Your Credentials", Toast.LENGTH_SHORT).show();
                return;
                }else{
                    mAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(MainActivity.this,Menu.class);
                                startActivity(i);
                                finish();

                            }else {
                                Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user!=null){
            Intent i = new Intent(MainActivity.this,Menu.class);
            startActivity(i);
            this.finish();
        }
    }
}