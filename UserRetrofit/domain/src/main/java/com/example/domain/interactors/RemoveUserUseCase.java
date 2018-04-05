package com.example.domain.interactors;

import com.example.domain.executor.PostExecutionThread;
import com.example.domain.repository.UserRepository;

import javax.inject.Inject;

import io.reactivex.Completable;


public class RemoveUserUseCase extends BaseUseCase {
    private UserRepository userRepository;

    @Inject
    public RemoveUserUseCase(PostExecutionThread postExecutionThread, UserRepository userRepository) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }

    public Completable remove(String id) {
        return userRepository.remove(id)
                .subscribeOn(threadExecution)
                .observeOn(postExecutionThread);
    }
}
