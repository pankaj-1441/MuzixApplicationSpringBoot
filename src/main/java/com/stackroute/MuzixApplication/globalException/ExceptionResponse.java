package com.stackroute.MuzixApplication.globalException;

public class ExceptionResponse extends Exception{

    private String errorMessage;


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

}