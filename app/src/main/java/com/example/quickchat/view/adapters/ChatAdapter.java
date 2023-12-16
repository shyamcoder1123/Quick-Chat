package com.example.quickchat.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickchat.BR;
import com.example.quickchat.R;
import com.example.quickchat.databinding.RowChatBinding;
import com.example.quickchat.model.ChatModel;
import com.example.quickchat.model.MessageModel;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatAdapterViewHolder> {
    private List<MessageModel> messageModelList;
    private Context context;

    public ChatAdapter(List<MessageModel> messageModelList, Context context) {
        this.messageModelList = messageModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_chat,parent,false);
        RowChatBinding rowChatBinding = DataBindingUtil.bind(view);
        return new ChatAdapterViewHolder(rowChatBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapterViewHolder holder, int position) {
        holder.getRowChatBinding().setVariable(BR.messageModel,messageModelList.get(position));
        holder.getRowChatBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return messageModelList.size();
    }

    public class ChatAdapterViewHolder extends RecyclerView.ViewHolder {
        private RowChatBinding rowChatBinding;
        public ChatAdapterViewHolder(RowChatBinding rowChatBinding) {
            super(rowChatBinding.getRoot());
            setBinding(rowChatBinding);
        }

        public RowChatBinding getRowChatBinding() {
            return rowChatBinding;
        }

        private void setBinding(RowChatBinding rowChatBinding) {
            this.rowChatBinding = rowChatBinding;
        }
    }
}
