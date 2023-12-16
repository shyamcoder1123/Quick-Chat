package com.example.quickchat.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quickchat.R;
import com.example.quickchat.databinding.ActivityGroupsBinding;
import com.example.quickchat.model.ChatGroup;
import com.example.quickchat.view.adapters.GroupChatRecyclerAdapter;
import com.example.quickchat.viewmodel.MyViewModel;

import java.util.ArrayList;
import java.util.List;

public class GroupsActivity extends AppCompatActivity {

    private ArrayList<ChatGroup> chatGroupArrayList;
    private RecyclerView recyclerView;
    private GroupChatRecyclerAdapter groupChatRecyclerAdapter;
    private MyViewModel viewModel;
    private ActivityGroupsBinding activityGroupsBinding;
    private Dialog groupChatDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        activityGroupsBinding = DataBindingUtil.setContentView(this,R.layout.activity_groups);
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);


        recyclerView=activityGroupsBinding.recyclerViewGroups;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //set up an observer to listen the changes in the groups arraylist in the viewmodel
        viewModel.getChatGroupMutableLiveData().observe(this, new Observer<List<ChatGroup>>() {
            @Override
            public void onChanged(List<ChatGroup> chatGroups) {
                //the updated data is recieved as "chatGroups" parameter in the onChanged
                chatGroupArrayList = new ArrayList<>();
                chatGroupArrayList.addAll(chatGroups);
                groupChatRecyclerAdapter=new GroupChatRecyclerAdapter(chatGroupArrayList);
                recyclerView.setAdapter(groupChatRecyclerAdapter);
                groupChatRecyclerAdapter.notifyDataSetChanged();
            }
        });
    }

    public void showDialog(View v){

        groupChatDialog = new Dialog(this);
        groupChatDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);




        //we are inflating view bcz we are created view for this seperately

        View view  = LayoutInflater.from(this).inflate(R.layout.dialogue_layout,null);
        groupChatDialog.setContentView(view);
        TextView enterNameTextView = view.findViewById(R.id.enterGroupNameTextView);
        EditText enterNameEditText = view.findViewById(R.id.enterGroupNameEditText);
        Button enterNameButton = view.findViewById(R.id.enterGroupNameButton);
        groupChatDialog.show();

        enterNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String groupName = enterNameEditText.getText().toString();
                Toast.makeText(GroupsActivity.this, "group Name is "+ groupName, Toast.LENGTH_SHORT).show();

                viewModel.createChatGroup(groupName);
                groupChatDialog.dismiss();
            }
        });

    }
}