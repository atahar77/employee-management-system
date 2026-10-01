package com.Project2.Employee.Management.Service;

import com.Project2.Employee.Management.Entity.Employee;
import com.Project2.Employee.Management.Exception.DuplicateEmailException;
import com.Project2.Employee.Management.Exception.EmployeeNotFoundException;
import com.Project2.Employee.Management.Repository.EmployeeRepository;
import com.Project2.Employee.Management.dto.EmployeeRequest;
import com.Project2.Employee.Management.dto.EmployeeResponse;
import com.Project2.Employee.Management.mapper.EmployeeMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void createEmployee_shouldCreateEmployeeSuccessfully() {

        EmployeeRequest request = new EmployeeRequest();

        Employee employee = new Employee();

        when(employeeRepository.existsByEmail(request.getEmail())).thenReturn(true);

        assertThrows(
                DuplicateEmailException.class,
                ()->employeeService.createEmployee(request)
        );

        verify(employeeRepository).existsByEmail(request.getEmail());

        verify(employeeRepository, never()).save(any(Employee.class));

    }

    @Test
    void getEmployeeId_shouldThrowException_WhenEmployeeNotFound(){

        Long employeeId = 999L;

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        assertThrows(
                EmployeeNotFoundException.class,
                ()-> employeeService.getEmployeeByID(employeeId)
        );
        verify(employeeRepository).findById(employeeId);
    }

    @Test
    void getEmployeeId_shouldReturnEmployee_WhenEmployeeExists(){

        Long employeeId = 1L;

        Employee employee = new Employee();
        EmployeeResponse expectedResponse = new EmployeeResponse();

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        when(employeeMapper.toResponse(employee)).thenReturn(expectedResponse);

        EmployeeResponse actualResponse = employeeService.getEmployeeByID(employeeId);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);

        verify(employeeRepository).findById(employeeId);
        verify(employeeMapper).toResponse(employee);
    }

    @Test
    void getAllEmployees_shouldReturnAllEmployees() {

        Employee employee1 = new Employee();
        Employee employee2 = new Employee();

        EmployeeResponse response1 = new EmployeeResponse();
        EmployeeResponse response2 = new EmployeeResponse();

        Page<Employee> employeePage =
                new PageImpl<>(List.of(employee1, employee2));

        when(employeeRepository.findAll(any(Pageable.class)))
                .thenReturn(employeePage);

        when(employeeMapper.toResponse(employee1))
                .thenReturn(response1);

        when(employeeMapper.toResponse(employee2))
                .thenReturn(response2);

        Page<EmployeeResponse> actualResponse =
                employeeService.getAllEmployees(0, 10, "id", "asc");

        assertNotNull(actualResponse);

        assertEquals(2, actualResponse.getContent().size());

        assertEquals(response1, actualResponse.getContent().get(0));
        assertEquals(response2, actualResponse.getContent().get(1));

        verify(employeeRepository).findAll(any(Pageable.class));

        verify(employeeMapper).toResponse(employee1);
        verify(employeeMapper).toResponse(employee2);
    }
}