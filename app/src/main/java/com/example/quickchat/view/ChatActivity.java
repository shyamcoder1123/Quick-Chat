package com.example.quickchat.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quickchat.R;
import com.squareup.picasso.Picasso;

public class ChatActivity extends AppCompatActivity {
 ImageView profileImageView;
 TextView profileNameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        String imageURL = getIntent().getStringExtra("profileImageURL");
        String name = getIntent().getStringExtra("profileName");
        profileImageView = findViewById(R.id.profileImageChatActivity);
        profileNameView = findViewById(R.id.nameChatActivity);

        profileNameView.setText(name);
        Picasso.get().load(imageURL).resize(50,50).centerCrop().into(profileImageView);
    }
}