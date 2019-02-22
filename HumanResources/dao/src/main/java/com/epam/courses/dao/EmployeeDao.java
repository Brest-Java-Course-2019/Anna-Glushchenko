package com.epam.courses.dao;

import com.epam.courses.model.Employee;

import java.util.Optional;
import java.util.stream.Stream;

public interface EmployeeDao {

    Stream<Employee> findAll();

    Optional<Employee> findById(Integer employeeId);

    Optional<Employee> add(Employee employee);

    void update(Employee employee);

    void delete(int employeeId);
}
