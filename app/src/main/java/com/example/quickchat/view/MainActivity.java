package com.example.quickchat.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.quickchat.R;
import com.example.quickchat.helper.ChatRecyclerAdapter;
import com.example.quickchat.model.ChatModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment selectedFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnItemSelectedListener(navListener);
        loadContent();

    }
    private final NavigationBarView.OnItemSelectedListener navListener = item -> {
        int itemId = item.getItemId();
        if(itemId == R.id.chat){
            selectedFragment = new RecentChatsFragment();
        }else if (itemId == R.id.contacts){
            selectedFragment = new ContactsFragment();
        }else if (itemId == R.id.settings){
            selectedFragment = new SettingsFragment();
        }

        if(selectedFragment!=null){
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in, R.anim.fade_out).replace(R.id.mainPart
                    ,selectedFragment).addToBackStack(null).commit();
        }
        return true;
    };
    private void loadContent(){
        getSupportFragmentManager().beginTransaction().replace(R.id.mainPart
                ,new RecentChatsFragment()).commit();
    }
}