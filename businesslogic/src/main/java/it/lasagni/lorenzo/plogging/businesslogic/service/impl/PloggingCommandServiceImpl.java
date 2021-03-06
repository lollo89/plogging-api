package it.lasagni.lorenzo.plogging.businesslogic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.lasagni.lorenzo.plogging.businesslogic.dto.AttendedRaceDto;
import it.lasagni.lorenzo.plogging.businesslogic.entity.Employee;
import it.lasagni.lorenzo.plogging.businesslogic.entity.EmployeeRace;
import it.lasagni.lorenzo.plogging.businesslogic.entity.Race;
import it.lasagni.lorenzo.plogging.businesslogic.entity.key.EmployeeRaceId;
import it.lasagni.lorenzo.plogging.businesslogic.exception.EmployeeNotFoundException;
import it.lasagni.lorenzo.plogging.businesslogic.exception.RaceNotFoundException;
import it.lasagni.lorenzo.plogging.businesslogic.repository.EmployeeRaceRepository;
import it.lasagni.lorenzo.plogging.businesslogic.repository.EmployeeRepository;
import it.lasagni.lorenzo.plogging.businesslogic.repository.RaceRepository;
import it.lasagni.lorenzo.plogging.businesslogic.service.PloggingCommandService;

@Service
public class PloggingCommandServiceImpl implements PloggingCommandService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private EmployeeRaceRepository partecipationRepository;

    @Override
    public void raceAttended(AttendedRaceDto attendedRace) {
        Employee employee = employeeRepository
            .findByEmail(attendedRace.getEmployeeEmail())
            .orElseThrow(() -> new EmployeeNotFoundException(attendedRace.getEmployeeEmail()));

        EmployeeRace partecipation = partecipationRepository
            .findById(new EmployeeRaceId(employee.getId(), attendedRace.getRaceId()))
            .orElseGet(() -> CreateEmployeeRaceFromIds(attendedRace.getRaceId(), employee));
     
        partecipation.setPickedUpKilograms(attendedRace.getPickedUpKilos());

        partecipationRepository.save(partecipation);
    }
    
    protected EmployeeRace CreateEmployeeRaceFromIds(int raceId, Employee employee) {
        Race race = raceRepository
            .findById(raceId)
            .orElseThrow(() -> new RaceNotFoundException(raceId));

        return new EmployeeRace(employee, race, 0f);
    }
}
