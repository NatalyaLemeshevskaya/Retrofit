package com.natallialemiasheuskaya.retrofit;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.natallialemiasheuskaya.domain.entity.UserEntity;
import com.squareup.picasso.Picasso;


import java.util.List;

public class DataBind {

    @BindingAdapter({"src"})
    public static void loadImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).into(view);
    }

}
