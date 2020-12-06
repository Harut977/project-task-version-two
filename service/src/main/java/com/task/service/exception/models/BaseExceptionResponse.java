package com.task.service.exception.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseExceptionResponse<T> {

    private Boolean success;

    private String message;

    private T data;
}
