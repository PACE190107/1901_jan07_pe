--Drop existing objects
drop table Reimbursements;
drop table Employee_Info;
drop sequence reimbursement_seq;
drop PUBLIC SYNONYM Employee_Info;
drop PUBLIC SYNONYM Reimbursements;
drop PUBLIC SYNONYM reimbursement_seq;
--drop PUBLIC SYNONYM register_user;
drop PUBLIC SYNONYM create_reimbursement;
drop user super;

--Employee table
CREATE TABLE Employee_Info
(
    first_name varchar2(20),
    last_name varchar2(20),
    job_description varchar2(20),
    user_name varchar(30) not null,
    user_password varchar2(30),
    CONSTRAINT user_name_PK PRIMARY KEY (user_name)
);

--Reimbursement table
CREATE TABLE Reimbursements
(
    u_name varchar2(20) references Employee_Info(user_name),
    reimbursement_id number(10) not null,
    reason varchar2(50),
    amount decimal(18,2),
    reimbursement_status varchar2(20),
    approved_by varchar2(20),
    CONSTRAINT reimbursement_id_PK PRIMARY KEY (reimbursement_id)
);
    
--Generate auto-sequence for reimbursement_ID
CREATE SEQUENCE reimbursement_seq
    START WITH 100
    INCREMENT BY 7;    

--Trigger to initiate account sequence
CREATE OR REPLACE TRIGGER new_reimbursement
BEFORE INSERT
    ON Reimbursements
FOR EACH ROW
BEGIN
    --increment sequence
    IF :new.reimbursement_id IS NULL THEN
        SELECT reimbursement_seq.NEXTVAL INTO :new.reimbursement_id FROM dual;
    END IF;
END;
/

--Stored procedure to register a new user
--CREATE OR REPLACE PROCEDURE register_user
--(
--    firstname varchar2, lastname varchar2, username varchar2, password varchar2, rows out number
--)
--AS
--BEGIN
--    INSERT INTO User_Details VALUES(null, firstname, lastname, username, password);
--    rows := sql%rowcount;
--    COMMIT;
--END;
--/

--Stored procedure to generate a new bank account
CREATE OR REPLACE PROCEDURE create_reimbursement
(
    uname varchar2, reason varchar2, dollars decimal, status varchar2, rows out number
)
AS
BEGIN
    INSERT INTO Reimbursements VALUES(uname, null, reason, dollars, status, null);
    rows := sql%rowcount;
    COMMIT;
END;
/

--Synonyms
CREATE PUBLIC SYNONYM Employee_Info FOR MTRevature.Employee_Info;
CREATE PUBLIC SYNONYM Reimbursements FOR MTRevature.Reimbursements;
CREATE PUBLIC SYNONYM reimbursement_seq FOR MTRevature.reimbursement_seq;
--CREATE PUBLIC SYNONYM register_user FOR MTRevature.register_user;
CREATE PUBLIC SYNONYM create_reimbursement FOR MTRevature.create_reimbursement;

--Populate employees
insert into Employee_Info (first_name, last_name, job_description, user_name, user_password) 
    values('M', 'Karen', 'Manager', 'man1', 'qwerty321');
insert into Employee_Info (first_name, last_name, job_description, user_name, user_password) 
    values('M', 'Phil', 'Manager', 'man2', 'qwerty987');
insert into Employee_Info (first_name, last_name, job_description, user_name, user_password)
    values('E', 'Un', 'Employee', 'emp1', 'qwerty1');
insert into Employee_Info (first_name, last_name, job_description, user_name, user_password) 
    values('E', 'Deux', 'Employee', 'emp2', 'qwerty2');
insert into Employee_Info (first_name, last_name, job_description, user_name, user_password) 
    values('E', 'Trois', 'Employee', 'emp3', 'qwerty3');
insert into Employee_Info (first_name, last_name, job_description, user_name, user_password) 
    values('E', 'Quatre', 'Employee', 'emp4', 'qwerty4');
insert into Employee_Info (first_name, last_name, job_description, user_name, user_password) 
    values('E', 'Cinq', 'Employee', 'emp5', 'qwerty5');

--DataBase Admin
create user super identified by super;
grant dba to super WITH ADMIN OPTION;

commit;
select * from Employee_Info;
select * from Reimbursements;