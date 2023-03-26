show databases;

CREATE DATABASE Module0303;

show databases;

SELECT database();

use Module0303;

SELECT database();

-- 직원
CREATE TABLE EMPLOYEE (
    EMPNO INT,
    EMPNAME NVARCHAR(20) NOT NULL,
    AGE INT,
    SALARY FLOAT,
    CONSTRAINT PRIMARY KEY (EMPNO)
);

DESC EMPLOYEE;
SELECT * FROM EMPLOYEE;



-- 부서
CREATE TABLE DEPARTMENT (
    DEPTNO INT, 
    BUDGET FLOAT,
    HEADNO INT, 
    PRIMARY KEY(DEPTNO),
    FOREIGN KEY(HEADNO) REFERENCES EMPLOYEE(EMPNO)
);

DESC DEPARTMENT;
SELECT * FROM DEPARTMENT;



-- 근무
CREATE TABLE WORK (
    EMPNO INT,
    DEPTNO INT,
    HOURS INT,
    FOREIGN KEY(EMPNO) REFERENCES EMPLOYEE(EMPNO),
    FOREIGN KEY(DEPTNO) REFERENCES DEPARTMENT(DEPTNO)
);

DESC WORK;
SELECT * FROM WORK;

show tables;









