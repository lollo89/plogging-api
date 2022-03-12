package it.lasagni.lorenzo.plogging.businesslogic.exception;

import lombok.Getter;

public class RaceNotFoundException extends RuntimeException {
    
    @Getter private Integer IdNotFound;

    public RaceNotFoundException(Integer id) {
        super("Race " + id + " not found");

        this.IdNotFound = id;
    }
}