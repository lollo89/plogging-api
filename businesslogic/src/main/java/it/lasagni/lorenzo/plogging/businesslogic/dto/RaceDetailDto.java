package it.lasagni.lorenzo.plogging.businesslogic.dto;

import java.util.Set;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter 
@SuperBuilder
public class RaceDetailDto extends RaceDto {

    Set<EmployeePickedUpKilosDto> Runners;
}
