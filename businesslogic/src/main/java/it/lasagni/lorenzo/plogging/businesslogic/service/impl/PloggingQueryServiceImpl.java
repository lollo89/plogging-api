package it.lasagni.lorenzo.plogging.businesslogic.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.lasagni.lorenzo.plogging.businesslogic.dto.EmployeePickedUpKilosDto;
import it.lasagni.lorenzo.plogging.businesslogic.dto.RaceDetailDto;
import it.lasagni.lorenzo.plogging.businesslogic.dto.RaceDto;
import it.lasagni.lorenzo.plogging.businesslogic.entity.Employee;
import it.lasagni.lorenzo.plogging.businesslogic.entity.EmployeeRace;
import it.lasagni.lorenzo.plogging.businesslogic.exception.RaceNotFoundException;
import it.lasagni.lorenzo.plogging.businesslogic.mapper.EmployeesPickedUpKilosDtoMapper;
import it.lasagni.lorenzo.plogging.businesslogic.mapper.RaceMapper;
import it.lasagni.lorenzo.plogging.businesslogic.repository.RaceRepository;
import it.lasagni.lorenzo.plogging.businesslogic.service.PloggingQueryService;

@Service
public class PloggingQueryServiceImpl implements PloggingQueryService {

    @Autowired
    private RaceRepository raceRepository;

    @Override
    public List<RaceDto> getRaces() {
        return this.raceRepository
                    .findAll()
                    .stream()
                    .map(race -> RaceMapper.toRaceDto(race))
                    .collect(Collectors.toList());
    }

    @Override
    public RaceDetailDto getRaceDetail(int id) 
    {
        return this.raceRepository
                        .findById(id)
                        .map(race -> RaceMapper.toRaceDetailDto(race))
                        .orElseThrow(() -> new RaceNotFoundException(id));
    }

    @Override
    public List<EmployeePickedUpKilosDto> getPickedUpKilosPerEmployee(Date from, Date to) 
    {

        Set<Entry<Employee, Double>> pippo = this.raceRepository
                                                    .findByRaceDateBetween(from, to)
                                                    .stream()
                                                    .flatMap(race -> race.getRunners().stream())
                                                    .collect(
                                                        Collectors.groupingBy(
                                                                EmployeeRace::getEmployee, 
                                                                Collectors.summingDouble(EmployeeRace::getPickedUpKilograms)
                                                            )
                                                    )
                                                    .entrySet();

        return pippo.stream().map(x -> EmployeesPickedUpKilosDtoMapper.fromEntryEmployeeKilos(x)).collect(Collectors.toList());
    }

    @Override
    public Double getCompanyPickedUpKilos(Date from, Date to) {
        return this.raceRepository.findByRaceDateBetween(from, to)
                    .stream()
                    .flatMap(race -> race.getRunners().stream())
                    .mapToDouble(x -> x.getPickedUpKilograms())
                    .sum();
    }    
}
