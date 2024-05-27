package com.streams8.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoCollegeInStateException extends BaseFunctionalException{
    public  NoCollegeInStateException(String fieldName, String fieldValue, String message){
        super(fieldName,fieldValue,message);
    }
}
