create table Employees(
    employee_id number,
    firstname varchar2(40) not null,
    lastname varchar2(40) not null,
    username varchar2(40) not null,
    pass varchar2(40) not null,
    isManager number not null,
    constraint Employee_PK primary key (employee_id),
    constraint Unique_Username unique (username),
    constraint isManager_True_or_False check (isManager = 0 or isManager = 1)
);

create table Reimbursements(
    reimbursement_id number,
    employee_id number not null,
    purpose varchar2(40) not null,
    amount number not null,
    approved_status varchar(40) not null,
    approved_by varchar(40),
    constraint Reimbursement_PK primary key (reimbursement_id)
);

--alter table Reimbursements add foreign key (employee_id) references Employees(employee_id);
alter table Reimbursements add constraint cascadeDeleteReimbursements foreign key (employee_id) references Employees(employee_id) on delete cascade;
commit;

--insert into Accounts values(1,0,0);
--insert into BankUsers values(1,1,'fname','lname','uname','pw');

--drop table Employees;
--drop table Reimbursements;
--commit;

-- Sequences used for auto-increment:  
create sequence Employee_seq
  start with 1
  increment by 1;
  
create sequence Reimbursement_seq
  start with 1
  increment by 1;
  
--drop sequence Employee_seq;
--drop sequence Reimbursement_seq;
  
--HASHING FUNCTION THAT COMBINES USERNAME, PASSWORD AND A SPECIAL WORD  
create or replace function get_user_hash(username varchar2, pass varchar2) return varchar2
is
extra varchar2(10) := 'Artificer';
begin
  return TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  input => UTL_I18N.STRING_TO_RAW(data => username || pass || extra)));
end;
/

--TRIGGER THAT GETS NEXT SEQUENCE VALUE FOR ID AND HASHES BLANK PASSWORD
create or replace trigger Employee_INSERT
BEFORE INSERT
ON Employees
FOR EACH ROW
BEGIN
  /* INCREASE THE SEQUENCE */
  IF :NEW.employee_id IS NULL THEN
    SELECT Employee_SEQ.NEXTVAL INTO :NEW.employee_id FROM DUAL;
  END IF;
  
  /* SAVE HASH INSTEAD OF PASSWORD */
  SELECT GET_USER_HASH(:NEW.username,:NEW.pass) INTO :NEW.pass FROM DUAL;
END;
/

--TRIGGER THAT GETS NEXT SEQUENCE VALUE FOR Account ID
create or replace trigger Reimbursement_insert
BEFORE INSERT
ON Reimbursements
FOR EACH ROW
BEGIN
  /* INCREASE THE SEQUENCE */
  IF :NEW.reimbursement_id IS NULL THEN
    SELECT Reimbursement_seq.NEXTVAL INTO :NEW.reimbursement_id FROM DUAL;
  END IF;
END;
/
-- Stored procedure to insert user
CREATE OR REPLACE PROCEDURE Insert_Employee(
firstname in VARCHAR2, lastname in VARCHAR2, username in VARCHAR2, pass in VARCHAR2, isManager in NUMBER, resultCount out number)
AS
    beforeCount number;
    afterCount number;
BEGIN
    select count (*) into beforeCount from Employees;
  insert into Employees values(null, firstname, lastname, username, pass, isManager);
  commit;
    select count (*) into afterCount from Employees;
    resultCount := afterCount - beforeCount;
END;
/


-- Stored procedure to create account
CREATE OR REPLACE PROCEDURE Insert_Reimbursement(employee_id in number, purpose in varchar2, amount in number, resultCount out number)
AS
    beforeCount number;
    afterCount number;
BEGIN
    select count (*) into beforeCount from Reimbursements;
  insert into Reimbursements values(null, employee_id, purpose, amount, 'pending', null);
  commit;
    select count (*) into afterCount from Reimbursements;
    resultCount := afterCount - beforeCount;
END;
/
--insert into Employees values(null,'Ren','Amamiya','Joker','Arsene',1);
--insert into Employees values(null,'Mitchell','Coon','Mitch','Coon',1);
--select * from Employees;
--select employee_id, firstname, lastname, username, pass, isManager from Employees where Employees.username = 'Mitch' and Employees.pass = get_User_Hash('Mitch', 'Coon');
--commit;
--select * from Reimbursements;
--update Reimbursements set approved_status = 'approved', approved_by = 'Mitch' where reimbursement_id = 8;