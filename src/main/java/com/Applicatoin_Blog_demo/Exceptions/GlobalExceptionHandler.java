package com.Applicatoin_Blog_demo.Exceptions;


import com.Applicatoin_Blog_demo.Payroads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends Throwable {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> ResourceNotFoundExceptionHandler(ResourceNotFoundException ex)
    {

        String message= ex.getMessage();

        ApiResponse apiResponse= new ApiResponse(message,false);

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> HandleMethodArgsNotValidException(MethodArgumentNotValidException ex)
    {
        Map<String,String> Response= new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((Error)->{
            String fieldName= ((FieldError)Error).getField();
            String Message = ((FieldError)Error).getDefaultMessage();
            Response.put(fieldName,Message);
        });

        return new ResponseEntity<>(Response,HttpStatus.BAD_REQUEST);
    }
}
