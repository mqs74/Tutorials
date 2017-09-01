package com.owczarek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

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
        employee.setFirstName("Marcin");
        employee.setLastName("Karolak");
        employee.setSalary(new BigDecimal("3000.0"));

        employeeRepository.save(employee);

        Iterable<Employee> marcins = employeeRepository.findByFirstName("Marcin");
        Employee jan = marcins.iterator().next();
        System.out.println("Marcin: " + jan);
    }

}
