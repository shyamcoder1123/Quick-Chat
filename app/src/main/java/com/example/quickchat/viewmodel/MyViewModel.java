package com.example.quickchat.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.quickchat.model.ChatGroup;
import com.example.quickchat.model.MessageModel;
import com.example.quickchat.repository.Repository;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    Repository repository;

    public MyViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository();
    }
    public void signUpAnonymousUser(){
        Context c = this.getApplication();
        repository.anonymousFirebaseAuth(c);
    }
    public String getCurrentUserId(){
        return repository.getCurrentUserId();
    }
    public void signOutAnonymousUser(){
        repository.signOut();
    }
    public MutableLiveData<List<ChatGroup>> getChatGroupMutableLiveData(){
        return repository.getChatGroupMutableLiveData();
    }
    public void createChatGroup(String groupName){
         repository.createNewChatGroup(groupName);
    }
    public MutableLiveData<List<MessageModel>> getMessageModelGroupMutableLiveData(String groupName){
        return repository.getChatMessageMutableLiveData(groupName);
    }

    public void sendMessage(String message, String groupName){
        repository.sendMessage(message,groupName);
    }
}
