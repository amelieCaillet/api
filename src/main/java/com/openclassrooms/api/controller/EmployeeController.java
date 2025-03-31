package com.openclassrooms.api.controller;

import com.openclassrooms.api.model.Employee;
import com.openclassrooms.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/test-employees")
    public Iterable<Employee> testEmployees() {
        return employeeService.getEmployees();
    }

    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable ("id") final Long id) {
        Optional<Employee> employee = employeeService.getEmployee(id);
        if (employee.isPresent()) {
            return employee.get();
        }else{
            return null;
        }
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable ("id") final Long id, @RequestBody Employee employee) {
        Optional<Employee> e = employeeService.getEmployee(id);
        if (e.isPresent()) {
            Employee currentEmployee = e.get();

            String firstName = employee.getFirstName();
            if (firstName != null && !firstName.isEmpty()) {
                currentEmployee.setFirstName(firstName);
            }
            String lastName = employee.getLastName();
            if (lastName != null && !lastName.isEmpty()) {
                currentEmployee.setLastName(lastName);
            }
            String email = employee.getEmail();
            if (email != null && !email.isEmpty()) {
                currentEmployee.setEmail(email);
            }
            String password = employee.getPassword();
            if (password != null && !password.isEmpty()) {
                currentEmployee.setPassword(password);
            }
            employeeService.saveEmployee(currentEmployee);
            return currentEmployee;
        }else{
            return null;
        }
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable ("id") final Long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/employees")
    public Iterable<Employee> getEmployees() {
        return employeeService.getEmployees();
    }
}
