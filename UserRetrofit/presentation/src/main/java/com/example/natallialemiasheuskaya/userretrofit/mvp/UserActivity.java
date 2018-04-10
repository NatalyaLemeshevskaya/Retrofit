package com.example.natallialemiasheuskaya.userretrofit.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.domain.entity.UserEntity;
import com.example.natallialemiasheuskaya.userretrofit.R;
import com.example.natallialemiasheuskaya.userretrofit.mvp.base.BaseMVPActivity;

public class UserActivity extends BaseMVPActivity<UserPresenter,UserRouter> implements UserView{


    @Override
    public int provideLayoutId() {
        return R.layout.activiry_user;
    }

    @Override
    public UserPresenter providePresenter() {
        return new SigninUserPresenter();
    }

    @Override
    public UserRouter provideRouter() {
        return new UserRouter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }


    @Override
    public void showUser(UserEntity userEntity) {

        //закидываем данные  в xml, например в textView (нужно предварительно сделать для них findviewById)
    }
}
