DROP TABLE BANK_CHECK;
DROP TABLE BANK_SAVE;
DROP SEQUENCE BANK_CUST_SEQ;
DROP SEQUENCE BANK_CHECK_SEQ;
DROP SEQUENCE BANK_SAVE_SEQ;
DROP TABLE BANK_CUST;
COMMIT;

CREATE TABLE BANK_CUST
(
 CUST_ID NUMBER NOT NULL,
 CUST_FIRSTNAME VARCHAR (20) NOT NULL,
 CUST_LASTNAME VARCHAR (20) NOT NULL,
 CUST_USERNAME VARCHAR (100) NOT NULL,
 CUST_PASSWORD VARCHAR (100) NOT NULL,
 CONSTRAINT PK_CUST PRIMARY KEY (CUST_ID),
 CONSTRAINT UNQ_USER UNIQUE (CUST_USERNAME)
);
COMMIT;

CREATE TABLE BANK_CHECK
(
 CHECK_ID NUMBER NOT NULL,
 CHECK_AMT NUMBER NOT NULL,
 CUST_ID NUMBER NOT NULL,
 CONSTRAINT PK_CHECK PRIMARY KEY (CHECK_ID),
 CONSTRAINT FK_CHECK FOREIGN KEY (CUST_ID) REFERENCES BANK_CUST(CUST_ID)
);
COMMIT;

CREATE TABLE BANK_SAVE
(
 SAVE_ID NUMBER NOT NULL,
 SAVE_AMT NUMBER NOT NULL,
 CUST_ID NUMBER NOT NULL,
 CONSTRAINT PK_SAVE PRIMARY KEY (SAVE_ID),
 CONSTRAINT FK_SAVE FOREIGN KEY (CUST_ID) REFERENCES BANK_CUST(CUST_ID)
);
COMMIT;

CREATE SEQUENCE BANK_CUST_SEQ
  START WITH 1
  INCREMENT BY 1;
  
CREATE SEQUENCE BANK_CHECK_SEQ
 START WITH 1
 INCREMENT BY 1;
 
CREATE SEQUENCE BANK_SAVE_SEQ
 START WITH 1
 INCREMENT BY 1;
  
CREATE OR REPLACE FUNCTION GET_BANK_CUST_HASH(USERNAME VARCHAR2, PASSWORD VARCHAR2) RETURN VARCHAR2
IS
EXTRA VARCHAR2(10) := 'SALT';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || PASSWORD || EXTRA)));
END;
/

CREATE OR REPLACE TRIGGER BANK_CUST_B_INSERT
BEFORE INSERT
ON BANK_CUST
FOR EACH ROW
BEGIN
  IF :NEW.CUST_ID IS NULL THEN
    SELECT BANK_CUST_SEQ.NEXTVAL INTO :NEW.CUST_ID FROM DUAL;
  END IF;
  
  SELECT GET_BANK_CUST_HASH(:NEW.CUST_USERNAME,:NEW.CUST_PASSWORD) INTO :NEW.CUST_PASSWORD FROM DUAL;
END;
/

CREATE OR REPLACE PROCEDURE INSERT_BANK_CUST(
FIRSTNAME VARCHAR2, LASTNAME VARCHAR2, USERNAME VARCHAR2, PASSWORD VARCHAR2)
AS
BEGIN
  INSERT INTO BANK_CUST VALUES(NULL, FIRSTNAME, LASTNAME, USERNAME, PASSWORD);
  COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE UPDATE_BANK_CUST(
CUST_ID NUMBER, FIRSTNAME VARCHAR2, LASTNAME VARCHAR2, USERNAME VARCHAR2, PASSWORD VARCHAR2)
AS
BEGIN
  INSERT INTO BANK_CUST VALUES(CUST_ID, FIRSTNAME, LASTNAME, USERNAME, PASSWORD);
  COMMIT;
END;
/

--look into different select statements
CREATE OR REPLACE PROCEDURE VIEW_BANK_CUST(
CUST_ID_VIEW NUMBER)
AS
BEGIN
  SELECT CUST_ID FROM BANK_CUST WHERE CUST_ID=CUST_ID_VIEW;
  COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE DELETE_BANK_CUST(
CUST_ID_DELETE NUMBER)
AS
BEGIN
 DELETE FROM BANK_CUST WHERE CUST_ID = CUST_ID_DELETE CASCADE;
 COMMIT;
 END;
/

CREATE OR REPLACE PROCEDURE INSERT_BANK_CHECK(
CHECK_ID NUMBER, AMOUNT NUMBER, CUST_ID NUMBER)
AS
BEGIN
  INSERT INTO BANK_CHECK VALUES(CHECK_ID, AMOUNT, CUST_ID);
  COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE INSERT_BANK_SAVE(
SAVE_ID NUMBER, AMOUNT NUMBER, CUST_ID NUMBER)
AS
BEGIN
  INSERT INTO BANK_SAVE VALUES(SAVE_ID, AMOUNT, CUST_ID);
  COMMIT;
END;
/
COMMIT;

INSERT INTO BANK_CUST VALUES (1,'Josh','Bjork','jbjork','p4ssword');
INSERT INTO BANK_CHECK VALUES (100, 700, 1);
INSERT INTO BANK_SAVE VALUES (101, 200, 1);
COMMIT;

SELECT * FROM BANK_CUST;
SELECT * FROM BANK_CHECK;
SELECT * FROM BANK_SAVE;