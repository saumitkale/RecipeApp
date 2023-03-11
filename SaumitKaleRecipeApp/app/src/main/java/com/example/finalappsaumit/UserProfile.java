package com.example.finalappsaumit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class UserProfile extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseFirestore fireStore;
    Button recipeBut, logoutBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        recipeBut = findViewById(R.id.RecipeSearcher);
        logoutBut = findViewById(R.id.logoutButton);
        firebaseAuth = FirebaseAuth.getInstance();
        fireStore = FirebaseFirestore.getInstance();

        logoutBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        recipeBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Recipes.class));
            }
        });
    }
}