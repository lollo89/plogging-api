package it.lasagni.lorenzo.plogging.businesslogic.exception;

import lombok.Getter;

public class EmployeeNotFoundException extends RuntimeException {
    
    @Getter private String EmailNotFound;

    public EmployeeNotFoundException(String email) {
        super("Employee " + email + " not found");

        this.EmailNotFound = email;
    }
}