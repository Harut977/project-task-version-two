package com.task.service.base.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResponseMessages {

    CREATED("Your Object is created"),
    NOT_CREATED("Your Object is not created"),
    DELETED("Your Object is deleted"),
    NOT_DELETED("Your Object is not deleted"),
    UPDATED("Your Object is updated"),
    NOT_UPDATED("Your Object is not updated"),
    GET("Is your Object");

    private String message;
}
