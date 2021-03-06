-- the sql scripts in this file was used for JDBC examples and 
-- DAO design pattern examples

--- varchar, varchar2, nvarchar, nvarchar2, char, nchar, 
create table employee_1810 
(
    emp_id number not null,
    first_name varchar2(50),
    --last_name char(50) 
    --will utilize space for 50 characters or bytes irrespective of 
    --number of characters used, please avoid char
    last_name varchar2(50),
    constraint pk_emp_id primary key (emp_id)
);

insert into employee_1810 values (1,'Mike', 'Tyson');
insert into employee_1810 values (2,'Ralph', 'Lauren');
insert into employee_1810 values (3,'Boss', 'Baby');
commit;

select * from employee_1810 where first_name like 'M%';

drop table customer;
commit;

CREATE TABLE CUSTOMER
(
  C_ID NUMBER NOT NULL,
  C_FIRSTNAME VARCHAR2(20) NOT NULL,
  C_LASTNAME VARCHAR2(20) NOT NULL,
  C_USERNAME VARCHAR2(20) NOT NULL,
  C_PASSWORD VARCHAR2(100) NOT NULL,
  CONSTRAINT PK_CUSTOMER PRIMARY KEY (C_ID),
  CONSTRAINT UNQ_USERNAME UNIQUE (C_USERNAME)
);

--SEQUENCE USED FOR AUTOINCREMENT  
CREATE SEQUENCE CUSTOMER_SEQ
  START WITH 1
  INCREMENT BY 1;
  
--HASHING FUNCTION THAT COMBINES USERNAME, PASSWORD AND A SPECIAL WORD  
CREATE OR REPLACE FUNCTION GET_CUSTOMER_HASH(USERNAME VARCHAR2, PASSWORD VARCHAR2) RETURN VARCHAR2
IS
EXTRA VARCHAR2(10) := 'SALT';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || PASSWORD || EXTRA)));
END;
/


--TRIGGET THAT GETS NEXT SEQUENCE VALUE FOR ID AND HASHES BLANK PASSWORD
CREATE OR REPLACE TRIGGER CUSTOMER_B_INSERT
BEFORE INSERT
ON CUSTOMER
FOR EACH ROW
BEGIN
  /* INCREASE THE SEQUENCE */
  IF :NEW.C_ID IS NULL THEN
    SELECT CUSTOMER_SEQ.NEXTVAL INTO :NEW.C_ID FROM DUAL;
  END IF;
  
  /* SAVE HASH INSTEAD OF PASSWORD */
  SELECT GET_CUSTOMER_HASH(:NEW.C_USERNAME,:NEW.C_PASSWORD) INTO :NEW.C_PASSWORD FROM DUAL;
END;
/

--STORED PROCEDURE TO INSERT CUSTOMER
CREATE OR REPLACE PROCEDURE INSERT_CUSTOMER(
FIRSTNAME VARCHAR2, LASTNAME VARCHAR2, USERNAME VARCHAR2, PASSWORD VARCHAR2)
AS
BEGIN
  INSERT INTO CUSTOMER VALUES(NULL, FIRSTNAME, LASTNAME, USERNAME, PASSWORD);
  COMMIT;
END;
/

--TESTING
EXEC INSERT_CUSTOMER('Yuvaraj','Damodaran','yuvi','1234');

insert into customer values(1,'Tom','Brady','tom','tom');
insert into customer values('Russell','Wilson','russell','russell');

select * from customer;

drop sequence customer_seq;
commit;

