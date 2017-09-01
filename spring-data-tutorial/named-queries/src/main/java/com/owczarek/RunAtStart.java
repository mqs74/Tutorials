package com.owczarek;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;

@Order()
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

//        List<Employee> allWithSalariesBetweenSomeValues = employeeRepository.findAllWithSalariesBetweenSomeValues(
//                new BigDecimal("1000.0"),
//                new BigDecimal("2000.0")
//        );
//        printAll(allWithSalariesBetweenSomeValues);

//        List<Employee> guyWithHighestSalary = employeeRepository.findGuyWithHighestSalary();
//        printAll(guyWithHighestSalary);
//
//        Employee onlyOneGuyWIthHighestSalary = employeeRepository.findOnlyOneGuyWIthHighestSalary();
//        logger.info(onlyOneGuyWIthHighestSalary);

        List<Employee> nativelyWithSalaryBetween = employeeRepository.findNativelyWithSalaryBetween(
                new BigDecimal("1000.0"),
                new BigDecimal("2000.0")
        );
        printAll(nativelyWithSalaryBetween);
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
