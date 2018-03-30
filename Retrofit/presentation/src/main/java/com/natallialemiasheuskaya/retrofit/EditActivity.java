package com.natallialemiasheuskaya.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;

import com.natallialemiasheuskaya.retrofit.base.BaseMVVMActivity;
import com.natallialemiasheuskaya.retrofit.base.BaseViewModel;


public class EditActivity extends BaseMVVMActivity{

    @Override
    public int provideLayoutId() {
        return R.layout.activity_edit;
    }

    @Override
    public BaseViewModel provideViewModel() {
        return new EditViewModel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
