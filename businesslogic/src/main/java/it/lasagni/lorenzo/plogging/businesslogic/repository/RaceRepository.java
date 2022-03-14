package it.lasagni.lorenzo.plogging.businesslogic.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.lasagni.lorenzo.plogging.businesslogic.entity.Race;


public interface RaceRepository extends JpaRepository<Race, Integer> { 
    List<Race> findByRaceDateBetweenOrderByRaceDateAsc(Date startDate, Date endDate);
}
