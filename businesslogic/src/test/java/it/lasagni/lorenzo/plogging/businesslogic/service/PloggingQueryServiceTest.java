package it.lasagni.lorenzo.plogging.businesslogic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import it.lasagni.lorenzo.plogging.businesslogic.dto.EmployeePickedUpKilosDto;
import it.lasagni.lorenzo.plogging.businesslogic.dto.RaceDetailDto;
import it.lasagni.lorenzo.plogging.businesslogic.entity.Employee;
import it.lasagni.lorenzo.plogging.businesslogic.entity.EmployeeRace;
import it.lasagni.lorenzo.plogging.businesslogic.entity.Race;
import it.lasagni.lorenzo.plogging.businesslogic.exception.RaceNotFoundException;
import it.lasagni.lorenzo.plogging.businesslogic.repository.RaceRepository;
import it.lasagni.lorenzo.plogging.businesslogic.service.impl.PloggingQueryServiceImpl;

@ExtendWith(MockitoExtension.class)
class PloggingQueryServiceTest {

    @InjectMocks
    @Autowired
	PloggingQueryServiceImpl service;

    @Mock
    RaceRepository repository;

    private Date TEN_DAYS_AGO = Date.from(Instant.now().minus(10, ChronoUnit.DAYS));
    private Date FIVE_DAYS_AGO = Date.from(Instant.now().minus(5, ChronoUnit.DAYS));
    

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCompanyPickedUpKilos_ifEmptyReturn0() {
        List<Race> races = List.of(
            new Race().setId(1).setRaceDate(TEN_DAYS_AGO).setName("Test race")
        );

        Date from = Date.from(Instant.now());

        when(repository.findByRaceDateBetween(from, from)).thenReturn(races);

        // act
        double value = service.getCompanyPickedUpKilos(from, from);

        assertEquals(0d, value);
    }

    @Test
    void testGetCompanyPickedUpKilos_withOneRaceAndMultipleRunnerReturnSum() {
        List<Race> races = List.of(
            new Race().setId(1).setRaceDate(TEN_DAYS_AGO).setName("Test race").setRunners(
                Set.of(new EmployeeRace().setPickedUpKilograms(15f),new EmployeeRace().setPickedUpKilograms(30f))
            )
        );

        Date from = Date.from(Instant.now());

        when(repository.findByRaceDateBetween(from, from)).thenReturn(races);

        // act
        double value = service.getCompanyPickedUpKilos(from, from);

        assertEquals(45f, value);
    }

    @Test
    void testGetCompanyPickedUpKilos_withMultipleRaceAndMultipleRunnerReturnSum() {
        List<Race> races = List.of(
            new Race().setId(1).setRaceDate(TEN_DAYS_AGO).setName("Test race").setRunners(
                Set.of(new EmployeeRace().setPickedUpKilograms(15f),new EmployeeRace().setPickedUpKilograms(30f))
            ),
            new Race().setId(1).setRaceDate(FIVE_DAYS_AGO).setName("Test race 2").setRunners(
                Set.of(new EmployeeRace().setPickedUpKilograms(5f),new EmployeeRace().setPickedUpKilograms(7f))
            )
        );

        Date from = Date.from(Instant.now());

        when(repository.findByRaceDateBetween(from, from)).thenReturn(races);

        // act
        double value = service.getCompanyPickedUpKilos(from, from);

        assertEquals(57f, value);
    }

    @Test
    void testGetPickedUpKilosPerEmployee() {
        Employee employee1 = new Employee().setId(10).setFirstName("Test").setLastName("One").setEmail("t.one@plogging.local");
        Employee employee2 = new Employee().setId(12).setFirstName("Test").setLastName("Two").setEmail("t.two@plogging.local");

        List<Race> races = List.of(
            new Race().setId(1).setRaceDate(TEN_DAYS_AGO).setName("Test race"),
            new Race().setId(2).setRaceDate(FIVE_DAYS_AGO).setName("Test race 2")
        );

        races.stream().forEach(race -> race.setRunners(Set.of(
            new EmployeeRace(employee1, race, 5f * race.getId() * employee1.getId()),
            new EmployeeRace(employee2, race, 3f * race.getId() * employee2.getId())
        )));

        Date from = Date.from(Instant.now());
        when(repository.findByRaceDateBetween(from, from)).thenReturn(races);

        // Act
        List<EmployeePickedUpKilosDto> kilosPerEmployees = service.getPickedUpKilosPerEmployee(from, from);

        // Assert
        assertEquals(2, kilosPerEmployees.size());
        assertTrue(kilosPerEmployees.stream().anyMatch(ek -> ek.getEmployeeName().equals("Test One") && ek.getPickedUpKilos() == 150f && ek.getEmployeeId() == 10));
        assertTrue(kilosPerEmployees.stream().anyMatch(ek -> ek.getEmployeeName().equals("Test Two") && ek.getPickedUpKilos() == 108f && ek.getEmployeeId() == 12));
    }

    @Test
    void testGetRaceDetail_returnRaceDto() {
        Employee employee1 = new Employee().setId(10).setFirstName("Test").setLastName("One").setEmail("t.one@plogging.local");
        Employee employee2 = new Employee().setId(12).setFirstName("Test").setLastName("Two").setEmail("t.two@plogging.local");

        Race race = new Race().setId(1).setRaceDate(TEN_DAYS_AGO).setName("Test race");
        race.setRunners(Set.of(
            new EmployeeRace(employee1, race, 5f * race.getId() * employee1.getId()),
            new EmployeeRace(employee2, race, 3f * race.getId() * employee2.getId())
        ));

        when(repository.findById(1)).thenReturn(Optional.of(race));

        // Act
        RaceDetailDto raceDetail = service.getRaceDetail(1);

        assertEquals(1, raceDetail.getId());
        assertEquals("Test race", raceDetail.getName());
        assertEquals(TEN_DAYS_AGO, raceDetail.getDate());
        assertEquals(86f, raceDetail.getRunners().stream().mapToDouble(k -> k.getPickedUpKilos()).sum());
    }

    @Test
    void testGetRaceDetail_wrongIdThrowExeption() {
        // Act & assert
        assertThrows(RaceNotFoundException.class, () -> service.getRaceDetail(1));
    }

    @Test
    void testGetRaces_ObtainListOfRaceDto() {
        List<Race> races = List.of(
            new Race().setId(1).setRaceDate(TEN_DAYS_AGO).setName("Test race"),
            new Race().setId(2).setRaceDate(FIVE_DAYS_AGO).setName("Test race 2")
        );

        when(repository.findAll()).thenReturn(races);


        //Act
        var redRaces = service.getRaces();
        
        
        assertEquals(2, redRaces.size());
        assertTrue(redRaces.stream().anyMatch(race -> race.getName() == "Test race" && race.getId() == 1));
        assertTrue(redRaces.stream().anyMatch(race -> race.getName() == "Test race 2" && race.getId() == 2));
    }
}
