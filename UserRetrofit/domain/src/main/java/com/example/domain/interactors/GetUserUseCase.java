package com.example.domain.interactors;


import com.example.domain.entity.UserEntity;
import com.example.domain.executor.PostExecutionThread;
import com.example.domain.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by natallialemiasheuskaya on 25.03.2018.
 */

public class GetUserUseCase extends BaseUseCase {

    private UserRepository userRepository;

    @Inject
    public GetUserUseCase(PostExecutionThread postExecutionThread,
                              UserRepository userRepository) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }

    public Flowable<List<UserEntity>> get(){

        return userRepository
                .get()
                .subscribeOn(threadExecution )
                .observeOn(postExecutionThread);


    }
}
