package it.lasagni.lorenzo.plogging.businesslogic.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.Date;

import org.junit.jupiter.api.Test;

import it.lasagni.lorenzo.plogging.businesslogic.dto.RaceDetailDto;
import it.lasagni.lorenzo.plogging.businesslogic.entity.Race;

public class RaceMapperTest {
    @Test
    void testToRaceDto() {
        Race race = new Race()
            .setRaceDate(Date.from(Instant.now()))
            .setName("Test race");

        // Act
        RaceDetailDto dto = RaceMapper.toRaceDetailDto(race);

        // Assert
        assertEquals("Test race", dto.getName());
        assertEquals(race.getRaceDate(), dto.getDate());
        assertTrue(dto.getRunners().isEmpty());
    }
}
