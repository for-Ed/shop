package com.shop.shop.configuration.exception.handler;

import com.shop.shop.configuration.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> handle(NotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        //return handleExceptionInternal(ex, bodyOfResponse,new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
     /*for a custom http status
     @org.springframework.web.bind.annotation.ExceptionHandler(value = {NotFoundException.class})
     protected void handle(NotFoundException ex, WebRequest request, HttpServletResponse response) {
         //response.setStatus(666);
         try {
             response.sendError(666, ex.getMessage());
         }
         catch (IOException ignored){}
     }
     //return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);*/
     @org.springframework.web.bind.annotation.ExceptionHandler(value = {IllegalArgumentException.class})
     protected ResponseEntity<Object> handle(IllegalArgumentException ex){
         return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
         //return handleExceptionInternal(ex, bodyOfResponse,new HttpHeaders(), HttpStatus.CONFLICT, request);
     }

}