package com.example.quickchat.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quickchat.R;
import com.squareup.picasso.Picasso;

public class SettingsFragment extends Fragment {

    View accountView;
    View privacyView;
    View avatarView;
    View chatsView;
    View notificationsView;
    View storageView;
    View appLanguageView;
    View helpView;
    View inviteView;
    ImageView profileImage;
    public SettingsFragment() {
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
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        profileImage = view.findViewById(R.id.profileImageSettings);
        Picasso.get().load("https://www.emmegi.co.uk/wp-content/uploads/2019/01/User-Icon.jpg").centerCrop().resize(80,80).into(profileImage);
        accountView = view.findViewById(R.id.account);
        TextView accTextView = accountView.findViewById(R.id.itemTextView);
        accTextView.setText("Account");
        TextView accInformationView = accountView.findViewById(R.id.information);
        accInformationView.setText("Security notifications, change number");
        ImageView accImage = accountView.findViewById(R.id.itemImage);
        accImage.setImageResource(R.drawable.key);

        avatarView = view.findViewById(R.id.avatar);

        TextView avtTextView = avatarView.findViewById(R.id.itemTextView);
        avtTextView.setText("Avatar");
        TextView avtInformationView = avatarView.findViewById(R.id.information);
        avtInformationView.setText("Create, edit, profile photo");
        ImageView avtImage = avatarView.findViewById(R.id.itemImage);
        avtImage.setImageResource(R.drawable.user_avatar_profile);

        privacyView = view.findViewById(R.id.privacy);
        TextView priTextView = privacyView.findViewById(R.id.itemTextView);
        priTextView.setText("Privacy");
        TextView priInformationView = privacyView.findViewById(R.id.information);
        priInformationView.setText("Block contacts, disappearing messages");
        ImageView priImage = privacyView.findViewById(R.id.itemImage);
        priImage.setImageResource(R.drawable.lock_keyhole);

        chatsView = view.findViewById(R.id.chats);
        TextView chaTextView = chatsView.findViewById(R.id.itemTextView);
        chaTextView.setText("Chats");
        TextView chaInformationView = chatsView.findViewById(R.id.information);
        chaInformationView.setText("Theme, wallpapers, chat history");
        ImageView chatImage = chatsView.findViewById(R.id.itemImage);
        chatImage.setImageResource(R.drawable.chat);

        notificationsView = view.findViewById(R.id.notification);
        TextView notiTextView = notificationsView.findViewById(R.id.itemTextView);
        notiTextView.setText("Notifications");
        TextView notiInformationView = notificationsView.findViewById(R.id.information);
        notiInformationView.setText("Message, group & call tones");
        ImageView notiImage = notificationsView.findViewById(R.id.itemImage);
        notiImage.setImageResource(R.drawable.notification);

        storageView = view.findViewById(R.id.storageAndData);
        TextView storageTextView = storageView.findViewById(R.id.itemTextView);
        storageTextView.setText("Storage and data");
        TextView storageInformationView = storageView.findViewById(R.id.information);
        storageInformationView.setText("Network usage, auto-download");
        ImageView strImage = storageView.findViewById(R.id.itemImage);
        strImage.setImageResource(R.drawable.storage_badged);

        appLanguageView = view.findViewById(R.id.appLanguage);
        TextView appTextView = appLanguageView.findViewById(R.id.itemTextView);
        appTextView.setText("App language");
        TextView appInformationView = appLanguageView.findViewById(R.id.information);
        appInformationView.setText("English(device's language");
        ImageView appImage = appLanguageView.findViewById(R.id.itemImage);
        appImage.setImageResource(R.drawable.global);

        helpView = view.findViewById(R.id.help);
        TextView helpTextView = helpView.findViewById(R.id.itemTextView);
        helpTextView.setText("Help");
        TextView helpInformationView = helpView.findViewById(R.id.information);
        helpInformationView.setText("Help centre, contact us, privacy policy");
        ImageView helpImage = helpView.findViewById(R.id.itemImage);
        helpImage.setImageResource(R.drawable.help);

        inviteView = view.findViewById(R.id.inviteAFriend);
        TextView invTextView = inviteView.findViewById(R.id.itemTextView);
        invTextView.setText("Invite a friend");
        TextView invInformationView = inviteView.findViewById(R.id.information);
        invInformationView.setText("");
        ImageView invImage = inviteView.findViewById(R.id.itemImage);
        invImage.setImageResource(R.drawable.people);



    }
}