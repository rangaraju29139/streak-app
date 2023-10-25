package com.example.streakapp.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class TaskNotCreateException extends RuntimeException {

    public TaskNotCreateException(String message){
        super(message);
        System.out.println(message);

    }
}
