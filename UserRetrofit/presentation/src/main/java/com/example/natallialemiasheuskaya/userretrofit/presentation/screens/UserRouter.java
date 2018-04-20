package com.example.natallialemiasheuskaya.userretrofit.presentation.screens;

import android.app.Activity;
import android.content.Intent;

import com.example.natallialemiasheuskaya.userretrofit.presentation.base.Router;

public class UserRouter extends Router {

    public UserRouter(Activity activity) {
        super(activity);
    }

    public void goBack(){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(intent);
    }


}
