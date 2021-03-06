package com.epam.courses.service;

import com.epam.courses.model.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:test-db.xml", "classpath:test-service.xml"})
class DepartmentServiceImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    @Autowired
    private DepartmentService departmentService;

    @Test
    void findAll() {
        Stream<Department> departments = departmentService.findAll();
        assertNotNull(departments);
    }

    @Test
    void add() {

        long count = departmentService.findAll().count();
        LOGGER.debug("Count before: {}", count);

        Department department = create();
//        Assertions.assertThrows(DuplicateKeyException.class, () -> {
//            departmentService.add(department, department);
//        });


        long newCount = departmentService.findAll().count();
        LOGGER.debug("Count after: {}", newCount);
        assert count == newCount;
    }

    private Department create() {
        Department department = new Department();
        department.setDepartmentName("name");
        department.setDepartmentDescription("desc");
        return department;
    }

}