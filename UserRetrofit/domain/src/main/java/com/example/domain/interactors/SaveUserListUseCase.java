package com.example.domain.interactors;


import com.example.domain.entity.UserEntity;
import com.example.domain.executor.PostExecutionThread;
import com.example.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by natallialemiasheuskaya on 26.03.2018.
 */

public class SaveUserListUseCase extends BaseUseCase {

    private UserRepository userRepository;

    @Inject
    public SaveUserListUseCase(PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }


    public Completable save(UserEntity userEntity) {
        return userRepository.save(userEntity).
                subscribeOn(threadExecution)
                .observeOn(postExecutionThread);

    }
}
