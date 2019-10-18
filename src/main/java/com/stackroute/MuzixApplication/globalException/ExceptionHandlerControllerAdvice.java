package com.stackroute.MuzixApplication.globalException;

import com.stackroute.MuzixApplication.exceptions.TrackAlreadyExistsException;
import com.stackroute.MuzixApplication.exceptions.TrackNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = TrackNotFoundException.class)
    public ResponseEntity<Response> globalExcNotFound(TrackNotFoundException ex)throws Exception{
        Response response=new Response();
        response.setId(HttpStatus.NOT_FOUND.value());
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = TrackAlreadyExistsException.class)
    public ResponseEntity<Response> globalExcAlreadyExists(TrackAlreadyExistsException ex)throws Exception{
        Response response=new Response();
        response.setId(HttpStatus.CONFLICT.value());
        response.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}

