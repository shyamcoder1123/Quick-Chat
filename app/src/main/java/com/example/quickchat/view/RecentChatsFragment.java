package com.example.quickchat.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quickchat.R;
import com.example.quickchat.view.adapters.ChatRecyclerAdapter;
import com.example.quickchat.model.ChatModel;

import java.util.ArrayList;

public class RecentChatsFragment extends Fragment implements ChatRecyclerAdapter.ChatItemTouchIterface{

    ArrayList<ChatModel> chatModelArrayList;
    ChatRecyclerAdapter chatRecyclerAdapter;
    RecyclerView chatRecyclerView;
    public RecentChatsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recent_chats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chatRecyclerView=view.findViewById(R.id.chatRecyclerView);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        chatModelArrayList=new ArrayList<>();
        addChat();
        chatRecyclerAdapter=new ChatRecyclerAdapter(chatModelArrayList,this::onChatItemTouch,getContext());
        chatRecyclerView.setAdapter(chatRecyclerAdapter);
        chatRecyclerAdapter.notifyDataSetChanged();
    }
    public void addChat(){
        chatModelArrayList.add(new ChatModel("https://www.emmegi.co.uk/wp-content/uploads/2019/01/User-Icon.jpg","Chris John","Hi! morning","14.36"));
    }
    @Override
    public void onChatItemTouch(int position) {
        Intent i = new Intent(getContext(), ChatActivity.class);
        i.putExtra("profileImageURL","https://www.emmegi.co.uk/wp-content/uploads/2019/01/User-Icon.jpg");
        i.putExtra("profileName","Chris John");
        startActivity(i);
    }
}