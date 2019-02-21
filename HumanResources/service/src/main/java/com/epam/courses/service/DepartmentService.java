package com.epam.courses.service;

import com.epam.courses.model.Department;

import java.util.stream.Stream;

public interface DepartmentService {

    Stream<Department> findAll();

    void add(Department... departments);
}
