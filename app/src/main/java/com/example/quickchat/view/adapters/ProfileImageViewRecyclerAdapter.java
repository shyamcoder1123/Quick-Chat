package com.example.quickchat.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickchat.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProfileImageViewRecyclerAdapter extends RecyclerView.Adapter<ProfileImageViewRecyclerAdapter.ViewHolder> {
    ArrayList<String> imagesForProfile;
    Context context;

    public ProfileImageViewRecyclerAdapter(ArrayList<String> imagesForProfile, Context context) {
        this.imagesForProfile = imagesForProfile;
        this.context = context;
    }

    @NonNull
    @Override
    public ProfileImageViewRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item_profile,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileImageViewRecyclerAdapter.ViewHolder holder, int position) {
        String imageString = imagesForProfile.get(position);
        Picasso.get().load(imageString).resize(100,100).centerCrop().into(holder.imageViewRecyclerProfile);
    }

    @Override
    public int getItemCount() {
        return imagesForProfile.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewRecyclerProfile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewRecyclerProfile = itemView.findViewById(R.id.imageItem);
        }
    }
}
