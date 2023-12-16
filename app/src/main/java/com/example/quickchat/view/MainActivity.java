package com.example.quickchat.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.quickchat.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment selectedFragment;
    Stack<String> menuItems = new Stack<>();
    boolean appLaunched=true;
    int continuousBackPressNumberIndicator = 0;
    String lastPopedItem;
    int continuosMenuPressNumberIndicator=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnItemSelectedListener(navListener);
        loadContent();

    }

    @Override
    public void onBackPressed() {
        continuosMenuPressNumberIndicator=0;
        super.onBackPressed();
        continuousBackPressNumberIndicator++;
        if(!menuItems.isEmpty() && continuousBackPressNumberIndicator ==1){
            menuItems.pop();
            int selectedMenu=Integer.parseInt(menuItems.pop());
            lastPopedItem=String.valueOf(selectedMenu);
            MenuItem item = bottomNavigationView.getMenu().findItem(selectedMenu);
            if (item != null) {
                item.setChecked(true);
            }
        }
        else if(!menuItems.isEmpty() && continuousBackPressNumberIndicator ==2){
            Log.e("hghu", continuousBackPressNumberIndicator +"");
            int selectedMenu=Integer.parseInt(menuItems.pop());
            lastPopedItem=String.valueOf(selectedMenu);
            MenuItem item = bottomNavigationView.getMenu().findItem(selectedMenu);
            if (item != null) {
                item.setChecked(true);
            }
            continuousBackPressNumberIndicator =1;
        }else {
            Log.e("hghu", continuousBackPressNumberIndicator +"");
            MenuItem item = bottomNavigationView.getMenu().findItem(R.id.chat);
            if (item != null) {
                item.setChecked(true);
            }
//            continuosBackPressNumberIndicator=0;
        }
    }

    private final NavigationBarView.OnItemSelectedListener navListener = item -> {
        continuosMenuPressNumberIndicator++;
        if(appLaunched){
            menuItems.add(String.valueOf(R.id.chat));
            appLaunched=false;
        }
        int itemId = item.getItemId();
        if(itemId == R.id.chat){
            selectedFragment = new RecentChatsFragment();
        }else if (itemId == R.id.contacts){
            selectedFragment = new ContactsFragment();
        }else if (itemId == R.id.settings){
            selectedFragment = new SettingsFragment();
        }

        if(selectedFragment!=null){
            continuousBackPressNumberIndicator =0;
            if(lastPopedItem!=null && continuosMenuPressNumberIndicator==1){
                menuItems.add(lastPopedItem);
            }
            menuItems.add(String.valueOf(itemId));
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