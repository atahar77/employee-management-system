package com.Project2.Employee.Management.Repository;

import com.Project2.Employee.Management.Entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeRepository
        extends JpaRepository<Employee, Long>,
              JpaSpecificationExecutor<Employee> {

    boolean existsByEmail(String email);
}
