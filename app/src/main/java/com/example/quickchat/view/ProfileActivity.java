package com.example.quickchat.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quickchat.R;
import com.example.quickchat.view.adapters.ProfileImageViewRecyclerAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    ImageView circularImageView;
    ArrayList<String>imagesList;
    Toolbar toolbar;
    TextView profileNameTextView;
    private boolean isTitleVisible = false;
    RecyclerView imagesRecyclerView;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    ProfileImageViewRecyclerAdapter profileImageViewRecyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        toolbar = findViewById(R.id.toolbar_profile);
        appBarLayout=findViewById(R.id.appBarProfile);
        imagesList = new ArrayList<>();
        imagesRecyclerView = findViewById(R.id.image_recycler_profile);
        addImages();
        profileImageViewRecyclerAdapter = new ProfileImageViewRecyclerAdapter(imagesList,this);
        imagesRecyclerView.setAdapter(profileImageViewRecyclerAdapter);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        circularImageView = findViewById(R.id.circularImageViewProfile);

        circularImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayoutProfile);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("Chris John");
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");//careful there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });

    }

    private void addImages() {
        imagesList.add("https://th.bing.com/th?id=OIP.HxV79tFMPfBAIo0BBF-sOgHaEy&w=310&h=200&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2");
        imagesList.add("https://th.bing.com/th?id=OIP.rgPl5uUflVlLSVbfBLPXnAHaEK&w=333&h=187&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2");
        imagesList.add("https://th.bing.com/th?id=OIP.LxbeKKIiDLRxgEkd0BsHTAHaEo&w=316&h=197&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2");
        imagesList.add("https://th.bing.com/th?id=OIP.SRB_cFiNZLKWfBHrd2IeJwHaEZ&w=324&h=192&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2");
        imagesList.add("https://th.bing.com/th?id=OIP.FzPJZ9lZKgBnPhELqhlUMwHaEo&w=316&h=197&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2");
    }
}