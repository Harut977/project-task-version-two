package com.task.service.base.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionMessages {

    PROJECT_NOT_CREATED("Project not created"),
    PROJECT_NOT_UPDATED("Project not updated"),
    PROJECT_NOT_DELETED("Project not deleted"),
    PROJECT_NOT_FOUND("Project not found with this id ="),
    CONTACT_NOT_CREATED("Contact not created"),
    CONTACT_NOT_UPDATED("Contact not updated"),
    CONTACT_NOT_DELETED("Contact not deleted"),
    CONTACT_NOT_FOUND("Contact not found with this id =");

    private String message;
}
