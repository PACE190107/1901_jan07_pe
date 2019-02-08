/*
    Project 1 Database Query Sheet
*/


--Remove all database objects
drop table REQUEST;
drop table EMPLOYEE;
drop sequence ASSIGN_E_ID;
drop sequence ASSIGN_R_ID;
commit;


-- Create table for Employee
create table EMPLOYEE(
    E_ID number(20) primary key,
    E_PASS varchar(50) not null,
    E_UNAME varchar(50) not null unique,
    E_FNAME varchar(50) not null,
    E_LNAME varchar(50) not null,
    E_EMAIL varchar(100) not null,
    E_MANAGER number(1) default 0
);
commit;

-- Create table for Requests
create table REQUEST(
    R_ID number(20) primary key,
    E_ID number(20) not null,
    R_SUBJECT varchar(50) not null,
    R_DESCRIPTION varchar(1000) not null,
    R_AMOUNT number(20,2) not null,
    R_STATUS varchar(8) default 'PENDING', --APPROVED, DENIED
    R_APPROVER number(20) default null,
    constraint fk_e_id
    foreign key (E_ID)
    references EMPLOYEE(E_ID)
    on delete cascade
);
commit;


-- Sequences for EMPLOYEE ID and REQUEST ID
create sequence ASSIGN_E_ID
    minvalue 1
    maxvalue 99999999999999999999
    start with 1
    increment by 1;
    
create sequence ASSIGN_R_ID
    minvalue 1
    maxvalue 99999999999999999999
    start with 1
    increment by 1;

--Password hash function
create or replace function GET_PASS_HASH(PASS VARCHAR2)
    return VARCHAR2
is
    HASH_VALUE VARCHAR(10) := 'SEA3PE40H';  --C3P0
begin
    return TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
    input => UTL_I18N.STRING_TO_RAW(DATA => PASS || HASH_VALUE)));
end;
/

create or replace trigger NEW_EMPLOYEE
    before insert
    on EMPLOYEE
    for each row
begin
        if :NEW.E_ID is null then
            select ASSIGN_E_ID.nextval into :NEW.E_ID from DUAL;
        end if;
        
        select GET_PASS_HASH(:NEW.E_PASS)
            into :NEW.E_PASS from DUAL;
end;
/

create or replace trigger NEW_REQUEST
    before insert
    on REQUEST
    for each row
begin
        if :NEW.R_ID is null then
            select ASSIGN_R_ID.nextval into :NEW.R_ID from DUAL;
        end if;
end;
/
    
create or replace procedure 
REGISTER_EMPLOYEE(EMAIL varchar2, UNAME varchar2, PASS varchar2, FNAME varchar2, LNAME varchar2)
as
begin
    insert into EMPLOYEE (E_ID, E_FNAME, E_LNAME, E_UNAME, E_PASS, E_EMAIL)
    values (null, FNAME, LNAME, UNAME, PASS, EMAIL);
    commit;
end;
/


create or replace procedure
    CREATE_REQUEST(E_ID varchar2, R_SUBJECT varchar2, R_DESCRIPTION varchar2,
    R_AMOUNT number, R_STATUS varchar2)
as
begin
    insert into REQUEST values (null, E_ID, R_SUBJECT, R_DESCRIPTION, R_AMOUNT,
        R_STATUS, null);
    commit;
end;
/

select * from EMPLOYEE;
select * from REQUEST;

EXEC REGISTER_EMPLOYEE('test@test.com', 'manager', 'bossman', 'Marcus', 'Anager');
update EMPLOYEE SET E_MANAGER = 1 WHERE E_UNAME = 'manager';
EXEC REGISTER_EMPLOYEE('edward@test.com', 'employee', 'norman', 'Edward', 'Mployee');

EXEC REGISTER_EMPLOYEE('nip@test.com', 'GeTRiGhT', 'PaSsWoRd', 'Christopher', 'Alesund');
EXEC REGISTER_EMPLOYEE('vpro@test.com', 'Pashabiceps', 'Papabiceps', 'Jaroslaw ', 'Jarz?bkowski');
EXEC REGISTER_EMPLOYEE('peanutbrain@test.com', 'Tarik', 'Tarik', 'Tarik', 'Celek');
EXEC REGISTER_EMPLOYEE('summit1g@test.com', 'Summit', 'Summit', 'Jaryd', 'Lazar');
EXEC REGISTER_EMPLOYEE('Morgs@test.com', 'thundamentals', 'ilovesongs', 'Jeswon', 'Tuka');
EXEC REGISTER_EMPLOYEE('aqua@barbie.girl', 'aqua', 'barbiegirl', 'Aqua', 'BarbieGirl');
INSERT INTO EMPLOYEE VALUES (null, 'test', 'test', 'test', 'test', 'test@test.test', 1);
update EMPLOYEE SET E_MANAGER = 1 WHERE E_UNAME = 'thundamentals';

EXEC CREATE_REQUEST(1, 'First Request', 'Give me money back', 350, 'PENDING');
EXEC CREATE_REQUEST(1, 'Approved Request', 'Need to borrow tree fitty', 350, 'APPROVED');
EXEC CREATE_REQUEST(1, 'Denied Request', 'Not the Lochness monster', 350, 'DENIED');
EXEC CREATE_REQUEST(2, 'Deny this Request', 'Keep the change', 99, 'PENDING');
EXEC CREATE_REQUEST(2, 'Deny this Request', 'Keep the change', 99, 'DENIED');
EXEC CREATE_REQUEST(3, 'Help', 'Need my money', 777, 'PENDING');
EXEC CREATE_REQUEST(3, 'Elite', 'The best request', 1337, 'APPROVED');
EXEC CREATE_REQUEST(4, 'Elite', 'The best request', 1337, 'PENDING');
EXEC CREATE_REQUEST(5, 'Only Request', 'No rush', 55555, 'APPROVED');
update REQUEST SET R_APPROVER = 1 WHERE R_STATUS != 'PENDING';
commit;

SELECT * FROM REQUEST WHERE R_STATUS = 'PENDING';
SELECT * FROM REQUEST WHERE R_STATUS != 'PENDING';
SELECT * FROM REQUEST WHERE E_ID = 4 AND R_STATUS = 'PENDING';