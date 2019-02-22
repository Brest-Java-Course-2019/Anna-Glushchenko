package com.epam.courses.dao;

import com.epam.courses.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:test-db.xml", "classpath:test-dao.xml"})
@Transactional
@Rollback
class EmployeeDaoImplTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    void findAll() {

        Stream<Employee> employees = employeeDao.findAll();
        assertNotNull(employees);
        assertEquals(10, employees.count());
    }

    @Test
    void findById() {

        Employee employee = employeeDao.findById(1).get();
        assertNotNull(employee);
        assertEquals("Ivan", employee.getFirstName());
        assertEquals("Ivanov", employee.getLastName());
    }

    @Test
    void add() {

        Stream<Employee> employeesBefore = employeeDao.findAll();

        Employee employee = new Employee();
        employee.setFirstName("Name");
        employee.setLastName("LastName");
        employee.setSalary(new BigDecimal(500));
        Employee newEmployee = employeeDao.add(employee).get();
        assertNotNull(newEmployee.getEmployeeId());

        Stream<Employee> employeesAfter = employeeDao.findAll();
        assertTrue(employeesAfter.count() > employeesBefore.count());
    }

    @Test
    void update() {

        Employee employee = employeeDao.findById(1).get();
        employee.setFirstName("Petr");
        employee.setLastName("Petrov");

        employeeDao.update(employee);
        assertEquals("Petr", employee.getFirstName());
        assertEquals("Petrov", employee.getLastName());
    }

    @Test
    void delete() {

        Stream<Employee> employees = employeeDao.findAll();
        Employee employee = employees.findFirst().get();
        employeeDao.delete(employee.getDepartmentId());

        Assertions.assertThrows(DataAccessException.class, () -> {
            employeeDao.findById(employee.getDepartmentId());
        });
    }
}