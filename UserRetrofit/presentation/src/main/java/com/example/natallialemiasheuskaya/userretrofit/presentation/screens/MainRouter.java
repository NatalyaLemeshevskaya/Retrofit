package com.example.natallialemiasheuskaya.userretrofit.presentation.screens;

import android.app.Activity;
import android.content.Intent;

import com.example.natallialemiasheuskaya.userretrofit.presentation.base.Router;


public class MainRouter extends Router {

    public MainRouter(Activity activity) {
        super(activity);
    }

    public void navigateToUser(String id){
        Intent intent = new Intent(getActivity(),UserActivity.class);
        intent.putExtra("ID",id);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(intent);

    }

    public void back(){


    }
}
