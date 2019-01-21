create table USERTABLE
(
USER_ID number(10) primary key,
USER_NAME varchar2(50),
USER_PASSWORD varchar2(50),
FIRST_NAME varchar2(50),
LAST_NAME varchar2(50)

);

CREATE SEQUENCE SEQUSER START WITH 1 INCREMENT BY 5;

insert into usertable values(SEQUSER.NEXTVAL, 'twjordan', 'tim93', 'Timothy', 'Jordan');

--Create stored procedure
CREATE OR REPLACE PROCEDURE INSERT_USER(
USER_NAME varchar2,
USER_PASSWORD varchar2,
FIRST_NAME varchar2,
LAST_NAME varchar2
)
AS
BEGIN
insert into usertable values(
SEQUSER.NEXTVAL,
USER_NAME,
USER_PASSWORD,
FIRST_NAME,
LAST_NAME
);
commit;
end;



create table ACCOUNTSTABLE
(
USER_ID number(10) references USERTABLE(USER_ID),
ACCOUNT_ID number(10) primary key,
ACCOUNT_TYPE varchar2(50),
ACCOUNT_BALANCE decimal(20,2)
);

CREATE SEQUENCE SEQACCOUNT START WITH 1000 INCREMENT BY 5;

insert into ACCOUNTSTABLE values(1, SEQACCOUNT.NEXTVAL, 'Checking', 200.25);
insert into ACCOUNTSTABLE values(1, SEQACCOUNT.NEXTVAL, 'Savings', 200.25);

--Create stored procedure
CREATE OR REPLACE PROCEDURE CREATE_ACCOUNT(
USER_ID varchar2,
ACCOUNT_TYPE varchar2,
ACCOUNT_BALANCE decimal

)
AS
BEGIN
insert into accountstable values(
USER_ID,
SEQACCOUNT.NEXTVAL,
ACCOUNT_TYPE,
ACCOUNT_BALANCE
);
commit;
end;


CREATE OR REPLACE PROCEDURE WITHDRAW_UPDATE(
NEW_ACCOUNT_BALANCE decimal,
NEW_ACCOUNT_ID varchar2

)
AS
BEGIN
UPDATE ACCOUNTSTABLE set ACCOUNT_BALANCE = NEW_ACCOUNT_BALANCE
WHERE ACCOUNT_ID = NEW_ACCOUNT_ID;
commit;
end;


