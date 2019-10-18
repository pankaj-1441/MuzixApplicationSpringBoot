package com.stackroute.MuzixApplication.globalException;

public class Response extends Exception{

    private String errorMessage;
    private int id;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}