package com.example.data.net;


import com.example.data.entity.Error;
import com.example.data.entity.ErrorRest;
import com.example.data.entity.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Path;

@Singleton
public class RestService {

    private RestApi restApi;

    private ErrorTransformers errorTransformers;


    @Inject
    public RestService(RestApi restApi,ErrorTransformers errorTransformers) {
        this.restApi = restApi;
        this.errorTransformers = errorTransformers;
    }
    public Flowable<List<User>> loadUsers(){
        return restApi.loadUsers()
                .compose(errorTransformers.<List<User>, ErrorRest>parseHttpError());
    }

    public Flowable<User> loadUserById(@Path("id") String id){
        return restApi.loadUserById(id)
                .compose(errorTransformers.<User, ErrorRest>parseHttpError());
    }

    public Completable saveUser(User user) {
        return restApi.saveUser(user);
    }

    public Completable addUser(User user) {
        return restApi.addUser(user);
    }

    public Completable removeUser(String id) {
        return restApi.removeUser(id);
    }

}
