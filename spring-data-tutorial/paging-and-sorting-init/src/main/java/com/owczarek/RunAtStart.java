package com.owczarek;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class RunAtStart {
    private Logger logger = Logger.getLogger(RunAtStart.class);

    private final EmployeeRepository employeeRepository;
    private final EmployeeGenerator employeeGenerator;

    @Autowired
    public RunAtStart(EmployeeRepository employeeRepository, EmployeeGenerator employeeGenerator) {
        this.employeeRepository = employeeRepository;
        this.employeeGenerator = employeeGenerator;
    }

    @PostConstruct
    public void runAtStart() {
        generateEmployees();

        logger.info("\nSOME UNSORTED DATA\n");
        printAll(findAllUnsorted());

        logger.info("\nSOME SORTED DATA\n");
        printAll(finAllSorted());

        Page<Employee> pages = employeeRepository.findAll(new PageRequest(2, 10));
        logger.info("TOTAL ELEMENTS:" + pages.getTotalElements());
        logger.info("TOTAL PAGES:" + pages.getTotalPages());
        logger.info("CURRENT PAGE: " + pages.getNumber());
        printAll(pages.getContent());
    }

    private void generateEmployees() {
        for (int i=0; i<1000; i++) {
            employeeRepository.save(employeeGenerator.generate());
        }
    }

    private List<Employee> findAllUnsorted() {
        return employeeRepository.findAll();
    }

    private List<Employee> finAllSorted() {
        return employeeRepository.findAll(new Sort(
                new Sort.Order(Sort.Direction.DESC, "firstName"),
                new Sort.Order(Sort.Direction.ASC, "lastName")));
    }

    private void printAll(List<Employee> employees) {
        employees.forEach(logger::info);

        for (Employee employee: employees) {
            logger.debug(employee.toString());
        }
    }
}
