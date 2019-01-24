drop table bank_transaction;
drop table bank_account;
drop table bank_user; 
drop sequence bank_User_Sequence;
drop sequence bank_Account_Sequence;
drop sequence bank_transaction_Sequence;
drop procedure insert_user;
drop procedure insert_account;
drop procedure insert_transaction;
commit; 


--BANK USER TABLE AND FUNCTIONALITY-------------------------------

CREATE TABLE bank_user(
    user_password varchar2(40) not null,
    username varchar2(40) unique not null,
    user_id number(10) primary key
);

create sequence bank_User_Sequence
    start with 1
    increment by 1;
    
--HASHING FUNCTION THAT COMBINES USERNAME, PASSWORD AND A SPECIAL WORD  
CREATE OR REPLACE FUNCTION GET_USER_HASH(USERNAME VARCHAR2, PASSWORD VARCHAR2) RETURN VARCHAR2
IS
EXTRA VARCHAR2(10) := 'ATG';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || PASSWORD || EXTRA)));
END;
/
-----


--TRIGGER THAT GETS NEXT SEQUENCE VALUE FOR ID AND HASHES BLANK PASSWORD
CREATE OR REPLACE TRIGGER User_Insert_TRIGGER
BEFORE INSERT
ON bank_user
FOR EACH ROW
BEGIN
  /* INCREASE THE SEQUENCE */
  IF :NEW.USER_ID IS NULL THEN
    SELECT bank_user_sequence.NEXTVAL INTO :NEW.USER_ID FROM DUAL;
  END IF;
  
  /* SAVE HASH INSTEAD OF PASSWORD */
  --SELECT GET_USER_HASH(:NEW.USERNAME,:NEW.USER_PASSWORD) INTO :NEW.USER_PASSWORD FROM DUAL;
END;
/
-----


--STORED PROCEDURE TO INSERT USER
CREATE OR REPLACE PROCEDURE INSERT_USER(pswd VARCHAR2, username VARCHAR2)
AS
BEGIN
  INSERT INTO BANK_USER VALUES(pswd, username, null );
  COMMIT;
END;
/
----

--BANK ACCOUNT TABLE AND FUNCTIONALITY-------------------------------

create table bank_account
(
    balance NUMBER (10) default 0 not null,
    user_id number(10) references bank_user(user_id) not null,
    account_Type varchar2(40),
    account_id number(10) primary key,
    CONSTRAINT account_cascade
        foreign key (user_id)
        references bank_user (user_id)
        on delete cascade
);

create sequence bank_account_sequence
    start with 1
    increment by 1;
    
    
--STORED PROCEDURE TO INSERT ACCOUNT
CREATE OR REPLACE PROCEDURE INSERT_ACCOUNT(BALANCE number, user_id number, account_type varchar2)
AS
BEGIN
  INSERT INTO BANK_ACCOUNT VALUES(balance, user_id, account_type, bank_account_sequence.nextVal);
  COMMIT;
END;
/
-----

--BANK TRANSACTION TABLE AND FUNCTIONALITY------------------------
    
create table bank_transaction
(
    amount number(10),
    transaction_type varchar2(28),
    account_id number(10) references bank_account(account_id),
    transaction_id number(10) primary key,
    CONSTRAINT transaction_cascade
        foreign key (account_id)
        references bank_account (account_id)
        on delete cascade
);

create sequence bank_transaction_sequence
    start with 1
    increment by 1;


--STORED PROCEDURE TO INSERT TRANSACTION and UPDATE ACCOUNT
CREATE OR REPLACE PROCEDURE INSERT_TRANSACTION
( transaction_type VARCHAR2, AMOUNT NUMBER, a_id number)
AS
BEGIN
  INSERT INTO BANK_TRANSACTION VALUES(amount, transaction_type, 
                                a_id, bank_transaction_sequence.nextVal);
  UPDATE bank_account SET balance = balance + amount where account_id = a_id;
  COMMIT;
END;
/
----


--DELETION PROCEDURES---------------------------------------------

--STORED PROCEDURE TO delete accounts
CREATE OR REPLACE PROCEDURE delete_account
(a_id number)
AS
BEGIN
  delete from bank_account where account_id = a_id;
  COMMIT;
END;
/
----

--STORED PROCEDURE TO delete TRANSACTION
CREATE OR REPLACE PROCEDURE delete_transaction
(t_id number)
AS
BEGIN
  DELETE FROM BANK_TRANSACTION WHERE transaction_id = t_id;
  COMMIT;
END;
/
----
select * from bank_user;


--stored procedure to delete users
CREATE OR REPLACE PROCEDURE delete_user
(u_id NUMBER)
AS
BEGIN
    DELETE FROM BANK_USER WHERE user_id = u_id;
    COMMIT;
END;
/
----

