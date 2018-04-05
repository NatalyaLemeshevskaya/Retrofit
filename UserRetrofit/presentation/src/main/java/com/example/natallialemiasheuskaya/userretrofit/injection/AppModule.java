package com.example.natallialemiasheuskaya.userretrofit.injection;


import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.data.db.AppDatabase;
import com.example.data.net.RestApi;
import com.example.data.net.RestService;
import com.example.data.repository.UserRepositoryImpl;
import com.example.domain.executor.PostExecutionThread;
import com.example.domain.repository.UserRepository;
import com.example.natallialemiasheuskaya.userretrofit.BuildConfig;
import com.example.natallialemiasheuskaya.userretrofit.executor.UIThread;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


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
    public UserRepository getUserRepository(Context context, RestService restService, AppDatabase dataBase){
        return new UserRepositoryImpl(context,restService,dataBase);
    }


    @Provides
    @Singleton
    public Retrofit getRetrofit(OkHttpClient okHttpClient, Gson gson){
        return new Retrofit.Builder()
                .baseUrl("https://api.backendless.com/13CD9715-647F-B683-FF55-4A30E465FC00/3E891234-25F9-8394-FF0F-62615CA0C800/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

    }

    @Provides
    @Singleton
    public OkHttpClient getOkHttp() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }
        return builder.build();
    }




    @Provides
    @Singleton
    public RestApi getRestApi(Retrofit retrofit){
        return retrofit.create(RestApi.class);

    }

    @Provides
    @Singleton
    public AppDatabase getDataBase(Context context){

        AppDatabase appDatBase = Room.databaseBuilder(context,
                AppDatabase.class,"database")
                .fallbackToDestructiveMigration()
                .build();

        return appDatBase;

    }

    @Provides
    @Singleton
    public Gson getGson(){
        return new GsonBuilder().create();
    }

}
