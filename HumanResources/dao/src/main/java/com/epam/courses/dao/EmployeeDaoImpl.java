package com.epam.courses.dao;

import com.epam.courses.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class EmployeeDaoImpl implements EmployeeDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDaoImpl.class);

    private static final String EMPLOYEE_ID = "employeeId";
    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME = "firstName";
    private static final String SALARY = "salary";
    private static final String DEPARTMENT_ID = "departmentId";

    @Value("${employee.SELECT_ALL}")
    private String SELECT_ALL;
    @Value("${employee.SELECT_BY_ID}")
    private String SELECT_BY_ID;
    @Value("${employee.INSERT}")
    private String INSERT;
    @Value("${employee.UPDATE}")
    private String UPDATE;
    @Value("${employee.DELETE}")
    private String DELETE;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public EmployeeDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Stream<Employee> findAll() {
        LOGGER.debug("findAll()");

        List<Employee> departments = namedParameterJdbcTemplate.query(SELECT_ALL, new EmployeeRowMapper());
        return departments.stream();
    }

    @Override
    public Optional<Employee> findById(Integer employeeId) {
        LOGGER.debug("findById()");

        SqlParameterSource mapSqlParameterSource = new MapSqlParameterSource(EMPLOYEE_ID, employeeId);
        Employee employee = namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID, mapSqlParameterSource,
                BeanPropertyRowMapper.newInstance(Employee.class));
        return Optional.ofNullable(employee);
    }

    @Override
    public Optional<Employee> add(Employee employee) {
        LOGGER.debug("add({})", employee);

        return Optional.of(employee)
                .map(this::insertEmployee).get();
    }

    private Optional<Employee> insertEmployee(Employee employee) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(FIRST_NAME, employee.getFirstName());
        mapSqlParameterSource.addValue(LAST_NAME, employee.getLastName());
        mapSqlParameterSource.addValue(SALARY, employee.getSalary());
        mapSqlParameterSource.addValue(DEPARTMENT_ID, employee.getDepartmentId());

        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        int result = namedParameterJdbcTemplate.update(INSERT, mapSqlParameterSource, generatedKeyHolder);
        LOGGER.debug("add( result update = {}, keyholder = {})", result, generatedKeyHolder.getKey().intValue());

        employee.setEmployeeId(generatedKeyHolder.getKey().intValue());
        return Optional.of(employee);
    }

    @Override
    public void update(Employee employee) {

        Optional.of(namedParameterJdbcTemplate.update(UPDATE, new BeanPropertySqlParameterSource(employee)))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to update employee in DB"));
    }

    @Override
    public void delete(int employeeId) {
        LOGGER.debug("delete()");

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue(EMPLOYEE_ID, employeeId);
        Optional.of(namedParameterJdbcTemplate.update(DELETE, mapSqlParameterSource))
                .filter(this::successfullyUpdated)
                .orElseThrow(() -> new RuntimeException("Failed to delete employee from DB"));
    }

    private boolean successfullyUpdated(int numRowsUpdated) {
        return numRowsUpdated > 0;
    }

    private class EmployeeRowMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
            Employee employee = new Employee();
            employee.setEmployeeId(resultSet.getInt(EMPLOYEE_ID));
            employee.setFirstName(resultSet.getString(FIRST_NAME));
            employee.setLastName(resultSet.getString(LAST_NAME));
            employee.setSalary(resultSet.getBigDecimal(SALARY));
            employee.setDepartmentId(resultSet.getInt(DEPARTMENT_ID));

            return employee;
        }
    }
}
