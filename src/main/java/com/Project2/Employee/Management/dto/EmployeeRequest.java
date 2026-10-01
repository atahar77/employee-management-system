package com.Project2.Employee.Management.dto;



import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {

    @NotBlank(message = "First Name is required")
    @Size(min = 2, max = 50, message = "First Name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last Name is required")
    @Size(min = 2, max = 50, message = "Last Name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email")
    private String email;

    @NotBlank(message = "Department is required")
    //@Size(min = 2, max = 50, message = "Department must be between 2 and 50 characters")
    private String department;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Phone number must exists 10 digits"
    )
    private String phone;

    @NotBlank(message = "Designation is required")
    //@Size(min = 2, max = 50, message = "First Name must be between 2 and 50 characters")
    private String designation;

    @NotNull(message = "Salary is required")
    @DecimalMin(
            value = "0.01",
            message = "salary must be greater tha 0"
    )
    private Double salary;

    @NotNull(message = "Joindate is required")
    //@Size(min = 2, max = 50, message = "First Name must be between 2 and 50 characters")
    private LocalDate joinDate;
}