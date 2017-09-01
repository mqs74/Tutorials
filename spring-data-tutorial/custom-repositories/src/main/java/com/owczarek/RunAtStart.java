package com.owczarek;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
class RunAtStart {
    private final EmployeeRepository employeeRepository;
    private final EmployeeGenerator employeeGenerator;
    private final Logger logger = Logger.getLogger(RunAtStart.class);

    @Autowired
    public RunAtStart(EmployeeRepository employeeRepository, EmployeeGenerator employeeGenerator) {
        this.employeeRepository = employeeRepository;
        this.employeeGenerator = employeeGenerator;
    }

    @PostConstruct
    public void runAtStart() {
        generateManyEmployees();

//        logger.info("ALL EMPLOYEES");
//        printAll(employeeRepository.findByFirstNameIgnoreCase("jOHN"));

//        printAll(employeeRepository.findByLastNameOrderByFirstNameDesc("Smith"));

//        printAll(
//                employeeRepository.findBySalaryBetween(
//                        new BigDecimal("1000.0"),
//                        new BigDecimal("2000.0")));
//
//        logger.info("FIRST JOHN: " + employeeRepository.findFirstByFirstName("John"));
//        logger.info("FIRST ARTHUR: " + employeeRepository.findFirstByFirstName("Arthur"));

//        printAll(employeeRepository.findFirst3ByFirstName("John"));
//        printAll(employeeRepository.findTop3ByFirstName("John"));

//        logger.info(
//                String.format(
//                        "Number of John Smith's: %d",
//                        employeeRepository
//                                .countByFirstNameAndLastNameIgnoreCase(
//                                        "John",
//                                        "Smith")));

//        Page<Employee> johnPage = employeeRepository.findByFirstName(
//                "John",
//                new PageRequest(1, 3));
//        printAll(johnPage.getContent());
//        logger.info("Total number of pages: " + johnPage.getTotalPages());

//        Stream<Employee> johnStream = employeeRepository.findTop10ByFirstName("John");
//        List<String> johnsLastNames = johnStream
//                .map(Employee::getLastName)
//                .collect(Collectors.toList());
//        logger.info(johnsLastNames);

        employeeRepository
                .findFirstByFirstNameIgnoreCase("John")
                .thenAccept(john -> {
                    logger.info("John: " + john);
                });
    }

    private void generateManyEmployees() {
        for (int i = 0; i < 100; i++) {
            employeeRepository.save(
                    employeeGenerator.generate());
        }
    }

    private void printAll(List<Employee> allUnsorted) {
        allUnsorted.forEach(logger::info);
    }

}
