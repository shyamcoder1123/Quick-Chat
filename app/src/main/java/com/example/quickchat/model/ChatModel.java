package com.example.quickchat.model;

public class ChatModel {
    String profileImageURL;
    String name;
    String message;
    String chatTime;

    public ChatModel(String profileImageURL, String name, String message,String time) {
        this.profileImageURL = profileImageURL;
        this.name = name;
        this.message = message;
        this.chatTime = time;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getChatTime() {
        return chatTime;
    }

    public void setChatTime(String chatTime) {
        this.chatTime = chatTime;
    }
}
