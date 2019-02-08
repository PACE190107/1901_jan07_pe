-------------------------------------------------------------------------------
-- TABLES
-------------------------------------------------------------------------------
CREATE TABLE EMPLOYEE
(
    E_ID NUMBER NOT NULL,
    E_USERNAME VARCHAR2(50) NOT NULL,
    E_PASSWORD VARCHAR2(50) NOT NULL,
    E_EMAIL VARCHAR2(50) NOT NULL,
    E_FIRSTNAME VARCHAR2(50) NOT NULL,
    E_LASTNAME VARCHAR2(50) NOT NULL,
    E_IS_MANAGER CHAR(1) NOT NULL,
    CONSTRAINT PK_E_ID PRIMARY KEY (E_ID),
    CONSTRAINT UNQ_E_USERNAME UNIQUE (E_USERNAME)
);

CREATE TABLE REIMBURSEMENT
(
    E_ID_AUTHORIZER NUMBER,
    E_ID_REQUESTER NUMBER NOT NULL,
    R_ID NUMBER NOT NULL,
    R_TYPE VARCHAR2(50) NOT NULL,
    R_STATUS VARCHAR2(50) NOT NULL,
    R_AMOUNT NUMBER NOT NULL,
    R_DATE VARCHAR2(50) NOT NULL,
    CONSTRAINT PK_R_ID PRIMARY KEY (R_ID),
    CONSTRAINT FK_E_ID_REQUESTER FOREIGN KEY (E_ID_REQUESTER) REFERENCES EMPLOYEE(E_ID)
);

-------------------------------------------------------------------------------
-- USEFUL STATEMENTS
-------------------------------------------------------------------------------

INSERT INTO EMPLOYEE VALUES(0, 'admin', 'admin', 'admin@admin.admin', 'admin', 'admin', 1);
INSERT INTO EMPLOYEE VALUES(1, 'manager0', '37D26C0AE96714114993CE43B34CF30C', 'manager0@revature.com', 'Jason', 'Davies', 1);
INSERT INTO EMPLOYEE VALUES(1, 'manager1', '203E890F051ECE279C7CED6C6199550A', 'manager0@revature.com', 'NotJason', 'NotDavies', 1);

--UPDATE EMPLOYEE SET E_PASSWORD = '37D26C0AE96714114993CE43B34CF30C' WHERE E_ID = 1;
SELECT GET_EMPLOYEE_HASH('manager1', 'manager1') FROM dual;

SELECT * FROM EMPLOYEE;
SELECT * FROM REIMBURSEMENT;

--DELETE FROM REIMBURSEMENT WHERE R_ID=75;
--DELETE FROM EMPLOYEE WHERE E_ID=111;

--CREATE USER admin IDENTIFIED BY admin;
--CREATE USER manager0 IDENTIFIED BY manager0;
--GRANT DBA TO manager0 WITH ADMIN OPTION;

DELETE FROM EMPLOYEE WHERE E_ID = 125;

EXEC DELETE_ALL_EMPLOYEE;
EXEC DELETE_ALL_REIMBURSEMENT;

--DROP TABLE REIMBURSEMENT;
--DROP TABLE EMPLOYEE;

commit;

-------------------------------------------------------------------------------
-- SEQUENCERS
-------------------------------------------------------------------------------

CREATE SEQUENCE EMPLOYEE_SEQ
    START WITH 1
    INCREMENT BY 1;
    
CREATE SEQUENCE REIMBURSEMENT_SEQ
    START WITH 1
    INCREMENT BY 1;

-------------------------------------------------------------------------------
-- STORED PROCEDURES
-------------------------------------------------------------------------------

-- YUVI's - HASHING FUNCTION THAT COMBINES USERNAME, PASSWORD AND A SPECIAL WORD
CREATE OR REPLACE FUNCTION GET_EMPLOYEE_HASH(USERNAME VARCHAR2, PASSWORD VARCHAR2) RETURN VARCHAR2
IS
EXTRA VARCHAR2(10) := 'SALT';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || PASSWORD || EXTRA)));
END;
/

