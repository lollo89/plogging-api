package it.lasagni.lorenzo.plogging.businesslogic.entity.key;



import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmployeeRaceId implements Serializable {

    @Column(name = "employee_id")
    private Integer EmployeeId;
 
    @Column(name = "race_id")
    private Integer RaceId;


    public EmployeeRaceId() {
    }


    public EmployeeRaceId(Integer EmployeeId, Integer RaceId) {
        this.EmployeeId = EmployeeId;
        this.RaceId = RaceId;
    }

    public Integer getEmployeeId() {
        return this.EmployeeId;
    }

    public void setEmployeeId(Integer EmployeeId) {
        this.EmployeeId = EmployeeId;
    }

    public Integer getRaceId() {
        return this.RaceId;
    }

    public void setRaceId(Integer RaceId) {
        this.RaceId = RaceId;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EmployeeRaceId)) {
            return false;
        }
        EmployeeRaceId employeeRaceId = (EmployeeRaceId) o;
        return Objects.equals(EmployeeId, employeeRaceId.EmployeeId) && Objects.equals(RaceId, employeeRaceId.RaceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(EmployeeId, RaceId);
    }



    @Override
    public String toString() {
        return "{" +
            " EmployeeId='" + getEmployeeId() + "'" +
            ", RaceId='" + getRaceId() + "'" +
            "}";
    }


}
