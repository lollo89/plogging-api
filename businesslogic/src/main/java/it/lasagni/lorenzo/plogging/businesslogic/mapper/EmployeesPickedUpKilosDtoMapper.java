package it.lasagni.lorenzo.plogging.businesslogic.mapper;

import java.util.Map.Entry;

import it.lasagni.lorenzo.plogging.businesslogic.dto.EmployeePickedUpKilosDto;
import it.lasagni.lorenzo.plogging.businesslogic.entity.Employee;
import it.lasagni.lorenzo.plogging.businesslogic.entity.EmployeeRace;

public class EmployeesPickedUpKilosDtoMapper {

    public static EmployeePickedUpKilosDto fromEmployeeRace(EmployeeRace employeeRace) 
    {
        return new EmployeePickedUpKilosDto()
                        .setEmployeeName(EmployeeNameFromEmployee(employeeRace.getEmployee()))
                        .setPickedUpKilos(employeeRace.getPickedUpKilograms());

    }


    public static EmployeePickedUpKilosDto fromEntryEmployeeKilos(Entry<Employee, Double> entry) 
    {
        return new EmployeePickedUpKilosDto()
                        .setEmployeeId(entry.getKey().getId())
                        .setEmployeeName(EmployeeNameFromEmployee(entry.getKey()))
                        .setPickedUpKilos(entry.getValue().floatValue());

    }


    private static String EmployeeNameFromEmployee(Employee employee) 
    {
        return employee.getFirstName() + " " + employee.getLastName();
    }
}
