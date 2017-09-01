package com.owczarek;

import org.springframework.data.repository.Repository;

import java.util.List;

interface EmployeeRepository extends Repository<Employee, Long> {
    Employee save(Employee employee);
    List<Employee> findAll();
}
