package com.task.service.exception.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class NotFoundException extends Exception {


    private HttpStatus httpStatus;

    private boolean success = false;

    public NotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public NotFoundException(String message, HttpStatus httpStatus, boolean success) {
        super(message);
        this.httpStatus = httpStatus;
        this.success = success;
    }
}