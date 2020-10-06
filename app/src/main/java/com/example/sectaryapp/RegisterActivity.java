package com.example.sectaryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sectaryapp.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    TextView tvLogin;
    Button registerbtn;
    EditText usernameId, emailId, schoolId, passwordId;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tvLogin = findViewById(R.id.tvLogin);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        emailId=findViewById(R.id.email);
        usernameId=findViewById(R.id.username);
        schoolId=findViewById(R.id.school);
        passwordId=findViewById(R.id.password);


    }
    public void OnClick(View view) {
        registerbtn= findViewById(R.id.register);
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = emailId.getText().toString();
                String password = passwordId.getText().toString();
                String school = schoolId.getText().toString();
                String username = usernameId.getText().toString();

                if(email.isEmpty()){
                    emailId.setError("Please Enter Valid email!!");
                    emailId.requestFocus();
                }else if(password.isEmpty()){
                    passwordId.setError("Please Enter Password!");
                    emailId.requestFocus();
                }else if(school.isEmpty()) {
                    schoolId.setError("Please Enter Name of your School!!");
                    schoolId.requestFocus();
                }else if(username.isEmpty()) {
                    usernameId.setError("Please Enter Your Full names!!");
                    usernameId.requestFocus();
                }
                else if(username.isEmpty() && school.isEmpty() && email.isEmpty() && password.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Fields are empty!", Toast.LENGTH_SHORT).show();
                }else if(!(username.isEmpty() && school.isEmpty() && email.isEmpty() && password.isEmpty())){
                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Registration Unsuccessful!, Try Again", Toast.LENGTH_SHORT).show();
                            }else{
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}