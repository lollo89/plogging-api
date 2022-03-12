package it.lasagni.lorenzo.plogging.web.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import it.lasagni.lorenzo.plogging.businesslogic.exception.EmployeeNotFoundException;
import it.lasagni.lorenzo.plogging.businesslogic.exception.RaceNotFoundException;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    
    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(EmployeeNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(RaceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String raceNotFoundHandler(RaceNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String messageNotReadable(HttpMessageNotReadableException ex) {
        return ex.getMessage();
    }
}
