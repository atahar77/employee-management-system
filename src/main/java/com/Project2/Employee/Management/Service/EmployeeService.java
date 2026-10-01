package com.Project2.Employee.Management.Service;

import com.Project2.Employee.Management.Entity.Employee;
import com.Project2.Employee.Management.Exception.DuplicateEmailException;
import com.Project2.Employee.Management.Exception.EmployeeNotFoundException;
import com.Project2.Employee.Management.Repository.EmployeeRepository;
import com.Project2.Employee.Management.dto.EmployeeRequest;
import com.Project2.Employee.Management.dto.EmployeeResponse;
import com.Project2.Employee.Management.mapper.EmployeeMapper;
import com.Project2.Employee.Management.specification.EmployeeSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;


    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper=employeeMapper;
    }

    //CREATE
    public EmployeeResponse createEmployee(EmployeeRequest request){

        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateEmailException(
                    "Employee with this email already exists"
            );
        }
        Employee employee = employeeMapper.toEntity(request);

        Employee savedEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponse(savedEmployee);
    }

    //GET ALL
    public Page<EmployeeResponse> getAllEmployees(
            int page,
            int size,
            String sortBy,
            String direction)
    {
        Sort sort;
        if(direction.equalsIgnoreCase("desc")){
            sort = Sort.by(sortBy).descending();
        } else{
            sort = Sort.by(sortBy).ascending();
        }
        Pageable pageable = PageRequest.of(
                page,
                size,
                sort
        );
        Page<Employee> employees = employeeRepository.findAll(pageable);
        return employees.map(employeeMapper::toResponse);
    }

    public Page<EmployeeResponse> searchEmployee(
            String name,
            String department,
            Double minSalary,
            Double maxSalary,
            Pageable pageable) {

        Specification<Employee>specification =
                Specification.unrestricted();

        if (name != null && !name.isBlank()) {

            specification = specification.and(
                    EmployeeSpecification.hasName(name)
            );
        }

        if (department != null && !department.isBlank()) {
            specification = specification.and(
                    EmployeeSpecification.hasDepartment(department)
            );
        }

        if (minSalary != null) {
            specification = specification.and(
                    EmployeeSpecification.salaryGreaterThanOrEqual(minSalary)
            );
        }

        if (maxSalary != null) {
            specification = specification.and(
                    EmployeeSpecification.salaryLessThanOrEqual(maxSalary)
            );
        }
        Page<Employee> employees = employeeRepository.findAll(
                specification,
                pageable
        );
        return employees.map(employeeMapper::toResponse);
    }

    //GET BY ID
    public EmployeeResponse getEmployeeByID(Long id){

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with Id: " + id)
                );

        return employeeMapper.toResponse(employee);
    }

    //UPDATE
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request){
        Employee existingEmployee = employeeRepository.findById(id)
                        .orElseThrow(()->new EmployeeNotFoundException("Employee Not Found by id :" + id));

        existingEmployee.setFirstName(request.getFirstName());
        existingEmployee.setLastName(request.getLastName());
        existingEmployee.setEmail(request.getEmail());
        existingEmployee.setPhone(request.getPhone());
        existingEmployee.setDepartment(request.getDepartment());
        existingEmployee.setDesignation(request.getDesignation());
        existingEmployee.setSalary(request.getSalary());
        existingEmployee.setJoinDate(request.getJoinDate());

        Employee updateEmployee = employeeRepository.save(existingEmployee);

        return employeeMapper.toResponse(updateEmployee);
    }

    //DELETE
    public void deleteEmployee(Long id){
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(()->
                        new EmployeeNotFoundException("Employee not found with id :" + id)
                );

        employeeRepository.delete(existingEmployee);
    }

}
