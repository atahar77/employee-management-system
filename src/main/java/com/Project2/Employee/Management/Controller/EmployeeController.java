package com.Project2.Employee.Management.Controller;

import com.Project2.Employee.Management.Service.EmployeeService;

import com.Project2.Employee.Management.dto.EmployeeRequest;
import com.Project2.Employee.Management.dto.EmployeeResponse;
import com.Project2.Employee.Management.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeResponse>> createEmployee(
            @Valid @RequestBody EmployeeRequest request){
        EmployeeResponse response = employeeService.createEmployee(request);

        ApiResponse<EmployeeResponse> apiResponse =
                new ApiResponse<>(
                        true,
                        "Employee Created Successfully",
                        response
                );

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<EmployeeResponse>>> getAllEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        Page<EmployeeResponse> employees = employeeService.getAllEmployees(
                page,
                size,
                sortBy,
                direction);

        ApiResponse<Page<EmployeeResponse>> apiResponse =
                new ApiResponse<>(
                        true,
                        "Employees Retrieved Successfully",
                        employees
                );

        return new ResponseEntity<>(
                apiResponse, HttpStatus.OK
        );
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<Page<EmployeeResponse>>> searchEmployee(

            @RequestParam(required = false)
            String name,

            @RequestParam(required = false)
            String department,

            @RequestParam(required = false)
            Double minSalary,

            @RequestParam(required = false)
            Double maxSalary,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction) {

        Sort sort;

        if (direction.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        } else {
            sort = Sort.by(sortBy).ascending();
        }

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<EmployeeResponse> employees =
                employeeService.searchEmployee(
                        name,
                        department,
                        minSalary,
                        maxSalary,
                        pageable);

        ApiResponse<Page<EmployeeResponse>> apiResponse =
                new ApiResponse<>(
                        true,
                        "Employees searched successfully",
                        employees
                );

        return new ResponseEntity<>(
                apiResponse, HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> getEmployeeByID(@PathVariable("id") Long id){
        EmployeeResponse response = employeeService.getEmployeeByID(id);

        ApiResponse<EmployeeResponse> apiResponse =
                new ApiResponse<>(
                        true,
                        "Employee Retrieved Successfully",
                        response
                );

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeResponse>> updateEmployee(
            @Valid @RequestBody EmployeeRequest request, @PathVariable("id") Long id){
        EmployeeResponse updatedEmployee = employeeService.updateEmployee(id,request);

        ApiResponse<EmployeeResponse> apiResponse =
                new ApiResponse<>(
                        true,
                        "Employee Updated Successfully",
                        updatedEmployee
                );

        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(@PathVariable("id") Long id){
         employeeService.deleteEmployee(id);

         ApiResponse<Void> apiResponse =
                 new ApiResponse<>(
                         true,
                         "Employee Deleted Successfully",
                         null
                 );


        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
