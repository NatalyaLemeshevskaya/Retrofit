package com.example.data.entity;

public class Error extends Exception {

    private ErrorType myError;

    public ErrorType getMyError() {
        return myError;
    }


    public Error(ErrorType myError) {
        this.myError = myError;
    }
}
