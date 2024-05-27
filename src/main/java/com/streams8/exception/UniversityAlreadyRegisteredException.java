package com.streams8.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UniversityAlreadyRegisteredException extends BaseFunctionalException{

    public UniversityAlreadyRegisteredException(String fieldName, String  filedValue , String message){
        super(fieldName,filedValue,message);
    }
}
