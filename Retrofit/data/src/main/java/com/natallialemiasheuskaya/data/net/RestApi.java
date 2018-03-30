package com.natallialemiasheuskaya.data.net;




import com.natallialemiasheuskaya.data.entity.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestApi {

    @GET("data/User")
    Observable<List<User>> loadUsers();

    @GET("data/User/{id}")
    Observable<User> loadUserById(@Path("id") String id);

    @PUT("data/User")
    Completable saveUser(@Body User user);

    @DELETE("data/User/{id}")
    Completable removeUser(@Path("id") String id);



}


