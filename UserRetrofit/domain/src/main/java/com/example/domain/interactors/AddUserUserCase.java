package com.example.domain.interactors;

import com.example.domain.entity.UserEntity;
import com.example.domain.executor.PostExecutionThread;
import com.example.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Completable;

public class AddUserUserCase extends BaseUseCase {


    private UserRepository userRepository;

    @Inject
    public AddUserUserCase(PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }


    public Completable addUser(UserEntity userEntity) {
        return userRepository.addUser(userEntity).
                subscribeOn(threadExecution)
                .observeOn(postExecutionThread);

    }
}
