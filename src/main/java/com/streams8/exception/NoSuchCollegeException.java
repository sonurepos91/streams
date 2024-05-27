package com.streams8.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoSuchCollegeException extends BaseFunctionalException{

    public NoSuchCollegeException(String fieldName, String fieldValue, String message){
        super(fieldName,fieldValue,message);
    }

}
