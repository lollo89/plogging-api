package it.lasagni.lorenzo.plogging.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.lasagni.lorenzo.plogging.businesslogic.dto.AttendedRaceDto;
import it.lasagni.lorenzo.plogging.businesslogic.service.PloggingCommandService;

@RestController
public class CommandController {
    
    @Autowired
    private PloggingCommandService service;

    @PostMapping("/races/attended")
    @ResponseStatus(code = HttpStatus.CREATED)
    void AttendedRace(@RequestBody AttendedRaceDto attendedRace) 
    {
        service.raceAttended(attendedRace);
    }
}
