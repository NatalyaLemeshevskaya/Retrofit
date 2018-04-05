package com.example.data.repository;

import android.content.Context;
import android.media.UnsupportedSchemeException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;


import com.example.data.db.AppDatabase;
import com.example.data.db.UserDao;
import com.example.data.entity.User;
import com.example.data.net.RestService;
import com.example.domain.entity.UserEntity;
import com.example.domain.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by natallialemiasheuskaya on 29.03.2018.
 */
public class UserRepositoryImpl implements UserRepository {


    private Context context;
    private RestService restService;
    private UserDao userDao;


    @Inject
    public UserRepositoryImpl(Context context, RestService restService,AppDatabase database) {
        this.restService = restService;
        this.context = context;
        this.userDao = database.userDao();

    }

    @Override
    public Flowable<UserEntity> get(String id) {


        final Flowable<User> user;

        if(isNetworkAvailable()) {
            user =restService
                    .loadUserById(id);

        }else
            user = userDao.getById(id).take(1)
                    .map(new Function<List<User>, User>() {
                        @Override
                        public User apply(List<User> users) throws Exception {
                            return users.get(0);
                        }
                    });

        return user
                .map(new Function<User, UserEntity>() {
                    @Override

                    public UserEntity apply(User user) throws Exception {
                        return new UserEntity(user.getUserName(),
                                user.getAge(),
                                user.getProfileUrl(),user.getObjectId());
                    }
                });
    }

    @Override
    public Flowable<List<UserEntity>> get() {

        Flowable<List<User>> users;

        if(isNetworkAvailable()) {
            users =restService
                    .loadUsers()
                    .doOnNext(new Consumer<List<User>>() {
                        @Override
                        public void accept(List<User> users) throws Exception {
                            userDao.deleteAll();
                            userDao.insert(users);
                        }
                    });
        }else
            users = userDao.getAll().take(1);

//
//        return restService
//                .loadUsers()
//                .doOnNext(new Consumer<List<User>>() {
//                    @Override
//                    public void accept(List<User> users) throws Exception {
//                        userDao.getAll();
//                        userDao.insert(users);
//                    }
//                })
//                .onErrorResumeNext(new Function<Throwable, Flowable<? extends List<User>>>() {
//                    @Override
//                    public Flowable<? extends List<User>> apply(Throwable throwable) throws Exception {
//                        //проверить на ошибку, если с интернетом
//                        return userDao.getAll().take(1);
//                    }
//                })

        return users
                .map(new Function<List<User>, List<UserEntity>>() {
                    @Override
                    public List<UserEntity> apply(List<User> users) throws Exception {

                        List<UserEntity> list = new ArrayList<>();
                        for(User user: users){
                            list.add(new UserEntity(user.getUserName(),
                                    user.getAge(),user.getProfileUrl(),user.getObjectId()));
                        }
                        return list;
                    }
                })
                ;
    }

    @Override
    public Completable save(UserEntity userEntity) {
        if(isNetworkAvailable()) {
            User user = new User();
            user.setUserName(userEntity.getUserName());
            user.setProfileUrl(userEntity.getProfileUrl());
            user.setAge(userEntity.getAge());
            user.setObjectId(userEntity.getId());
            return restService.saveUser(user);
        }else {
            Toast.makeText(context,"No network connection",Toast.LENGTH_SHORT);
            return Completable.error(new Throwable("No network connection"));
          //  return Toast.makeText(context,"No network connection",Toast.LENGTH_SHORT);
        }
    }

    @Override
    public Completable addUser(UserEntity userEntity) {
        if(isNetworkAvailable()) {
            User user = new User();
            user.setUserName(userEntity.getUserName());
            user.setProfileUrl(userEntity.getProfileUrl());
            user.setAge(userEntity.getAge());
            user.setObjectId(userEntity.getId());
            return restService.addUser(user);
        }else {
            Toast.makeText(context,"No network connection",Toast.LENGTH_SHORT);
            return Completable.error(new Throwable("No network connection"));
        }
    }

    @Override
    public Completable remove(String id) {
        if(isNetworkAvailable()) {
            return restService.removeUser(id);
        }else {
            Toast.makeText(context,"No network connection",Toast.LENGTH_SHORT);
            return Completable.error(new Throwable("No network connection"));
        }
    }


    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
