package com.task.service.exception.models;

import com.task.service.exception.handler.ErrorCode;
import com.task.service.exception.handler.ErrorFieldResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {

    private ErrorCode errorCode;
    private List<ErrorFieldResponse> errors;


    public BaseException(ErrorCode errorCode, List<ErrorFieldResponse> errors) {
        this.errorCode = errorCode;
        this.errors = errors;
    }

    public BaseException(String message) {
        super(message);
    }
}
