package com.example.natallialemiasheuskaya.userretrofit.mvp;

import com.example.domain.entity.UserEntity;
import com.example.natallialemiasheuskaya.userretrofit.mvp.base.BaseView;

public interface UserView extends BaseView {

    void showUser(UserEntity userEntity);
}
