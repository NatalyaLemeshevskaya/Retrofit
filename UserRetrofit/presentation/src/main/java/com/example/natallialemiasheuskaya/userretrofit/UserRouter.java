package com.example.natallialemiasheuskaya.userretrofit;

import android.app.Activity;
import android.content.Intent;

import com.example.natallialemiasheuskaya.userretrofit.base.Router;

public class UserRouter extends Router {

    public UserRouter(Activity activity) {
        super(activity);
    }

    public void navigateToUser(String id){

        Intent intent = new Intent(getActivity(),UserActivity.class);
        intent.putExtra("key",id);
        getActivity().startActivity(intent);

    }

    public void back(){


    }
}
