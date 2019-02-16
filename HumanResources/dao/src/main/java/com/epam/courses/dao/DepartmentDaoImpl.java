package com.epam.courses.dao;

import com.epam.courses.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class DepartmentDaoImpl implements DepartmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDaoImpl.class);
    private static final String DEPARTMENT_ID = "departmentId";
    private static final String DEPARTMENT_NAME = "departmentName";
    private static final String DEPARTMENT_DESCRIPTION = "departmentDescription";
    private static final String SELECT_ALL = "select departmentId, departmentName, departmentDescription from department";
    private static final String SELECT_BY_ID = "select departmentId, departmentName, departmentDescription from department where departmentId=:departmentId";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DepartmentDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Stream<Department> findAll() {
        LOGGER.debug("findAll()");

        List<Department> departments = namedParameterJdbcTemplate.query(SELECT_ALL, new DepartmentRowMapper());
        return departments.stream();
    }

    @Override
    public Optional<Department> findById(Integer departmentId) {
        LOGGER.debug("findById()");

        SqlParameterSource parameterSource = new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        Department department = namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID, parameterSource,
                BeanPropertyRowMapper.newInstance(Department.class));
        return Optional.ofNullable(department);
    }

    private class DepartmentRowMapper implements RowMapper<Department>{

        @Override
        public Department mapRow(ResultSet resultSet, int i) throws SQLException {
            Department department = new Department();
            department.setDepartmentId(resultSet.getInt(DEPARTMENT_ID));
            department.setDepartmentName(resultSet.getString(DEPARTMENT_NAME));
            department.setDepartmentName(resultSet.getString(DEPARTMENT_DESCRIPTION));

            return department;
        }
    }
}
