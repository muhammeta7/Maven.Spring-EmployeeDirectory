-- DROP TABLE EMPLOYEE;
--
-- CREATE TABLE EMPLOYEE (
--     ID NUMBER(10,0) NOT NULL AUTO_INCREMENT,
--     FIRST_NAME VARCHAR2(255) NOT NULL DEFAULT '',
--     LAST_NAME VARCHAR2(255) NOT NULL DEFAULT '',
--     TITLE VARCHAR2(255) NOT NULL DEFAULT '',
--     PHONE_NUMBER VARCHAR2(255) NOT NULL DEFAULT '',
--     EMAIL VARCHAR2(255) NOT NULL DEFAULT '',
--     HIRE_DATE VARCHAR2(255) NOT NULL DEFAULT '',
--     MANAGER_ID INT DEFAULT '',
--     DEPT_NUMBER INT NOT NULL DEFAULT '',
--     PRIMARY KEY (ID)
-- );
--
-- DROP TABLE DEPARTMENT;
--
-- CREATE TABLE DEPARTMENT (
--     ID NUMBER(10,0) NOT NULL AUTO_INCREMENT,
--     DEPT_NAME VARCHAR2(255) NOT NULL DEFAULT '',
--     MANAGER_ID VARCHAR2(255) DEFAULT '',
--     PRIMARY KEY (ID)
-- );



DROP SEQUENCE hibernate_sequence;

CREATE SEQUENCE hibernate_sequence;