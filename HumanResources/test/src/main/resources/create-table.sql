DROP TABLE IF EXISTS department;
CREATE TABLE department (
  departmentId             INT             NOT NULL AUTO_INCREMENT,
  departmentName           VARCHAR(255)    NOT NULL,
  departmentDescription    VARCHAR(255)    NULL,
  PRIMARY KEY (departmentId)
);
DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
  employeeId               INT             NOT NULL AUTO_INCREMENT,
  firstName                VARCHAR(255)    NOT NULL,
  lastName                 VARCHAR(255)    NOT NULL,
  salary                   INT             NOT NULL,
  departmentId             INT,
  PRIMARY KEY (employeeId),
  FOREIGN KEY (departmentId) REFERENCES department(departmentId)
);