package it.lasagni.lorenzo.plogging.web;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.github.javafaker.Faker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import it.lasagni.lorenzo.plogging.businesslogic.entity.Employee;
import it.lasagni.lorenzo.plogging.businesslogic.entity.EmployeeRace;
import it.lasagni.lorenzo.plogging.businesslogic.entity.Race;
import it.lasagni.lorenzo.plogging.businesslogic.repository.EmployeeRaceRepository;
import it.lasagni.lorenzo.plogging.businesslogic.repository.EmployeeRepository;
import it.lasagni.lorenzo.plogging.businesslogic.repository.RaceRepository;

@SpringBootApplication(scanBasePackages = "it.lasagni.lorenzo.plogging")
@EnableJpaRepositories(basePackages = "it.lasagni.lorenzo.plogging")
@EntityScan(basePackages = "it.lasagni.lorenzo.plogging")
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(WebApplication.class);
	
	@Bean
	CommandLineRunner loadExampleData(RaceRepository raceRepository, EmployeeRepository employeeRepository, EmployeeRaceRepository partecipanRepository) {
		Faker faker = new Faker(new Locale("it"));

		
		// Fixed employees, for test use
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(new Employee("Guglielmo", "Cancelli", "g.cancelli@plogging.local"));
		employees.add(new Employee("Stefano", "Lavori", "s.lavori@logging.local"));
		
		// Randon employees, for fill db
		for (int i = 0; i < 15; i++) {
			Employee employee = new Employee(faker.name().firstName(), faker.name().lastName(), faker.internet().safeEmailAddress());
			
			employees.add(employee);
		}
		
		List<EmployeeRace> partecipants = new ArrayList<EmployeeRace>();
		
		// Fixed races, for test use
		List<Race> races = new ArrayList<Race>();
		races.add(new Race(Date.from(Instant.parse("2022-01-07T08:00:00.000Z")), "Tricolore run"));
		races.add(new Race(Date.from(Instant.parse("2022-01-27T15:00:00.000Z")), "Holocust Memorial day run"));
		races.add(new Race(Date.from(Instant.parse("2022-03-01T11:00:00.000Z")), "Carnival run"));
		// Random races, for fill db
		for (int i = 0; i < 5; i++) {
			Race race = new Race(faker.date().past(150, 75, TimeUnit.DAYS), faker.hitchhikersGuideToTheGalaxy().location() + " run");
			
			races.add(race);

			partecipants.addAll(employees.stream().map(e -> new EmployeeRace(e, race, (float)Math.random() * 20)).collect(Collectors.toList()));
		}

		return args -> {
			employees.stream()
			.forEach(employee -> log.info("[PRE-LOAD] Employees: " + employeeRepository.save(employee)));

			races.stream()
				.forEach(race -> log.info("[PRE-LOAD] Races: " + raceRepository.save(race)));

			partecipants.stream()
				.forEach(partecipant -> log.info("[PRE-LOAD] Partecipants: " + partecipanRepository.save(partecipant)));
			
		};
	}
}
