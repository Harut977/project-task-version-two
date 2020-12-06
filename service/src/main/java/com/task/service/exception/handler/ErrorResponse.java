package com.task.service.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
public class ErrorResponse {
    private List<ErrorFieldResponse> errors;
}
