package it.lasagni.lorenzo.plogging.businesslogic.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public class Employee {
    
    @Id 
    @GeneratedValue
    private Integer Id;

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "Employee", cascade = CascadeType.ALL)
    private Set<EmployeeRace> AttendedEvents;
    

    public Employee() {
        this.AttendedEvents = new HashSet<EmployeeRace>();
    }


    public Employee(String FirstName, String LastName, String Email) {
        this.firstName = FirstName;
        this.lastName = LastName;
        this.email = Email;
        this.AttendedEvents = new HashSet<EmployeeRace>();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(Id, employee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id);
    }
    
    @Override
    public String toString() {
        return "{" +
            " Id='" + getId() + "'" +
            ", FirstName='" + getFirstName() + "'" +
            ", LastName='" + getLastName() + "'" +
            ", Email='" + getEmail() + "'" +
            ", AttendedEvents='" + getAttendedEvents() + "'" +
            "}";
    }
    
}
