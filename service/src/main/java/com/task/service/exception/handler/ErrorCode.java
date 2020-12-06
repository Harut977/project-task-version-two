package com.task.service.exception.handler;

import org.springframework.http.HttpStatus;

public interface ErrorCode {


    String getName();

    HttpStatus getStatus();

}
