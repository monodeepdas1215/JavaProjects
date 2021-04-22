package com.example.realtime_project.service;

import com.example.realtime_project.models.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> findAll();

    public Employee findById(Long id);

    public void save(Employee employee);

    public void deleteById(Long id);
}
