package com.example.quickchat.view.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickchat.R;
import com.example.quickchat.databinding.ItemCardMessageBinding;
import com.example.quickchat.model.ChatGroup;
import com.example.quickchat.view.ChatActivity;

import java.util.ArrayList;

public class GroupChatRecyclerAdapter extends RecyclerView.Adapter<GroupChatRecyclerAdapter.GroupChatViewHolder> {
    public static final String GROUP_NAME = "GROUP_NAME";
    private ArrayList<ChatGroup> chatGroupsArrayList;

    public GroupChatRecyclerAdapter(ArrayList<ChatGroup> chatGroupsArrayList) {
        this.chatGroupsArrayList = chatGroupsArrayList;
    }

    @NonNull
    @Override
    public GroupChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCardMessageBinding binding= DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_card_message,
                parent,
                false
        );
        return new GroupChatViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupChatViewHolder holder, int position) {
        ChatGroup currentChatGroup = chatGroupsArrayList.get(position);
        holder.itemCardMessageBinding.setChatGroup(currentChatGroup);

    }

    @Override
    public int getItemCount() {
        return chatGroupsArrayList.size();
    }

    public class GroupChatViewHolder extends RecyclerView.ViewHolder{
        private ItemCardMessageBinding itemCardMessageBinding;
        public GroupChatViewHolder(ItemCardMessageBinding itemCardMessageBinding) {
            super(itemCardMessageBinding.getRoot());
            this.itemCardMessageBinding=itemCardMessageBinding;
            itemCardMessageBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    ChatGroup clickedChatGroup = chatGroupsArrayList.get(position);

                    Intent i = new Intent(view.getContext(), ChatActivity.class);
                    i.putExtra(GROUP_NAME,clickedChatGroup.getGroupName());
                    view.getContext().startActivity(i);
                }
            });
        }
    }
}
