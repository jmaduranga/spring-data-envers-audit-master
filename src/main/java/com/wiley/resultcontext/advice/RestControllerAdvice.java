package com.wiley.resultcontext.advice;

import com.wiley.resultcontext.controller.AssessmentRestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice(assignableTypes = {AssessmentRestController.class})
public class RestControllerAdvice {

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> handleCacheException(ConstraintViolationException ex) {
        log.error("user login exception: ", ex.getMessage());

        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleArgumentMismatchException(MethodArgumentTypeMismatchException ex) {
        log.error("Invalid types: ", ex.getMessage());
        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
