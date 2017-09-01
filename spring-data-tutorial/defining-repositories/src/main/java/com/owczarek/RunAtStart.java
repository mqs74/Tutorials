package com.owczarek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class RunAtStart {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public RunAtStart(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @PostConstruct
    public void runAtStart() {
        Employee employee = new Employee();
        employee.setFirstName("Jan");
        employee.setLastName("Nowak");
        employee.setSalary(new BigDecimal("3000.0"));
        employee.setEmploymentDate(LocalDate.of(2016, 1, 1));

        employeeRepository.save(employee);

        List<Employee> jans = employeeRepository.findByFirstName("Jan");
        Employee jan = jans.get(0);
        System.out.println("Janek: " + jan);

        List<Employee> allEmployees = employeeRepository.findAll();

    }

}
