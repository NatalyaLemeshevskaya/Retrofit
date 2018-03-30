package com.natallialemiasheuskaya.domain.interactors;



import com.natallialemiasheuskaya.domain.executor.PostExecutionThread;
import com.natallialemiasheuskaya.domain.executor.ThreadExecution;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;


public abstract class BaseUseCase {
    // потом в кот будет получать результаты в презентейшн слое
    protected Scheduler postExecutionThread;
    // другой поток(не UI) в кот будет выполнять сложные запросы
    protected Scheduler threadExecution;

    public BaseUseCase(PostExecutionThread postExecutionThread, ThreadExecution threadExecution) {
        this.postExecutionThread = postExecutionThread.getScheduler();
        this.threadExecution = Schedulers.from(threadExecution);
    }

    public BaseUseCase(PostExecutionThread postExecutionThread ) {
        this.postExecutionThread = postExecutionThread.getScheduler();
        this.threadExecution = Schedulers.io();
    }
}
