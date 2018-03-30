package com.natallialemiasheuskaya.domain.interactors;



import com.natallialemiasheuskaya.domain.entity.UserEntity;
import com.natallialemiasheuskaya.domain.executor.PostExecutionThread;
import com.natallialemiasheuskaya.domain.repository.UserRepository;

import java.util.List;

import javax.inject.Inject;

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

    public Observable<List<UserEntity>> get(){

        return userRepository
                .get()
                .subscribeOn(threadExecution )
                .observeOn(postExecutionThread);


    }
}
