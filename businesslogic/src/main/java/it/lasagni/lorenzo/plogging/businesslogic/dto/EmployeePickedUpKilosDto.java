package it.lasagni.lorenzo.plogging.businesslogic.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter 
@Setter
@Accessors(chain = true)
public class EmployeePickedUpKilosDto {
    private String employeeName;
    private float pickedUpKilos;

}
