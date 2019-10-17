package com.stackroute.MuzixApplication.globalException;

import com.stackroute.MuzixApplication.exceptions.TrackAlreadyExistsException;
import com.stackroute.MuzixApplication.exceptions.TrackNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler(TrackNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody
    ExceptionResponse handleResourceNotFound(final TrackNotFoundException exception,
                                             final HttpServletRequest request) {

        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());

        return error;
    }

    @ExceptionHandler(TrackAlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.IM_USED)
    public @ResponseBody ExceptionResponse handleException(final TrackAlreadyExistsException exception,
                                                           final HttpServletRequest request) {

        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());

        return error;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ExceptionResponse handleException(final Exception exception,
                                                           final HttpServletRequest request) {

        ExceptionResponse error = new ExceptionResponse();
        error.setErrorMessage(exception.getMessage());

        return error;
    }

}
