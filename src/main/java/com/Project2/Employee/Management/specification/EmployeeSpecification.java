package com.Project2.Employee.Management.specification;
import com.Project2.Employee.Management.Entity.Employee;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {
    public static Specification<Employee> hasName(String name){

        return(root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("firstName")),
                        "%" + name.toLowerCase() + "%"
                );
    }

    public static Specification<Employee> hasDepartment(String department){

        return(root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        criteriaBuilder.lower(root.get("department")),
                        department.toLowerCase()
                );
    }

    public static Specification<Employee> salaryGreaterThanOrEqual( Double minSalary){
        return(root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(
                        root.get("salary"),
                        minSalary
                );
    }

    public static Specification<Employee> salaryLessThanOrEqual(Double maxSalary){
        return(root, query, criteriaBuilder)->
                criteriaBuilder.lessThanOrEqualTo(
                        root.get("salary"),
                        maxSalary
                );
    }
}
