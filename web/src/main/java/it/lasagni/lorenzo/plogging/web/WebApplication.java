package it.lasagni.lorenzo.plogging.web;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import it.lasagni.lorenzo.plogging.businesslogic.entity.Employee;
import it.lasagni.lorenzo.plogging.businesslogic.entity.Race;
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
	CommandLineRunner initData(RaceRepository raceRepository, EmployeeRepository employeeRepository) {
		List<Employee> employees = Arrays.asList(
				new Employee("Lorenzo", "Lasagni", "lorenzo@lasagni.test"),
				new Employee("Simone", "Lasagni", "simone@lasagni.test"),
				new Employee("Giacomo", "Lasagni", "giacomo@lasagni.test"),
				new Employee("Giovanni", "Lasagni", "giovanni@lasagni.test"),
				new Employee("Marco", "Lasagni", "marco@lasagni.test"),
				new Employee("Luca", "Lasagni", "luca@lasagni.test")
			)
			.stream()
			.map(employee -> employeeRepository.save(employee))
			.toList();

		List<Race> races = Arrays.asList(
				new Race(Date.from(Instant.parse("2022-01-07T08:00:00.000Z")), "Tricolore run"),
				new Race(Date.from(Instant.parse("2022-01-27T15:00:00.000Z")), "Holocust Memorial day run"),
				new Race(Date.from(Instant.parse("2022-03-01T11:00:00.000Z")), "Carnival run")
			)
			.stream()
			.map(race -> raceRepository.save(race))
			.toList();
			
		return args -> {
			employees.forEach(employee -> log.info("Preloading Employees: " + employee));
			races.forEach(race -> log.info("Preloading Races: " + race));
		};
	}
}
