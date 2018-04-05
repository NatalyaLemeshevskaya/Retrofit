package com.example.domain.interactors;


import com.example.domain.entity.UserEntity;
import com.example.domain.executor.PostExecutionThread;
import com.example.domain.repository.UserRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Observable;

@Singleton
public class GetUserByIdUseCase extends BaseUseCase {

    private UserRepository userRepository;

    @Inject
    public GetUserByIdUseCase(PostExecutionThread postExecutionThread,
                              UserRepository userRepository) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }

    public Flowable<UserEntity> get(String id){

        return userRepository
                .get(id)
                .subscribeOn(threadExecution )
                .observeOn(postExecutionThread);


    }


}
