package it.lasagni.lorenzo.plogging.businesslogic.entity;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter 
@Setter
@Accessors(chain = true)
@Entity
public class Race {
    
    @Id
    @GeneratedValue
    private Integer id;
    private Date raceDate;
    private String name;
    
    @OneToMany(mappedBy = "Race", cascade = CascadeType.ALL)
    private Set<EmployeeRace> Runners;


    public Race() {
        this.Runners = new HashSet<>();
    }


    public Race(Date Date, String Name) {
        this.raceDate = Date;
        this.name = Name;
        this.Runners = new HashSet<>();
    }
    
    @Override
    public String toString() {
        return "{" +
            " Id='" + getId() + "'" +
            ", Date='" + getRaceDate() + "'" +
            ", Name='" + getName() + "'" +
            ", Runners='" + getRunners() + "'" +
            "}";
    }
}
