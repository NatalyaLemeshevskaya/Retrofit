package com.example.data.entity;

public class ErrorRest extends Error {

    private String codeServerError;

    public ErrorRest(ErrorType myError) {
        super(myError);
    }
}
