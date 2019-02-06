CREATE TABLE EMPLOYEE (
E_ID number not null,
E_FIRST varchar2(50),
E_LAST varchar2(50),
E_USERNAME varchar2(50),
E_PASSWORD varchar2(50),
E_MANAGER number(1,0),
E_EMAIL varchar2(50),
CONSTRAINT UNIQUE_USERNAME UNIQUE (E_USERNAME),
CONSTRAINT E_ID_PK PRIMARY KEY (E_ID)
);

CREATE SEQUENCE EMPLOYEE_SEQ
START WITH 1
INCREMENT BY 1;


CREATE OR REPLACE TRIGGER EMPLOYEE_B_INSERT
BEFORE INSERT
ON EMPLOYEE
FOR EACH ROW
BEGIN
  /* INCREASE THE SEQUENCE */
  IF :NEW.E_ID IS NULL THEN
    SELECT EMPLOYEE_SEQ.NEXTVAL INTO :NEW.E_ID FROM DUAL;
  END IF;
  
  /* SAVE HASH INSTEAD OF PASSWORD */
  SELECT GET_USER_HASH(:NEW.E_USERNAME,:NEW.E_PASSWORD) INTO :NEW.E_PASSWORD FROM DUAL;
END;
/

CREATE OR REPLACE PROCEDURE INSERT_EMPLOYEE(
FIRSTNAME VARCHAR2, LASTNAME VARCHAR2, USERNAME VARCHAR2, PASSWORD VARCHAR2, MANAGER number, EMAIL varchar2)
AS
BEGIN
  INSERT INTO EMPLOYEE VALUES(NULL, FIRSTNAME, LASTNAME, USERNAME, PASSWORD, MANAGER, EMAIL);
  COMMIT;
END;
/


CREATE TABLE REQUEST (
R_ID NUMBER,
R_TYPE VARCHAR2(50) NOT NULL,
R_AMOUNT NUMBER,
R_DESCRIPTION VARCHAR2(100),
R_USERID NUMBER,
R_MANAGER NUMBER,
R_APPROVALE NUMBER,
CONSTRAINT R_ID_PK PRIMARY KEY (R_ID),
CONSTRAINT R_UID_PK FOREIGN KEY(R_USERID) REFERENCES EMPLOYEE(E_ID)
);


CREATE SEQUENCE REQUEST_SEQ
START WITH 1
INCREMENT BY 1;


CREATE OR REPLACE TRIGGER REQUEST_B_INSERT
BEFORE INSERT
ON REQUEST
FOR EACH ROW
BEGIN
  /* INCREASE THE SEQUENCE */
  IF :NEW.R_ID IS NULL THEN
    SELECT REQUEST_SEQ.NEXTVAL INTO :NEW.R_ID FROM DUAL;
  END IF;
  
END;
/

CREATE OR REPLACE PROCEDURE INSERT_REQUEST(
R_TYPE VARCHAR2, AMOUNT NUMBER, R_DESCRIPTION VARCHAR2, USERID NUMBER)
AS
BEGIN
  INSERT INTO REQUEST VALUES(NULL, R_TYPE, AMOUNT, R_DESCRIPTION, USERID, 0, 0);
  COMMIT;
END;
/

commit;

exec INSERT_EMPLOYEE('first','last','first','password',0,'sample@website.com');
exec INSERT_EMPLOYEE('Isa','Manager','mag1','password',1,'sample1@website.com');



select * from employee;

select * from REQUEST where R_APPROVALE = 0;

select * from EMPLOYEE WHERE E_PASSWORD LIKE GET_USER_HASH('first','password');