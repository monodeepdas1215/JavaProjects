package com.example.realtime_project;

import com.example.realtime_project.dao.EmployeeDAO;
import com.example.realtime_project.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    private EntityManager manager;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        manager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        TypedQuery<Employee> query = manager.createQuery("from Employee", Employee.class);

        return query.getResultList();
    }

    @Override
    public Employee findById(Long id) {

        return manager.find(Employee.class, id);
    }

    @Override
    public void save(Employee employee) {

        Employee dbEmployee = manager.merge(employee);
        employee.setId(dbEmployee.getId());
    }

    @Override
    public void deleteById(Long id) {

        Query query = manager.createQuery("delete from Employee where id=:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
