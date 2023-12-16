package com.example.quickchat.model;

import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MessageModel {
    String senderId;
    String text;
    long time;
    boolean isMine;

    public MessageModel(String senderId, String text, long time) {
        this.senderId = senderId;
        this.text = text;
        this.time = time;
    }

    public MessageModel() {
    }

    public boolean isMine() {
        return senderId.equals(FirebaseAuth.getInstance().getCurrentUser().getUid());
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
    public String convertTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = new Date(getTime());
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(date);
    }
}
