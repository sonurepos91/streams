package com.streams8.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationFieldError {

    private String field;
    private String code;
    private String message;
}
