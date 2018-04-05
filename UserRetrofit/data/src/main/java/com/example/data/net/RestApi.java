package com.example.data.net;

import com.example.data.entity.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestApi {

    @GET("data/User")
    Flowable<List<User>> loadUsers();

    @GET("data/User/{id}")
    Flowable<User> loadUserById(@Path("id") String id);

    @PUT("data/User")
    Completable saveUser(@Body User user);

    @POST("data/User")
    Completable addUser(@Body User user);

    @DELETE("data/User/{id}")
    Completable removeUser(@Path("id") String id);



}


