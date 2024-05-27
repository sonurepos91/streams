package com.streams8.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseFunctionalException extends RuntimeException {

    private String fieldName;
    private String filedValue;
    private String message;
}
