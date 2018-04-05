package com.example.domain.repository;


import com.example.domain.entity.UserEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;


public interface UserRepository {

    //надо flowable
    Flowable<UserEntity> get(String id);
    Flowable<List<UserEntity>> get();
    Completable save(UserEntity userEntity);
    Completable addUser(UserEntity userEntity);
    Completable remove(String id);
}
