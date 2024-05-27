package com.streams8.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice public class ExceptionTransalator<T> {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(UniversityAlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<T> handleUniversityAlreadyRegisteredException(UniversityAlreadyRegisteredException ex){
        ValidationFieldError validationFieldError = new ValidationFieldError(ex.getFieldName(), ex.getFiledValue(),
                messageSource.getMessage("university.already.exists",null, LocaleContextHolder.getLocale()));
        return new ResponseEntity<>((T)validationFieldError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateCollegeInfoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<T> handleDuplicateCollegeInfoException(DuplicateCollegeInfoException ex){
        ValidationFieldError error = new ValidationFieldError(ex.getFieldName(), ex.getFiledValue(),
                messageSource.getMessage("duplicate.college.info", null,LocaleContextHolder.getLocale()));
        return new ResponseEntity<>((T) error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchCollegeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<T> handleNoCollegeRegisteredException(NoSuchCollegeException ex){
        ValidationFieldError error =new ValidationFieldError(ex.getFieldName(), ex.getFiledValue(),
                messageSource.getMessage("college.not.registered",null,LocaleContextHolder.getLocale()));
        return new ResponseEntity<>((T) error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoCollegeInStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<T> NoCollegeRegisterdInStateException(NoCollegeInStateException ex){
        ValidationFieldError error = new ValidationFieldError(ex.getFieldName(), ex.getFiledValue(),
                messageSource.getMessage("no.colleges.registered.in.state",null,LocaleContextHolder.getLocale()));
        return new ResponseEntity<>((T) error ,HttpStatus.BAD_REQUEST);

    }
}
