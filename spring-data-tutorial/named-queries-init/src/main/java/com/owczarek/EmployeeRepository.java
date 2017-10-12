package com.owczarek;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllWithSalariesBetweenSomeValues(BigDecimal from, BigDecimal to);

    @Query("select e from employee e where e.salary = (select max(em.salary) from employee em)")
    List<Employee> findGuyWithHighestSalary();

    @Query(
            value = "select * from employee where salary = (select max(salary) from employee) limit 1",
            nativeQuery = true)
    Employee findOnlyOneGurWithHighestSalary();

    @Query(
            value = "select * from employee where salary between :from and :to",
            nativeQuery = true)
    List<Employee> findNativleyAllWithSalaryBetween(@Param("from") BigDecimal from, @Param("to") BigDecimal to);
}
