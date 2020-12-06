package com.task.service.validator;



import com.task.service.exception.handler.ErrorFieldResponse;
import com.task.service.exception.handler.ValidationError;
import com.task.service.exception.models.BaseException;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;


@Component
public class RequestFieldsValidator {

    public void validate(final Errors errors) {

        List<ErrorFieldResponse> errorList = new ArrayList<>();
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> {
                ErrorFieldResponse fieldError = new ErrorFieldResponse();
                fieldError.setField(error.getObjectName());
                fieldError.setMessage(error.getDefaultMessage());

                errorList.add(fieldError);
            });
            throw new BaseException(
                    ValidationError.VALIDATION_ERROR, errorList);
        }
    }


    public void validate(final Object obj) {

        if (obj == null) {
            throw new BaseException("Please send correct data");
        }
    }
}
