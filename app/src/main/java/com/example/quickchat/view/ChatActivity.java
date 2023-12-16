package com.example.quickchat.view;

import static com.example.quickchat.view.adapters.GroupChatRecyclerAdapter.GROUP_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quickchat.R;
import com.example.quickchat.databinding.ActivityChatBinding;
import com.example.quickchat.model.MessageModel;
import com.example.quickchat.view.adapters.ChatAdapter;
import com.example.quickchat.viewmodel.MyViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    ActivityChatBinding activityChatBinding;
    MyViewModel viewModel;
    ChatAdapter chatAdapter;
    RecyclerView recyclerView;
    private List<MessageModel> messageModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        activityChatBinding = DataBindingUtil.setContentView(this,R.layout.activity_chat);
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        recyclerView = activityChatBinding.recyclerViewMessages;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        String groupName = getIntent().getStringExtra(GROUP_NAME);

        activityChatBinding.nameChatActivity.setText(groupName);
        activityChatBinding.profileImageChatActivity.setImageResource(R.drawable.groups_image);
        viewModel.getMessageModelGroupMutableLiveData(groupName).observe(this, new Observer<List<MessageModel>>() {
            @Override
            public void onChanged(List<MessageModel> messageModels) {
                messageModelList = new ArrayList<>();
                messageModelList.addAll(messageModels);
                chatAdapter = new ChatAdapter(messageModelList,getApplicationContext());
                recyclerView.setAdapter(chatAdapter);
                chatAdapter.notifyDataSetChanged();

                //scroll to the latest message added
                int latestPosition = chatAdapter.getItemCount()-1;
                if(latestPosition>0){
                    recyclerView.smoothScrollToPosition(latestPosition);
                }
            }
        });

        activityChatBinding.setVModel(viewModel);

        TextWatcher messageTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().length()==0){
                    activityChatBinding.sendMessageButton.setImageResource(R.drawable.mic_with_background);
                    activityChatBinding.attach.setVisibility(View.VISIBLE);
                    activityChatBinding.camera.setVisibility(View.VISIBLE);

                }else {
                    activityChatBinding.sendMessageButton.setImageResource(R.drawable.send_with_background);
                    activityChatBinding.attach.setVisibility(View.GONE);
                    activityChatBinding.camera.setVisibility(View.GONE);
                }
            }
        };
        activityChatBinding.messageInputEditText.addTextChangedListener(messageTextWatcher);

        activityChatBinding.sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = activityChatBinding.messageInputEditText.getText().toString();
                viewModel.sendMessage(message,groupName);
                activityChatBinding.messageInputEditText.getText().clear();
            }
        });
    }

    public void openProfile(View view) {
        Intent i= new Intent(this, ProfileActivity.class);
        startActivity(i);
    }
}