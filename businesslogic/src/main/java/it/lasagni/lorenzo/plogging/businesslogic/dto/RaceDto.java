package it.lasagni.lorenzo.plogging.businesslogic.dto;

import java.util.Date;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter 
@SuperBuilder
public class RaceDto {

    private Integer Id;
    private Date Date;
    private String Name;
}