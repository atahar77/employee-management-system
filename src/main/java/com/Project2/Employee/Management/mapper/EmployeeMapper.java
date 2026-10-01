package com.Project2.Employee.Management.mapper;

import com.Project2.Employee.Management.Entity.Employee;
import com.Project2.Employee.Management.dto.EmployeeRequest;
import com.Project2.Employee.Management.dto.EmployeeResponse;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee toEntity(EmployeeRequest request) {
        Employee employee = new Employee();

        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setDepartment(request.getDepartment());
        employee.setDesignation(request.getDesignation());
        employee.setSalary(request.getSalary());
        employee.setJoinDate(request.getJoinDate());

        return employee;
    }

    public EmployeeResponse toResponse(Employee employee){
        EmployeeResponse response = new EmployeeResponse();

        response.setId(employee.getId());
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
        response.setEmail(employee.getEmail());
        response.setPhone(employee.getPhone());
        response.setDepartment(employee.getDepartment());
        response.setDesignation(employee.getDesignation());
        response.setSalary(employee.getSalary());
        response.setJoinDate(employee.getJoinDate());

        return response;
    }

}
