package com.epam.courses.dao;

import com.epam.courses.model.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:test-db.xml", "classpath:test-dao.xml"})
class DepartmentDaoImplTest {

    @Autowired
    private DepartmentDao departmentDao;

    @Test
    void findAll() {

        Stream<Department> departments = departmentDao.findAll();
        assertNotNull(departments);
    }

    @Test
    void findAllCheckCount() {

        Stream<Department> departments = departmentDao.findAll();
        assertNotNull(departments);
        assertEquals(departments.count(), 4);
    }


    @Test
    void findById() {

        Department department = departmentDao.findById(1).get();
        assertNotNull(department);
        assertEquals(department.getDepartmentId().intValue(), 1);
        assertEquals(department.getDepartmentName(), "DEV");
    }
}