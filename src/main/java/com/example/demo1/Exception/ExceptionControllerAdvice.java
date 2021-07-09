package com.example.demo1.Exception;

import com.example.demo1.entity.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @Autowired
    Environment  environment;
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception ex){
        return ex.getMessage();

    }

    @ExceptionHandler(InvalidUserException.class)
    public ResponseEntity<ErrorMessage> exceptionHandler2(InvalidUserException ex){
        ErrorMessage message=new ErrorMessage();
        message.setErrorCode(HttpStatus.BAD_REQUEST.value());
        message.setMessage(ex.getMessage());
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> handleConstraintValidationExceptions(ConstraintViolationException ex)
    {
        ErrorMessage error = new ErrorMessage();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getConstraintViolations()
                .stream().map(ConstraintViolation::getMessage)//lambda equivalent -> x->x.getMessage()
                .collect(Collectors.joining(", ")));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
