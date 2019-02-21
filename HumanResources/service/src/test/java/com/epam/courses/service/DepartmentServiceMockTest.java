package com.epam.courses.service;

import com.epam.courses.dao.DepartmentDao;
import com.epam.courses.model.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DepartmentServiceMockTest {

    private DepartmentDao dao;

    private DepartmentService service;

    @BeforeEach
    void setup() {
        dao = Mockito.mock(DepartmentDao.class);
        service = new DepartmentServiceImpl(dao);
    }

    @Test
    public void findAll() {

        Mockito.when(dao.findAll()).thenReturn(Stream.of(create()));

        Stream<Department> result = service.findAll();
        assertNotNull(result);
        assertEquals(1, result.count());

        Mockito.verify(dao, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(dao);
    }

    private Department create() {
        Department department = new Department();
        department.setDepartmentName("name");
        department.setDepartmentDescription("desc");
        return department;
    }
}
