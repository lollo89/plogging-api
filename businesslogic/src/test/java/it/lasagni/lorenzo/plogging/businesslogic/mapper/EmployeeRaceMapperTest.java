package it.lasagni.lorenzo.plogging.businesslogic.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.Date;

import org.junit.jupiter.api.Test;

import it.lasagni.lorenzo.plogging.businesslogic.dto.EmployeePickedUpKilosDto;
import it.lasagni.lorenzo.plogging.businesslogic.entity.Employee;
import it.lasagni.lorenzo.plogging.businesslogic.entity.EmployeeRace;
import it.lasagni.lorenzo.plogging.businesslogic.entity.Race;

public class EmployeeRaceMapperTest {

    @Test
    void testToEmployeeRaceDto() {
        EmployeeRace runner = CreateRunner();

        // Act
        EmployeePickedUpKilosDto dto = EmployeesPickedUpKilosDtoMapper.fromEmployeeRace(runner);

        // Assert
        assertEquals("Lorenzo Lasagni", dto.getEmployeeName());
        assertEquals(15f, dto.getPickedUpKilos());
    }


    private EmployeeRace CreateRunner() {
        Employee employee = new Employee()
                .setFirstName("Lorenzo")
                .setLastName("Lasagni");
        Race race = new Race()
        .setRaceDate(Date.from(Instant.now()))
        .setName("Test race");

        EmployeeRace runner = new EmployeeRace(employee, race, 15f);
        race.getRunners().add(runner);
        employee.getAttendedEvents().add(runner);

        return runner;
    }
}
