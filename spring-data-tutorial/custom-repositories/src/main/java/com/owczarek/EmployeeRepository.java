package com.owczarek;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Stream;

interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByFirstNameIgnoreCase(String firstName);

    List<Employee> findByLastNameOrderByFirstNameDesc(String lastName);

    List<Employee> findBySalaryBetween(BigDecimal salary1, BigDecimal salary2);

    Employee findFirstByFirstName(String firstName);

    List<Employee> findTop3ByFirstName(String firstName);
    List<Employee> findFirst3ByFirstName(String firstName);

    int countByFirstNameAndLastNameIgnoreCase(String firstName, String lastName);

    Page<Employee> findByFirstName(String firstName, Pageable pageable);

    Stream<Employee> findTop10ByFirstName(String firstName);

    @Async
    Future<List<Employee>> findDistinctByFirstName(String firstName);

    @Async
    CompletableFuture<Employee> findFirstByFirstNameIgnoreCase(String firstName);

    @Async
    ListenableFuture<Employee> findFirstByLastName(String lastName);
}
