package com.task.service.exception.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter

public class NotReadException extends RuntimeException {


    private HttpStatus httpStatus;

    public NotReadException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }


}
