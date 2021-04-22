package com.example.realtime_project.controllers;

import com.example.realtime_project.EmployeeRepository;
import com.example.realtime_project.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class EmployeeRepositoryController {

    private EmployeeRepository employeeRepo;

    @Autowired
    public EmployeeRepositoryController(EmployeeRepository repo) {
        employeeRepo = repo;
    }

    @GetMapping("/employees")
    public @ResponseBody
    List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @PostMapping("/employees")
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody Employee createEmployee(@RequestBody Employee theEmployee) {

        theEmployee.setId(Long.valueOf(0));
        employeeRepo.save(theEmployee);
        return theEmployee;
    }

    @GetMapping("/employees/{id}")
    public @ResponseBody Employee findById(@PathVariable(name = "id") Long id) {

        Employee result = employeeRepo.findById(id).orElse(null);
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
        employeeRepo.save(employeeInfo);
        return employeeInfo;
    }

    @DeleteMapping("/employees/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteEmployee(@PathVariable(name = "id") Long id) {

        Employee result = employeeRepo.findById(id).orElse(null);
        if (result == null) {
            throw new IllegalArgumentException();
        }
        employeeRepo.deleteById(id);
        return "done !";
    }
}
