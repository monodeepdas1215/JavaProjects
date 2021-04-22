package com.example.realtime_project.models;

import javax.persistence.*;

@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    public Employee() {
        this.id = Long.valueOf(0);
    }

    public Employee(String f, String l, String e) {
        this.id = Long.valueOf(0);
        firstName = f;
        lastName = l;
        email = e;
    }


    @Override
    public String toString() {
        return String.format("Employee{firstname=%s, lastname=%s, email=%s}", firstName, lastName, email);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
