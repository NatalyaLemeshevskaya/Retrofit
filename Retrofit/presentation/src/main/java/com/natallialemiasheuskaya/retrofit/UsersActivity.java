package com.natallialemiasheuskaya.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.natallialemiasheuskaya.retrofit.base.BaseMVVMActivity;
import com.natallialemiasheuskaya.retrofit.databinding.UsersActivityBinding;


public class UsersActivity extends BaseMVVMActivity<UsersActivityBinding,UsersViewModel> {

    @Override
    public int provideLayoutId() {
        return R.layout.users_activity;
    }

    @Override
    public UsersViewModel provideViewModel() {
        return new UsersViewModel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.RVUsers);
        recyclerView.setAdapter(viewModel.usersAdapter );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }
}
