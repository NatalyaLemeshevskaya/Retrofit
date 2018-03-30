package com.natallialemiasheuskaya.domain.interactors;




import com.natallialemiasheuskaya.domain.entity.UserEntity;
import com.natallialemiasheuskaya.domain.executor.PostExecutionThread;
import com.natallialemiasheuskaya.domain.repository.UserRepository;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class GetUserByIdUseCase extends BaseUseCase {

    private UserRepository userRepository;

    @Inject
    public GetUserByIdUseCase(PostExecutionThread postExecutionThread,
                              @Named("repository1") UserRepository userRepository) {
        super(postExecutionThread);
        this.userRepository = userRepository;
    }

    public Observable<UserEntity> get(String id){

        return userRepository
                .get(id)
                .subscribeOn(threadExecution )
                .observeOn(postExecutionThread);


    }


}
