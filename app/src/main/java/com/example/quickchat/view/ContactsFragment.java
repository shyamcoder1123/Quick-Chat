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
import com.example.quickchat.view.adapters.ContactRecyclerAdapter;
import com.example.quickchat.model.ChatModel;

import java.util.ArrayList;

public class ContactsFragment extends Fragment implements ContactRecyclerAdapter.ContactItemTouchInterface {

    ArrayList<ChatModel> contactModelArrayList;
    ContactRecyclerAdapter contactRecyclerAdapter;
    RecyclerView contactRecyclerView;
    public ContactsFragment() {
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
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contactRecyclerView=view.findViewById(R.id.contactRecycler);
        contactRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        contactModelArrayList=new ArrayList<>();
        addContact();
        contactRecyclerAdapter=new ContactRecyclerAdapter(contactModelArrayList,this::onContactItemTouch,getContext());
        contactRecyclerView.setAdapter(contactRecyclerAdapter);
        contactRecyclerAdapter.notifyDataSetChanged();
    }
    public void addContact(){
        contactModelArrayList.add(new ChatModel("https://www.emmegi.co.uk/wp-content/uploads/2019/01/User-Icon.jpg","Chris John","Hi! morning",""));
        contactModelArrayList.add(new ChatModel("https://e7.pngegg.com/pngimages/572/301/png-clipart-chatbot-avatar-eliza-internet-bot-internet-forum-avatar-business-woman-3d-computer-graphics.png","Sanjana","Hi! morning",""));

        contactModelArrayList.add(new ChatModel("https://th.bing.com/th/id/OIP.XN38b7ZYG5d2kD4b17cfhQHaJo?pid=ImgDet&rs=1","Preeti","Hi! morning",""));

        contactModelArrayList.add(new ChatModel("https://th.bing.com/th/id/OIP.4BqBEibnSKCQTB9IMF6BAQHaL-?pid=ImgDet&rs=1","Priyanka","Hi! morning",""));

        contactModelArrayList.add(new ChatModel("https://i.pinimg.com/originals/59/fd/1f/59fd1ff8c1e5619f8e96121c194b3c20.jpg","Ravi Kumar","Hi! morning",""));
        contactModelArrayList.add(new ChatModel("https://i.pinimg.com/originals/4c/41/ef/4c41eff22888e5e5d8277cf5121691db.png","Sajid","Hi! morning",""));
        contactModelArrayList.add(new ChatModel("https://th.bing.com/th?q=Male+Business+Avatar&w=120&h=120&c=1&rs=1&qlt=90&cb=1&dpr=1.3&pid=InlineBlock&mkt=en-IN&cc=IN&setlang=en&adlt=strict&t=1&mw=247","Dev","Hi! morning",""));
        contactModelArrayList.add(new ChatModel("https://cdn4.vectorstock.com/i/1000x1000/77/43/young-man-head-avatar-cartoon-face-character-vector-21757743.jpg","Shyam","Hi! morning",""));
        contactModelArrayList.add(new ChatModel("https://i.pinimg.com/originals/55/6b/d0/556bd040c7da74d48dc710c3cefa5dd7.jpg","Shivam","Hi! morning",""));

    }

    @Override
    public void onContactItemTouch(int position) {
        Intent i = new Intent(getContext(), ChatActivity.class);
        i.putExtra("profileImageURL",contactModelArrayList.get(position).getProfileImageURL());
        i.putExtra("profileName",contactModelArrayList.get(position).getName());
        startActivity(i);
    }
}