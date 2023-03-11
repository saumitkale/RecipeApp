package com.example.finalappsaumit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UserRegister extends AppCompatActivity {

    EditText emailR,passwordR, nameR, phoneR;
    Button registerLogin, backtoLogin;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        emailR = findViewById(R.id.emailRegister);
        passwordR = findViewById(R.id.passwordRegister);
        nameR = findViewById(R.id.nameRegister);
        phoneR = findViewById(R.id.phoneRegister);
        registerLogin = findViewById(R.id.RegButton);
        backtoLogin = findViewById(R.id.BackLoginButton);
        firebaseAuth = FirebaseAuth.getInstance();

        registerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailR.getText().toString().replace(" ", "");
                String password = passwordR.getText().toString().replace(" ", "");
                String name = nameR.getText().toString();
                String phone = phoneR.getText().toString();

                if (name.equals("")) {
                    nameR.setError("Incomplete");
                    nameR.requestFocus();
                    return;
                }
                else if (email.equals("")) {
                    emailR.setError("Incomplete");
                    emailR.requestFocus();
                    return;
                }
                else if(!phoneCheck(phone)) {
                    phoneR.setError("Incomplete");
                    phoneR.requestFocus();
                    return;
                }
                else if (password.equals("")) {
                    passwordR.setError("Incomplete");
                    passwordR.requestFocus();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), UserProfile.class));
                        }
                    }
                });
            }
        });
        backtoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
    public static boolean phoneCheck(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) >= '0' && string.charAt(i) <= '9') {
                return true;
            }
        }
        return false;
    }
}