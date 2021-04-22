package com.example.realtime_project;

import com.example.realtime_project.dao.EmployeeDAO;
import com.example.realtime_project.models.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

    private EntityManager entityManager;

    // constructor injection
    @Autowired
    public EmployeeDAOHibernateImpl(EntityManager manager) {
        entityManager = manager;
    }

    @Override
    public List<Employee> findAll() {

        Session currentSession = entityManager.unwrap(Session.class);

        Query<Employee> query = currentSession.createQuery("from Employee", Employee.class);
        return query.getResultList();
    }

    @Override
    public Employee findById(Long id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Employee employee = currentSession.get(Employee.class, id);
        return employee;
    }

    @Override
    public void save(Employee employee) {

        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(employee);
    }

    @Override
    public void deleteById(Long id) {

        Session currentSession = entityManager.unwrap(Session.class);

        javax.persistence.Query query = currentSession.createQuery(
                "delete from Employee where id=:employeeId");
        query.setParameter("employeeId", id);

        query.executeUpdate();
    }
}
