package com.example.adminapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class adminRegister extends AppCompatActivity {

    private EditText emailReg,usernameReg, pwdReg, rePwdReg;
    private Button registerBtn;
    private TextView logintxt;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);
        emailReg = findViewById(R.id.emailReg);
        usernameReg = findViewById(R.id.adminName);
        pwdReg = findViewById(R.id.regPassword);
        rePwdReg = findViewById(R.id.regPasswordRE);
        logintxt = findViewById(R.id.registertxt);
        registerBtn = findViewById(R.id.registerBtn);
        mAuth = FirebaseAuth.getInstance();
        logintxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(adminRegister.this,MainActivity.class);
                startActivity(i);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailReg.getText().toString();
                String userName = usernameReg.getText().toString();
                String pwd = pwdReg.getText().toString();
                String rePwd = rePwdReg.getText().toString();
                if (!pwd.equals(rePwd)){
                    Toast.makeText(adminRegister.this, "Please check Password", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(userName) && TextUtils.isEmpty(pwd) && TextUtils.isEmpty(rePwd)){
                    Toast.makeText(adminRegister.this, "Please add your credentials", Toast.LENGTH_SHORT).show();
                }else{
                    mAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(adminRegister.this, "Admin Registered..", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(adminRegister.this,MainActivity.class) ;
                                startActivity(i);
                                finish();
                            }else{
                                Toast.makeText(adminRegister.this, "Failed to Register..", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}