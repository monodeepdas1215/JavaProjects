package com.example.realtime_project.dao;

import com.example.realtime_project.models.Employee;

import java.util.List;

public interface EmployeeDAO {

    public List<Employee> findAll();

    public Employee findById(Long id);

    public void save(Employee employee);

    public void deleteById(Long id);
}
