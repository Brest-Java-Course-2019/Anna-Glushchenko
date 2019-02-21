package com.epam.courses.dao;

import com.epam.courses.model.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:test-db.xml", "classpath:test-dao.xml"})
@Transactional
@Rollback
class DepartmentDaoImplTest {

    private static final String DEVELOPMENT_DEPARTMENT = "Development Department";
    private static final String DEV = "DEV";
    private static final String NEW_DEPARTMENT_NAME = "New Department";
    private static final String NEW_DEPARTMENT_DESCRIPTION = "New Department Description";

    private static final int FIRST_DEPARTMENT_ID = 1;
    private static final int FULL_DEPARTMENT_LIST = 4;

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
        assertEquals(4, departments.count());
    }


    @Test
    void findById() {

        Department department = departmentDao.findById(1).get();
        assertNotNull(department);
        assertEquals(department.getDepartmentId().intValue(), 1);
        assertEquals(department.getDepartmentName(), "DEV");
    }

    @Test
    void create() {
        Stream<Department> departmentsBeforeInsert = departmentDao.findAll();

        Department department = new Department();
        department.setDepartmentName(NEW_DEPARTMENT_NAME);
        department.setDepartmentDescription(NEW_DEPARTMENT_DESCRIPTION);
        Department newDepartment = departmentDao.add(department).get();
        assertNotNull(newDepartment.getDepartmentId());

        Stream<Department> departmentsAfterInsert = departmentDao.findAll();
        assertEquals(1, departmentsAfterInsert.count() - departmentsBeforeInsert.count());
    }

    @Test
    void createDuplicateDepartment() {
        Department department2 = new Department();
        department2.setDepartmentName(NEW_DEPARTMENT_NAME);
        department2.setDepartmentDescription(NEW_DEPARTMENT_DESCRIPTION);
        Department newDepartment = departmentDao.add(department2).get();
        assertNotNull(newDepartment.getDepartmentId());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            departmentDao.add(department2);
        });
    }

    @Test
    void update() {
        Department department = new Department();
        department.setDepartmentName(NEW_DEPARTMENT_NAME);
        department.setDepartmentDescription(NEW_DEPARTMENT_NAME);
        Department newDepartment = departmentDao.add(department).get();
        assertNotNull(newDepartment.getDepartmentId());

        department.setDepartmentName(NEW_DEPARTMENT_NAME + "_2");
        department.setDepartmentDescription(NEW_DEPARTMENT_NAME + "_2");
        departmentDao.update(department);

        Department updatedDepartment = departmentDao.findById(department.getDepartmentId()).get();

        assertEquals(NEW_DEPARTMENT_NAME + "_2", updatedDepartment.getDepartmentName());
        assertEquals(NEW_DEPARTMENT_NAME + "_2", updatedDepartment.getDepartmentDescription());
    }


    @Test
    void delete() {
        Stream<Department> departments = departmentDao.findAll();
        Department department = departments.findFirst().get();
        departmentDao.delete(department.getDepartmentId());

        Assertions.assertThrows(DataAccessException.class, () -> {
            departmentDao.findById(department.getDepartmentId());
        });
    }

}