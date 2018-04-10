package com.example.data.net;

import android.view.Display;

import com.example.data.entity.Error;
import com.example.data.entity.ErrorType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.reactivestreams.Publisher;

import java.io.IOException;

import java.lang.reflect.Type;
import java.net.SocketTimeoutException;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import retrofit2.HttpException;

@Singleton
public class ErrorTransformers  {

    private Gson gson;

    @Inject
    public ErrorTransformers(Gson gson) {
        this.gson = gson;
    }

    public <Model, ErrorThrowable extends Error> FlowableTransformer<Model, Model> parseHttpError(){


        return new FlowableTransformer<Model, Model>() {

            @Override
            public Publisher<Model> apply(Flowable<Model> upstream) {
                return upstream.onErrorResumeNext(new Function<Throwable, Publisher<? extends Model>>() {
                    @Override
                    public Publisher<? extends Model> apply(Throwable throwable) throws Exception {


                        if(throwable instanceof SocketTimeoutException) {
                            //сервер не доступен
                            return Flowable.error(new Error(ErrorType.SERVER_NOT_AVAILABLE));
                        }
                        else if(throwable instanceof IOException){

                            Error error = new Error(ErrorType.NO_INTERNET);
                            return Flowable.error(error);

                        }
                        else if (throwable instanceof HttpException){
                            //ошибка с интернетом
                            HttpException httpException = (HttpException) throwable;
                            //сохраняет ошибку(код)
                            String bodyError = (String) httpException.response().body();

                            //
                            Type errorType = new TypeToken<ErrorThrowable>(){}.getType();
                            ErrorThrowable errorThrowable =  gson.fromJson(bodyError,errorType);
                            return Flowable.error(errorThrowable);

                        }

                        return Flowable.error(new Error(ErrorType.UNKNOWN));
                    }
                });
            }
        };


    }


}
