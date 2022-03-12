package it.lasagni.lorenzo.plogging.businesslogic.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import it.lasagni.lorenzo.plogging.businesslogic.entity.key.EmployeeRaceId;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Entity
@Getter 
@Accessors(chain = true)
public class EmployeeRace {
    @EmbeddedId
    private EmployeeRaceId Id;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @MapsId("EmployeeId")
    private Employee Employee;
 
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @MapsId("RaceId")
    private Race Race;

    @Setter private Float PickedUpKilograms;


    public EmployeeRace() {
    }


    public EmployeeRace(Employee employee, Race race, Float pickedUpKilograms) {
        this.Employee = employee;
        this.Race = race;
        this.Id = new EmployeeRaceId(employee.getId(), race.getId());
        this.PickedUpKilograms = pickedUpKilograms;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EmployeeRace)) {
            return false;
        }
        EmployeeRace employeeRace = (EmployeeRace) o;
        return Objects.equals(Id, employeeRace.Id) && Objects.equals(Employee, employeeRace.Employee) && Objects.equals(Race, employeeRace.Race) && Objects.equals(PickedUpKilograms, employeeRace.PickedUpKilograms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Employee, Race, PickedUpKilograms);
    }


    @Override
    public String toString() {
        return "{" +
            " Id='" + Id + "'" +
            ", PickedUpKilograms='" + getPickedUpKilograms() + "'" +
            "}";
    }

}
