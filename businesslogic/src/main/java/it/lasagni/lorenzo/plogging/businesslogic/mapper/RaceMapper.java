package it.lasagni.lorenzo.plogging.businesslogic.mapper;

import java.util.stream.Collectors;

import it.lasagni.lorenzo.plogging.businesslogic.dto.RaceDetailDto;
import it.lasagni.lorenzo.plogging.businesslogic.dto.RaceDto;
import it.lasagni.lorenzo.plogging.businesslogic.entity.Race;

public class RaceMapper {
    
    public static RaceDto toRaceDto(Race race) 
    {
        return RaceDto.builder()
                    .Id(race.getId())
                    .Date(race.getRaceDate())
                    .Name(race.getName())
                    .build();
    }

    public static RaceDetailDto toRaceDetailDto(Race race) 
    {
        return RaceDetailDto.builder()
                    .Id(race.getId())
                    .Date(race.getRaceDate())
                    .Name(race.getName())
                    .Runners(
                        race
                            .getRunners()
                            .stream()
                            .map(runner -> EmployeesPickedUpKilosDtoMapper.fromEmployeeRace(runner))
                            .collect(Collectors.toSet())
                    )
                    .build();
    }
}
