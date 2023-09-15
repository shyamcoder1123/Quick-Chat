package com.example.quickchat.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.quickchat.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if(auth!=null){
            if(auth.getCurrentUser()==null){
                Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(i);
            }
        }

    }
}