package com.owczarek;

import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Employee.class, idClass = Long.class)
interface EmployeeRepositoryWithAnnotation {
    Employee save(Employee employee);

    List<Employee> findAll();
}
