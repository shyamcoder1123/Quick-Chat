package com.example.quickchat.repository;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.quickchat.model.ChatGroup;
import com.example.quickchat.model.MessageModel;
import com.example.quickchat.view.ChatActivity;
import com.example.quickchat.view.MainActivity;
import com.example.quickchat.view.RegistrationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    MutableLiveData<List<ChatGroup>> chatGroupMutableLiveData;
    MutableLiveData<List<MessageModel>> chatMessageMutableLiveData;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    DatabaseReference groupReference;
    public Repository(){
        this.chatGroupMutableLiveData=new MutableLiveData<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        chatMessageMutableLiveData = new MutableLiveData<>();
    }
    public void anonymousFirebaseAuth(Context context){
        FirebaseAuth.getInstance().signInAnonymously()
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent i = new Intent(context, MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(i);
                        }
                    }
                });
    }
    public String getCurrentUserId(){
        return FirebaseAuth.getInstance().getUid();
    }
    public void signOut(){
        FirebaseAuth.getInstance().signOut();
    }
    //Getting chat groups available from firebase realtime database

    public MutableLiveData<List<ChatGroup>> getChatGroupMutableLiveData() {
        List<ChatGroup> chatGroups = new ArrayList<>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chatGroups.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    ChatGroup chatGroup = new ChatGroup(dataSnapshot.getKey());
                    chatGroups.add(chatGroup);
                }
                chatGroupMutableLiveData.postValue(chatGroups);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return chatGroupMutableLiveData;
    }
    public void createNewChatGroup(String groupName){
        //if we need to create the child of a root then use databaseReference.child();
        databaseReference.child(groupName).setValue(new ChatGroup(groupName));
    }

    public MutableLiveData<List<MessageModel>> getChatMessageMutableLiveData(String groupName) {
        groupReference = firebaseDatabase.getReference().child(groupName);
        List<MessageModel> messageModelList = new ArrayList<>();
        groupReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messageModelList.clear();
                if(snapshot.getChildrenCount()>1){
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    Log.e("error1234",dataSnapshot.get);
                        try{
                            MessageModel messageModel = dataSnapshot.getValue(MessageModel.class);
                            messageModelList.add(messageModel);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }

                chatMessageMutableLiveData.postValue(messageModelList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return chatMessageMutableLiveData;
    }

    public void sendMessage(String message,String groupName){
        DatabaseReference databaseReference = firebaseDatabase.getReference(groupName);
        if(!message.trim().equals("")){
            MessageModel messageModel = new MessageModel(
                  FirebaseAuth.getInstance().getCurrentUser().getUid(),
                    message,
                    System.currentTimeMillis()
            );
            String randomKey = databaseReference.push().getKey();

            databaseReference.child(randomKey).setValue(messageModel);
        }
    }
}
