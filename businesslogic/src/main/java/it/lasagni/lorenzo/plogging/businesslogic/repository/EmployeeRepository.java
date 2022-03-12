package it.lasagni.lorenzo.plogging.businesslogic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.lasagni.lorenzo.plogging.businesslogic.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByEmail(String email);
}
