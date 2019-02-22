package com.epam.courses.dao;

import com.epam.courses.model.Department;

import java.util.Optional;
import java.util.stream.Stream;

public interface DepartmentDao {

    Stream<Department> findAll();

    Optional<Department> findById(Integer departmentId);

    Optional<Department> add(Department department);

    void update(Department department);

    void delete(int departmentId);

}
