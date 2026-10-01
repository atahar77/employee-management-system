package com.Project2.Employee.Management.Exception;

public class DuplicateEmailException extends RuntimeException{

    public DuplicateEmailException (String message){
        super(message);
    }
}
