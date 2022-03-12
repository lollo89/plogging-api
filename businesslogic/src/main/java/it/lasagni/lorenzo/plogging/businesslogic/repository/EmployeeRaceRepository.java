package it.lasagni.lorenzo.plogging.businesslogic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.lasagni.lorenzo.plogging.businesslogic.entity.EmployeeRace;
import it.lasagni.lorenzo.plogging.businesslogic.entity.key.EmployeeRaceId;

public interface EmployeeRaceRepository extends JpaRepository<EmployeeRace, EmployeeRaceId> {
    
}
