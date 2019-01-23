drop table BANK_USER;
CREATE TABLE BANK_USER
(
USERNAME VARCHAR2(20) NOT NULL UNIQUE,
PASSPHRASE VARCHAR2(20) NOT NULL,
USER_ID INT PRIMARY KEY,
SUPER INT NOT NULL
);

insert into BANK_USER values ('books_admin', 'MyPassword', 1, 0);
insert into BANK_USER values ('TestUser', 'MyTestPassword', 2, 0);
insert into BANK_USER values ('Olympia', 'zeus', 3, 0);
insert into BANK_USER values ('Odin', 'thor', 4, 0);
insert into BANK_USER values ('super', 'man', 5, 1);

delete from BANK_USER WHERE USERNAME = 'super';
select * from BANK_USER;

DROP TABLE BANK_ACCOUNT;
CREATE TABLE BANK_ACCOUNT
(
ACCOUNT_TYPE VARCHAR2(10) NOT NULL,
ACCOUNT_ID INT PRIMARY KEY,
BALANCE FLOAT,
USER_ID INT NOT NULL, 
FOREIGN KEY(USER_ID) REFERENCES BANK_USER(USER_ID)
);

insert into BANK_ACCOUNT values ('Checking', 100092, 2000, 1);
insert into BANK_ACCOUNT values ('Checking', 100093, 2000, 2);
insert into BANK_ACCOUNT values ('Checking', 100094, 2000, 3);
insert into BANK_ACCOUNT values ('Checking', 100095, 2000, 4);

select * from BANK_ACCOUNT; 

--create trigger for changeBalance for transaction history
--use if else statements for overdraft protection and wrong account info

--Incrementing userID sequence 
drop sequence USER_ID_SEQ;
CREATE SEQUENCE USER_ID_SEQ
  START WITH 1000
  INCREMENT BY 1;
  
--Incrementing ACCOUNT_ID sequence 
drop sequence ACCOUNT_ID_SEQ;
CREATE SEQUENCE ACCOUNT_ID_SEQ
  START WITH 1000
  INCREMENT BY 1;
 
--Updating USER_ID for new BANK_USER 
drop trigger user_insert; 
CREATE OR REPLACE TRIGGER USER_INSERT
BEFORE INSERT
ON BANK_USER
FOR EACH ROW
BEGIN
    SELECT USER_ID_SEQ.NEXTVAL INTO :NEW.USER_ID FROM DUAL;
END;
/

--Updating ACCOUNT_ID for new BANK_ACCOUNT
drop trigger ACCOUNT_insert; 
CREATE OR REPLACE TRIGGER ACCOUNT_INSERT
BEFORE INSERT
ON BANK_ACCOUNT
FOR EACH ROW
BEGIN
    SELECT ACCOUNT_ID_SEQ.NEXTVAL INTO :NEW.ACCOUNT_ID FROM DUAL;
END;
/
--Updating the balance (deposit/withdrawl)
CREATE OR REPLACE PROCEDURE changeBalance (amount float, accountId int, userId int)
AS
BEGIN
UPDATE BANK_ACCOUNT
SET BALANCE = BALANCE + amount
WHERE ACCOUNT_ID = accountId 
AND BANK_ACCOUNT.USER_ID = userId;
COMMIT;
END;
/

--Inserting a new user into a row 
CREATE OR REPLACE PROCEDURE newUser (username varchar2, passphrase varchar2)
AS
BEGIN
insert into BANK_USER values (username, passphrase, null, 0);
COMMIT;
END;
/

--Inserting a new account into BANK_ACCOUNT
CREATE OR REPLACE PROCEDURE newAccount (acctType varchar2, userId int)
AS
BEGIN
insert into BANK_ACCOUNT values (acctType, null, 0, userId);
COMMIT;
END;
/
drop user cmon;

CREATE USER books_admin IDENTIFIED BY MyPassword;
grant connect, resource to books_admin;
grant DBA to books_admin with admin option;
GRANT aq_administrator_role TO books_admin;
commit;

commit;