CREATE OR REPLACE PROCEDURE INSERT_EMPLOYEE(USERNAME VARCHAR2, PASS VARCHAR2,
EMAIL VARCHAR2, F_NAME VARCHAR2, L_NAME VARCHAR2, IS_MANAGER CHAR, OUTINT OUT INTEGER)
AS
BEGIN
  INSERT INTO EMPLOYEE VALUES(NULL, USERNAME, PASS, EMAIL, F_NAME, L_NAME, IS_MANAGER);
  OUTINT := SQL%ROWCOUNT;
  COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE INSERT_REIMBURSEMENT(AUTH_ID NUMBER, REQ_ID NUMBER,
RE_TYPE VARCHAR2, STATUS VARCHAR2, AMOUNT NUMBER, RE_DATE VARCHAR2, OUTINT OUT INTEGER)
AS
BEGIN
  INSERT INTO REIMBURSEMENT VALUES(AUTH_ID, REQ_ID, NULL, RE_TYPE, STATUS, AMOUNT, RE_DATE);
  OUTINT := SQL%ROWCOUNT;
  COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE DELETE_ALL_EMPLOYEE
AS
BEGIN
    DELETE FROM (SELECT * FROM EMPLOYEE);
end;
/

CREATE OR REPLACE PROCEDURE DELETE_ALL_REIMBURSEMENT
AS
BEGIN
    DELETE FROM (SELECT * FROM REIMBURSEMENT);
end;
/

-------------------------------------------------------------------------------
-- TRIGGERS
-------------------------------------------------------------------------------
    
CREATE OR REPLACE TRIGGER EMPLOYEE_B_INSERT
BEFORE INSERT ON EMPLOYEE FOR EACH ROW
BEGIN
  -- INCREASE THE SEQUENCE
  IF :NEW.E_ID IS NULL THEN
    SELECT EMPLOYEE_SEQ.NEXTVAL INTO :NEW.E_ID FROM DUAL;
  END IF;
END;
/

CREATE OR REPLACE TRIGGER REIMBURSEMENT_B_INSERT
BEFORE INSERT ON REIMBURSEMENT FOR EACH ROW
BEGIN
  -- INCREASE THE SEQUENCE
  IF :NEW.R_ID IS NULL THEN
    SELECT REIMBURSEMENT_SEQ.NEXTVAL INTO :NEW.R_ID FROM DUAL;
  END IF;
END;
/
 
-------------------------------------------------------------------------------
-- SYNONYMS
-------------------------------------------------------------------------------

CREATE OR REPLACE PUBLIC SYNONYM EMPLOYEE FOR admin.EMPLOYEE;
CREATE OR REPLACE PUBLIC SYNONYM REIMBURSEMENT FOR admin.REIMBURSEMENT;
CREATE OR REPLACE PUBLIC SYNONYM EMPLOYEE_SEQ FOR admin.EMPLOYEE_SEQ;
CREATE OR REPLACE PUBLIC SYNONYM REIMBURSEMENT_SEQ FOR admin.REIMBURSEMENT_SEQ;
CREATE OR REPLACE PUBLIC SYNONYM INSERT_EMPLOYEE FOR admin.INSERT_EMPLOYEE;
CREATE OR REPLACE PUBLIC SYNONYM INSERT_REIMBURSEMENT FOR admin.INSERT_REIMBURSEMENT;
CREATE OR REPLACE PUBLIC SYNONYM DELETE_ALL_EMPLOYEE FOR admin.DELETE_ALL_EMPLOYEE;
CREATE OR REPLACE PUBLIC SYNONYM DELETE_ALL_REIMBURSEMENT FOR admin.DELETE_ALL_REIMBURSEMENT;
CREATE OR REPLACE PUBLIC SYNONYM EMPLOYEE_B_INSERT FOR admin.EMPLOYEE_B_INSERT;
CREATE OR REPLACE PUBLIC SYNONYM REIMBURSEMENT_B_INSERT FOR admin.REIMBURSEMENT_B_INSERT;   