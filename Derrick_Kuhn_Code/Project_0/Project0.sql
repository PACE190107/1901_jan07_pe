-- Project 0 JDBC Bank

-- User table for login information
-- super_user bit 0 = non-superuser; 1 = superuser
-- Accounts table for managing user accounts
drop table bank_account;
drop table bank_user;
commit;

create table bank_user ( 
    u_name varchar2(20) not null unique,
    u_password varchar2(100) not null,
    u_id number(10) primary key,
    u_super number(1) not null
);


create table bank_account (
    a_id number(20) unique not null,
    a_balance float(50) not null,
    a_name varchar2(20) not null,
    u_id number(10) not null,
    constraint fk_u_id
    foreign key (U_ID)
    references BANK_USER(U_ID)
    on delete cascade
);

--BANK_USER  functions/procedures/sequences
--user id, incremental sequence
drop sequence ASSIGN_USER_ID;
commit;
create sequence ASSIGN_USER_ID
    minvalue 1
    maxvalue 9999999999
    start with 1
    increment by 1;
    
--Password hash function
create or replace function GET_USER_HASH(USERNAME VARCHAR2, PASSWORD VARCHAR2)
    return VARCHAR2
is
    HASH_VALUE VARCHAR(10) := 'ZOIDBERG';
begin
    return TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
    input => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || PASSWORD || HASH_VALUE)));
end;
/

--Trigger to generate user_id for new user records
create or replace trigger NEW_USER_CREATION
    before insert
    on BANK_USER
    for each row
begin
        if :NEW.U_ID is null then
            select assign_user_id.nextval into :NEW.U_ID from DUAL;
        end if;
        
        --select GET_USER_HASH(:NEW.U_NAME, :NEW.U_PASSWORD)
         --   into :NEW.U_PASSWORD from DUAL;
end;
/
commit;

--Register a new user procedure
create or replace procedure REGISTER_USER(NAME varchar2, PASSWORD varchar2)
as
begin
    insert into BANK_USER VALUES(NAME, PASSWORD, NULL, 0);
    commit;
end;
/

commit;

--BANK_ACCOUNT functions/procedures/sequences
drop sequence ASSIGN_ACCOUNT_ID;
create sequence assign_account_id
    minvalue 1
    maxvalue 99999999999999999999
    start with 1
    increment by 1;

--Trigger for assigning account number
create or replace trigger NEW_ACCOUNT_CREATION
    before insert
    on BANK_ACCOUNT
    for each row
begin
    if :NEW.A_ID is null then
        select ASSIGN_ACCOUNT_ID.nextval into :NEW.A_ID from DUAL;
    end if;
end;
/

create or replace procedure CREATE_ACCOUNT(
    A_NAME VARCHAR2, U_ID VARCHAR2)
as
begin
    insert into BANK_ACCOUNT values (null, 0, A_NAME, U_ID);
    commit;
end;
/

--create or replace procedure UPDATE_BALANCE(




insert into BANK_USER values ('admin', 'admin', null, 1);



--Debug commands
select * from BANK_USER;
select * from BANK_ACCOUNT;
--Test Procedure (U_NAME, U_PASSWORD)
EXEC REGISTER_USER('rob', 'test');
EXEC CREATE_ACCOUNT('Checking1', 2)
EXEC CREATE_ACCOUNT('Checking2', 2)
EXEC CREATE_ACCOUNT('Checking3', 2)
commit;

--delete BANK_USER where U_ID != 1;
update BANK_USER set U_ID = 22, U_NAME = 'robert', U_PASSWORD = 'testing' where U_ID = 2;