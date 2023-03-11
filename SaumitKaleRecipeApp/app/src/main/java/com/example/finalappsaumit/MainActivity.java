package com.example.finalappsaumit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    EditText emailL;
    EditText passwordL;
    Button login, register;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        emailL = findViewById(R.id.emailLogin);
        passwordL = findViewById(R.id.passwordLogin);
        login = findViewById(R.id.LoginButton);
        register = findViewById(R.id.RegisterButton);

        firebaseAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth.signInWithEmailAndPassword(emailL.getText().toString(), passwordL.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), UserProfile.class));
                        }
                    }
                });
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserRegister.class));
            }
        });

    }
}