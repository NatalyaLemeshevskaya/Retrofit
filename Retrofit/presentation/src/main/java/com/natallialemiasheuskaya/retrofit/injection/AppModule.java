package com.natallialemiasheuskaya.retrofit.injection;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.natallialemiasheuskaya.data.net.RestApi;
import com.natallialemiasheuskaya.data.net.RestService;
import com.natallialemiasheuskaya.data.repository.UserRepositoryImpl;
import com.natallialemiasheuskaya.domain.executor.PostExecutionThread;
import com.natallialemiasheuskaya.domain.repository.UserRepository;
import com.natallialemiasheuskaya.retrofit.executor.UIThread;


import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {// помогает даггеру найти нужный тип

    Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides//-если кто то попросит обьект с методом context вызовет именно этот метод
    @Singleton
    public Context getContext(){
        return  context;
    }

    @Provides
    @Singleton
    public PostExecutionThread getUiThread(){
        return new UIThread();
    }

    @Provides
    @Singleton
    @Named("repository1")
    public UserRepository getUserRepository(Context context, RestService restService){
        return new UserRepositoryImpl(context,restService);
    }


    @Provides
    @Singleton
    public Retrofit getRetrofit(OkHttpClient okHttpClient, Gson gson){
        return new Retrofit.Builder()
                .baseUrl("https://api.backendless.com/70E26EEB-3ACD-601D-FF12-541F239F8800/FDBEBFDC-2C3B-E045-FF00-D718E4134700/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

    }

    @Provides
    @Singleton
    public RestApi getRestApi(Retrofit retrofit){
        return retrofit.create(RestApi.class);

    }

    @Provides
    @Singleton
    public OkHttpClient getOkHttp(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder()//здесь можно кешировать данные
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS);
            HttpLoggingInterceptor httpLogging = new HttpLoggingInterceptor();
            httpLogging.setLevel(HttpLoggingInterceptor.Level.BODY);

            //добавить что то в промежутке перед отправкой данных
            builder.addInterceptor(httpLogging);

        return builder.build();

    }


    @Provides
    @Singleton
    public Gson getGson(){
        return new GsonBuilder().create();
    }

//    @Provides
//    @Singleton
//    @Named("repository2")
//    public UserRepository getUserRepository2(){
//        return new UserRepositoryImpl();
//    }




    //    =
//    @Binds
//    public abstract PostExecutionThread getUiThread(UIThread uiThread);
//    весь класс тоже делает abstract
//
}
