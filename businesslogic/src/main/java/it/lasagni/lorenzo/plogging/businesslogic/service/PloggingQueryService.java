package it.lasagni.lorenzo.plogging.businesslogic.service;

import java.util.Date;
import java.util.List;

import it.lasagni.lorenzo.plogging.businesslogic.dto.EmployeePickedUpKilosDto;
import it.lasagni.lorenzo.plogging.businesslogic.dto.RaceDetailDto;
import it.lasagni.lorenzo.plogging.businesslogic.dto.RaceDto;

public interface PloggingQueryService {

    List<RaceDto> getRaces();

    RaceDetailDto getRaceDetail(int id);

    List<EmployeePickedUpKilosDto> getPickedUpKilosPerEmployee(Date from, Date to);

    Double getCompanyPickedUpKilos(Date from, Date to);
}
