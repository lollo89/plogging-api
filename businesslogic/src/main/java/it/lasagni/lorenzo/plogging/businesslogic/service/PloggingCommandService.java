package it.lasagni.lorenzo.plogging.businesslogic.service;

import it.lasagni.lorenzo.plogging.businesslogic.dto.AttendedRaceDto;

public interface PloggingCommandService {
    void raceAttended(AttendedRaceDto attendedRace);
}
