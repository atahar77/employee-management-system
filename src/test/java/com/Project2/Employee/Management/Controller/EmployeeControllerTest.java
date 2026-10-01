package com.Project2.Employee.Management.Controller;

import com.Project2.Employee.Management.Service.EmployeeService;
import com.Project2.Employee.Management.dto.EmployeeRequest;
import com.Project2.Employee.Management.dto.EmployeeResponse;

import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import tools.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;


@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void createEmployee_shouldReturnCreated() throws Exception {

        EmployeeRequest request = new EmployeeRequest();

        request.setFirstName("Rahul");
        request.setLastName("Sharma");
        request.setEmail("rahulcontroller@gmail.com");
        request.setPhone("9876543210");
        request.setDepartment("CSE");
        request.setDesignation("Developer");
        request.setSalary(50000.0);
        request.setJoinDate(LocalDate.of(2026, 9, 1));

        EmployeeResponse response = new EmployeeResponse();


        when(employeeService.createEmployee(any(EmployeeRequest.class)))
                .thenReturn(response);


        mockMvc.perform(
                        post("/api/employees")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated());
    }
    @Test
    void getEmployeeById_shouldReturnEmployee() throws Exception {

        EmployeeResponse response = new EmployeeResponse();

        when(employeeService.getEmployeeByID(1L)).thenReturn(response);

        mockMvc.perform(
                get("/api/employees/1")
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(employeeService).getEmployeeByID(1L);
    }

    @Test
    void updateEmployee_shouldReturnUpdatedEmployee() throws Exception {

        EmployeeRequest request = new EmployeeRequest();

        request.setFirstName("John");
        request.setLastName("Doe");
        request.setEmail("john.doe@gmail.com");
        request.setPhone("9876543210");
        request.setDepartment("IT");
        request.setDesignation("Software Engineer");
        request.setSalary(60000.0);
        request.setJoinDate(LocalDate.of(2026, 9, 17));

        EmployeeResponse response = new EmployeeResponse();

        when(employeeService.updateEmployee(
                eq(1L),
                any(EmployeeRequest.class)
        )).thenReturn(response);

        mockMvc.perform(
                        put("/api/employees/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk());

        verify(employeeService).updateEmployee(
                eq(1L),
                any(EmployeeRequest.class)
        );
    }

}