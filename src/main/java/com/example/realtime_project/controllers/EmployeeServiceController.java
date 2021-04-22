package com.example.realtime_project.controllers;

import com.example.realtime_project.models.Employee;
import com.example.realtime_project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeServiceController {

    private EmployeeService employeeService;

    // inject employee service
    @Autowired
    public EmployeeServiceController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public @ResponseBody List<Employee> findAll() {
        return employeeService.findAll();
    }

    @PostMapping("/employees")
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody Employee createEmployee(@RequestBody Employee theEmployee) {

        theEmployee.setId(Long.valueOf(0));
        employeeService.save(theEmployee);
        return theEmployee;
    }

    @GetMapping("/employees/{id}")
    public @ResponseBody Employee findById(@PathVariable(name = "id") Long id) {

        Employee result = employeeService.findById(id);
        if (result == null) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    @PutMapping("/employees/{id}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody Employee updateEmployeeDetails(@PathVariable(name = "id") Long id,
                                                        @RequestBody Employee employeeInfo) {
        if (id != employeeInfo.getId()) {
            throw new IllegalArgumentException();
        }
        employeeService.save(employeeInfo);
        return employeeInfo;
    }

    @DeleteMapping("/employees/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteEmployee(@PathVariable(name = "id") Long id) {

        Employee result = employeeService.findById(id);
        if (result == null) {
            throw new IllegalArgumentException();
        }
        employeeService.deleteById(id);
        return "done !";
    }
}
