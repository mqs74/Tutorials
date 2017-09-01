package com.owczarek;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EmployeeBaseRepository extends JpaRepository<Employee, Long> {
}
