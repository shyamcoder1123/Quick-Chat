package com.example.quickchat.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickchat.R;
import com.example.quickchat.model.ChatModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatRecyclerAdapter.ViewHolder> {
    ArrayList<ChatModel> chatModelArrayList;
    ChatItemTouchIterface chatItemTouchIterface;
    Context context;

    public ChatRecyclerAdapter(ArrayList<ChatModel> chatModelArrayList, ChatItemTouchIterface chatItemTouchIterface, Context context) {
        this.chatModelArrayList = chatModelArrayList;
        this.chatItemTouchIterface = chatItemTouchIterface;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRecyclerAdapter.ViewHolder holder, int position) {
        ChatModel chat = chatModelArrayList.get(position);
        try {
            String chatName = chat.getName();
            String chatTime = chat.getChatTime();
            String message = chat.getMessage();
            String profileImage = chat.getProfileImageURL();

            holder.chatNameTextView.setText(chatName);
            holder.timeTextView.setText(chatTime);
            holder.messageTextView.setText(message);
            Picasso.get().load(profileImage).centerCrop().resize(56,56).into(holder.profileImageView);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chatItemTouchIterface.onChatItemTouch(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return chatModelArrayList.size();
    }
    public interface ChatItemTouchIterface{
        void onChatItemTouch(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImageView;
        TextView chatNameTextView;
        TextView messageTextView;
        TextView timeTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImageView= itemView.findViewById(R.id.profileImage);
            chatNameTextView= itemView.findViewById(R.id.nameTextView);
            messageTextView= itemView.findViewById(R.id.messagePreview);
            timeTextView= itemView.findViewById(R.id.timeTextView);
        }
    }
}
