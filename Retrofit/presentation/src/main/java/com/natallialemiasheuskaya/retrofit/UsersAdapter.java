package com.natallialemiasheuskaya.retrofit;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.natallialemiasheuskaya.domain.entity.UserEntity;
import com.natallialemiasheuskaya.retrofit.databinding.ItemUserProfileBinding;


import java.util.ArrayList;
import java.util.List;


public class UsersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<UserEntity> list = new ArrayList<>();
    private OnClickUserListener listener;

    public OnClickUserListener getListener() {
        return listener;
    }

    public void setListener(OnClickUserListener listener) {
        this.listener = listener;
    }

    public void setList(List<UserEntity> list) {
        Log.e("sds","adapterset");
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemUserProfileBinding binding = ItemUserProfileBinding.inflate(inflater, parent, false);
        return new Holder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Log.e("sds","adapterOnBind");
        Holder  myHolder = (Holder)holder;
        myHolder.itemUserProfileBinding.setUser(list.get(position));
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(list.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Holder extends RecyclerView.ViewHolder{

        ItemUserProfileBinding itemUserProfileBinding;

        Holder(View itemView) {
            super(itemView);

            itemUserProfileBinding = DataBindingUtil.bind(itemView);
        }
    }

    interface OnClickUserListener {
        void onClick (UserEntity userEntity);
    }
}

