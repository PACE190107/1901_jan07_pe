-------------------------------------------------------------
-------------------------------------------------------------
--CREATING TABLES
-------------------------------------------------------------
-------------------------------------------------------------
CREATE TABLE USERS 
(
  USER_ID NUMBER NOT NULL 
, USERNAME VARCHAR2(20 BYTE) NOT NULL 
, PASSWORD VARCHAR2(20 BYTE) NOT NULL 
, FIRSTNAME VARCHAR2(20 BYTE) NOT NULL 
, LASTNAME VARCHAR2(20 BYTE) NOT NULL 
, CONSTRAINT USERS_PK PRIMARY KEY 
  (
    USER_ID 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX USERS_PK ON USERS (USER_ID ASC) 
      LOGGING 
      TABLESPACE USERS 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        INITIAL 65536 
        NEXT 1048576 
        MINEXTENTS 1 
        MAXEXTENTS UNLIMITED 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
LOGGING 
TABLESPACE USERS 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1 
  MAXEXTENTS UNLIMITED 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NOPARALLEL;

ALTER TABLE USERS
ADD CONSTRAINT SYS_C003952 UNIQUE 
(
  USERNAME 
)
USING INDEX 
(
    CREATE UNIQUE INDEX SYS_C003952 ON USERS (USERNAME ASC) 
    LOGGING 
    TABLESPACE USERS 
    PCTFREE 10 
    INITRANS 2 
    STORAGE 
    ( 
      INITIAL 65536 
      NEXT 1048576 
      MINEXTENTS 1 
      MAXEXTENTS UNLIMITED 
      BUFFER_POOL DEFAULT 
    ) 
    NOPARALLEL 
)
 ENABLE;

CREATE TABLE ACCOUNTS 
(
  ACCOUNT_ID NUMBER NOT NULL 
, ACCOUNT_NUM VARCHAR2(20 BYTE) NOT NULL 
, ACCOUNT_BAL NUMBER(13, 2) NOT NULL 
, ACCOUNT_TYPE VARCHAR2(20 BYTE) NOT NULL 
, CONSTRAINT ACCOUNTS_PK PRIMARY KEY 
  (
    ACCOUNT_NUM 
  )
  USING INDEX 
  (
      CREATE UNIQUE INDEX ACCOUNTS_PK ON ACCOUNTS (ACCOUNT_NUM ASC) 
      LOGGING 
      TABLESPACE USERS 
      PCTFREE 10 
      INITRANS 2 
      STORAGE 
      ( 
        INITIAL 65536 
        NEXT 1048576 
        MINEXTENTS 1 
        MAXEXTENTS UNLIMITED 
        BUFFER_POOL DEFAULT 
      ) 
      NOPARALLEL 
  )
  ENABLE 
) 
LOGGING 
TABLESPACE USERS 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1 
  MAXEXTENTS UNLIMITED 
  BUFFER_POOL DEFAULT 
) 
NOCOMPRESS 
NOPARALLEL;

ALTER TABLE ACCOUNTS
ADD CONSTRAINT FK_ID FOREIGN KEY
(
  ACCOUNT_ID 
)
REFERENCES USERS
(
  USER_ID 
)
ON DELETE CASCADE ENABLE;


-------------------------------------------------------------
-------------------------------------------------------------
--PROCEDURES
-------------------------------------------------------------
-------------------------------------------------------------

create or replace PROCEDURE REGISTER_USER(
USERNAME VARCHAR2, PASSWORD VARCHAR2, FIRSTNAME VARCHAR2, LASTNAME VARCHAR2)
AS 
BEGIN
  INSERT INTO USERS (USER_ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME) VALUES(users_sequence.nextval, USERNAME, PASSWORD, FIRSTNAME, LASTNAME);
  --INSERT INTO ACCOUNTS (ACCOUNT_ID, CHECKING_NUM, CHECKING_BAL, SAVINGS_NUM, SAVINGS_BAL) VALUES(users_sequence.nextval, NULL, NULL, NULL, NULL);
  COMMIT;
END REGISTER_USER;


create or replace PROCEDURE DELETE_USER(
USER_ID NUMBER)
AS 
BEGIN
  DELETE FROM users WHERE user_id= USER_ID;
  COMMIT;
END DELETE_USER;

create or replace PROCEDURE DELETE_USER(
USER_ID NUMBER)
AS 
BEGIN
  DELETE FROM users WHERE user_id= USER_ID;
  COMMIT;
END DELETE_USER;

-------------------------------------------------------------
-------------------------------------------------------------
--TRIGGERS
-------------------------------------------------------------
-------------------------------------------------------------

create or replace TRIGGER accountnum_on_insert
    BEFORE INSERT ON accounts
    FOR EACH ROW
--The ‘code’ of the trigger itself is fairly simple: We SELECT the next 
--incremental value from our previously created users_sequence SEQUENCE, 
--and inserting that into the :new record of the users table in the specified .user_id field.
BEGIN 
    SELECT account_num_seq.nextval
    INTO :new.account_num
    FROM dual;
END;


create or replace TRIGGER users_on_insert
    BEFORE INSERT ON users
    FOR EACH ROW
--The ‘code’ of the trigger itself is fairly simple: We SELECT the next 
--incremental value from our previously created users_sequence SEQUENCE, 
--and inserting that into the :new record of the users table in the specified .user_id field.
BEGIN 
    SELECT users_sequence.nextval
    INTO :new.user_id
    FROM dual;
END;


-------------------------------------------------------------
-------------------------------------------------------------
--SEQUENCES
-------------------------------------------------------------
-------------------------------------------------------------

CREATE SEQUENCE ACCOUNT_NUM_SEQ INCREMENT BY 17 MAXVALUE 9999999999999999999999999999 MINVALUE 1 CACHE 20;
CREATE SEQUENCE USERS_SEQUENCE INCREMENT BY 1 MAXVALUE 9999999999999999999999999999 MINVALUE 1 CACHE 20;

