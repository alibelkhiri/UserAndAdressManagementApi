package com.abscript.brightcodingspringv2.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.abscript.brightcodingspringv2.responses.ErrorMessage;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(value = {UserException.class})
    public ResponseEntity<Object> handlUserException(UserException ex,WebRequest request){
        ErrorMessage errorMessage=new ErrorMessage(new Date(),ex.getMessage());
        return new ResponseEntity<>(errorMessage,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handlOtherException(UserException ex,WebRequest request){
        ErrorMessage errorMessage=new ErrorMessage(new Date(),ex.getMessage());
        return new ResponseEntity<>(errorMessage,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handlMethodArgumentNotValid(MethodArgumentNotValidException ex,WebRequest request){
        
        Map<String,String> errorsMap=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            errorsMap.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errorsMap,new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
