package it.lasagni.lorenzo.plogging.businesslogic.exception;

import lombok.Getter;

public class EmployeeNotFoundException extends RuntimeException {
    
    @Getter private Integer IdNotFound;

    public EmployeeNotFoundException(Integer id) {
        super("Employee " + id + " not found");

        this.IdNotFound = id;
    }
}