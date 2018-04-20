package com.example.natallialemiasheuskaya.userretrofit.presentation.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.natallialemiasheuskaya.userretrofit.ItemAdapter;
import com.example.natallialemiasheuskaya.userretrofit.R;
import com.example.natallialemiasheuskaya.userretrofit.presentation.base.BaseMVVMActivity;
import com.example.natallialemiasheuskaya.userretrofit.databinding.ActivityMainBinding;

public class MainActivity extends BaseMVVMActivity<ActivityMainBinding,MainViewModel,MainRouter>{


    @Override
    public int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel provideViewModel() {
        return new MainViewModel();
    }

    @Override
    public MainRouter provideRouter() {
        return new MainRouter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecyclerView recyclerView = binding.usersRV;
        recyclerView.setAdapter(viewModel.itemAdapter);
       // recyclerView.setAdapter(viewModel.itemAdapter2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

//        viewModel.itemAdapter.setListener(new ItemAdapter.OnUserClickListener() {
//            @Override
//            public void onUserClick(String id) {
//                Intent intent  = new Intent(MainActivity.this,UserActivity.class);
//                intent.putExtra("ID",id);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                Log.e("Main","id");
//                startActivity(intent);
//            }
//        });

    }

}
