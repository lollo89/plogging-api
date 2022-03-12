package it.lasagni.lorenzo.plogging.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.lasagni.lorenzo.plogging.businesslogic.dto.EmployeePickedUpKilosDto;
import it.lasagni.lorenzo.plogging.businesslogic.dto.RaceDto;
import it.lasagni.lorenzo.plogging.businesslogic.service.PloggingQueryService;

@RestController
public class QueryController {
    
    @Autowired
    private PloggingQueryService service;

    @GetMapping("/races")
    public List<RaceDto> all() {
        return service.getRaces();
    }

    @GetMapping("/races/{raceId}")
    public RaceDto detail(@PathVariable int raceId) {
        return service.getRaceDetail(raceId);
    }

    @GetMapping("/kilos-per-employee")
    public List<EmployeePickedUpKilosDto> kilosPerEmployee(@RequestParam @DateTimeFormat(iso = ISO.DATE) Date from, @RequestParam @DateTimeFormat(iso = ISO.DATE) Date to) {
        return service.getPickedUpKilosPerEmployee(from, to);
    }
}
