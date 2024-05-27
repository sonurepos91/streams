package com.streams8.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DuplicateCollegeInfoException extends BaseFunctionalException{

    public DuplicateCollegeInfoException(String fieldName, String fieldValue, String message){
        super(fieldName,fieldValue,message);
    }
}
