package com.example.natallialemiasheuskaya.userretrofit;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.domain.entity.UserEntity;
import com.example.natallialemiasheuskaya.userretrofit.databinding.ItemUsersBinding;

import java.util.ArrayList;
import java.util.List;


public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private List<UserEntity> list = new ArrayList<>();
    private OnUserClickListener listener;

    public OnUserClickListener getListener() {
        return listener;
    }

    public void setListener(OnUserClickListener listener) {
        this.listener = listener;
    }

    public void setImageList(List<UserEntity> list) {

        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemUsersBinding binding = ItemUsersBinding.inflate(inflater, parent, false);
        return new Holder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Log.e("sds","adapterOnBind");
        Holder  myHolder = (Holder)holder;
        myHolder.itemUsersBinding.setUser(list.get(position));
        myHolder.itemUsersBinding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onUserClick(list.get(position).getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Holder extends RecyclerView.ViewHolder{

        ItemUsersBinding itemUsersBinding;

        Holder(View itemView) {
            super(itemView);

            itemUsersBinding = DataBindingUtil.bind(itemView);
        }
    }

    public interface  OnUserClickListener{
        void onUserClick(String id);
    }
}
