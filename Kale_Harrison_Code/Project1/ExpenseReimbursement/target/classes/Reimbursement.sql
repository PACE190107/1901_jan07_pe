-------------------------------------------------
drop table EMPLOYEE;
CREATE TABLE EMPLOYEE (
e_id INT PRIMARY KEY,
e_username VARCHAR2(20) NOT NULL UNIQUE,
e_password VARCHAR2(200) NOT NULL,
e_firstName VARCHAR2(40) NOT NULL,
e_lastName VARCHAR2(40) NOT NULL,
e_email VARCHAR2(50) NOT NULL,
e_isManager INT NOT NULL
);

-------------------------------------------------
DROP TABLE REIMBURSEMENT;
CREATE TABLE REIMBURSEMENT (
r_id INT PRIMARY KEY,
r_amount NUMBER,
r_description VARCHAR2(40),
r_status VARCHAR2(10),
r_resolverId INT, 
e_id INT NOT NULL, 
FOREIGN KEY(e_id) REFERENCES EMPLOYEE(e_id)
);
-------------------------------------------------
commit;
insert into EMPLOYEE values (1, 'test', 'pass', 'kale', 'harrison', 'testEmail', 0);
insert into EMPLOYEE values (2, 'user2', 'pass2', 'joe', 'blow', '@something.com', 0);
insert into EMPLOYEE values (3, 'user3', 'pass3', 'boss', 'man', 'bossEmail', 1);
insert into EMPLOYEE values (4, 'hashuser', 'hashpass', 'name', 'lastname', 'email', 0);
insert into REIMBURSEMENT values (200, 10.00, 'a test example', 'Pending', null, 1);
insert into REIMBURSEMENT values (400, 20.00, 'a test example', 'Approved', null, 1);
delete from EMPLOYEE where e_id = 1;

--Submit reimbursement request ---------------------------------------
--Incrementing REIMBURSEMENT_ID sequence 
drop sequence REIMBURSEMENT_ID_SEQ;
CREATE SEQUENCE REIMBURSEMENT_ID_SEQ
  START WITH 1000
  INCREMENT BY 1;
  
 --Updating REIMBURSEMENT_ID for new REIMBURSEMENT
drop trigger REIMBURSEMENT_insert; 
CREATE OR REPLACE TRIGGER REIMBURSEMENT_INSERT
BEFORE INSERT
ON REIMBURSEMENT
FOR EACH ROW
BEGIN
    SELECT REIMBURSEMENT_ID_SEQ.NEXTVAL INTO :NEW.r_id FROM DUAL;
END;
/
--Don't need manager ID 
CREATE OR REPLACE PROCEDURE newRequest(amount number, description_text varchar2, e_id int)
AS
BEGIN
insert into REIMBURSEMENT values (null, amount, description_text, 'Pending', null, e_id);
COMMIT;
END;
/

exec newRequest(300, 'some description', 1);
select * from REIMBURSEMENT;
select * from EMPLOYEE;


/*
Do this within Java
Simply compare the input password with the password of the Database user 
After verifying valid login, check if user is super user.
	During the login verification, return a new Employee object that contains
	e_isManager If so, go to super menu. 
*/

-------------------------------------------------------------------------
--Employee view pending reimbursement requests for an employee 
--Can also be used for manager finding specific employee reimbursements
/*
Do this within Java
Select * from REIMBURSEMENT
WHERE e_id = (the user's id) && r_status = 'Pending'
Store the results in an ArrayList in Java. 
Do an enhanced for loop to retrieve the needed data
*/
-------------------------------------------------------------------------
--Employee view resolved reimbursement requests 
--Can also be used for manager finding specific employee reimbursements
/*
Do this within Java
Select * from REIMBURSEMENT 
WHERE e_id = (the user's id) && r_status = 'Resolved'
Store the results in an ArrayList in Java. 
Do an enhanced for loop to retrieve the needed data
*/
-------------------------------------------------------------------------
--Employee view their account info
/*
THIS IS DONE
If I store the values in Java, then I should be able to just pass the information I need 
in responses. 
Grab all the personal data when verifying login.
*/
-------------------------------------------------------------------------
--Employee Update info
--Change username
DROP PROCEDURE updateUsername;
CREATE OR REPLACE PROCEDURE updateUsername(newUsername varchar2, em_id int)
AS
BEGIN
UPDATE EMPLOYEE SET e_username = newUsername WHERE e_id= em_id;
COMMIT;
END;
/

exec updateUsername('test', 4);
select * from Employee;

--Change Password
DROP PROCEDURE updatePassword;
CREATE OR REPLACE PROCEDURE updatePassword(newPassword varchar2, em_id int)
AS
BEGIN
UPDATE EMPLOYEE SET e_password = newPassword WHERE e_id= em_id;
COMMIT;
END;
/
exec updatePassword('test', 1);
select * from Employee;

--Change Email
DROP PROCEDURE updateEmail;
CREATE OR REPLACE PROCEDURE updateEmail(newEmail varchar2, em_id int)
AS
BEGIN
UPDATE EMPLOYEE SET e_email = newEmail WHERE e_id= em_id;
COMMIT;
END;
/
exec updateEmail('test', 1);
select * from Employee;
-------------------------------------------------------------------------
--Employee View Pending
DROP PROCEDURE e_viewPending;
CREATE OR REPLACE PROCEDURE e_viewPending(userID int)
AS
BEGIN
Select * from REIMBURSEMENT WHERE r_status = 'Pending' AND e_id = 1;
COMMIT;
END;
/
--Manager view all pending requests
Select * from REIMBURSEMENT WHERE r_status = 'Pending';
--Manager view all approved requests 
Select * from REIMBURSEMENT WHERE r_status = 'Approved';
--Manager view single pending request
Select * from REIMBURSEMENT WHERE e_id = (theusersid) AND r_status = 'Pending';
--Manager view single resolved request
Select * from REIMBURSEMENT 
WHERE e_id = (theusersid) AND r_status = 'Approved';
--Manager view all employees 
Select * from EMPLOYEES;
-------------------------------------------------------------------------
--Manager approve/deny 
CREATE OR REPLACE PROCEDURE approval (re_id int, manager_id int)
AS
BEGIN
UPDATE REIMBURSEMENT set r_status = 'Approved' WHERE r_id = re_id;
UPDATE REIMBURSEMENT set r_resolverId = manager_id WHERE r_id = re_id;
COMMIT;
END;
/
SELECT * FROM REIMBURSEMENT WHERE r_status = 'Approved' AND e_id = 1;
SELECT * FROM REIMBURSEMENT WHERE r_status = 'Approved';
-------------------------------------------------------------------------
--Manager deny 
CREATE OR REPLACE PROCEDURE denied (re_id int, manager_id int)
AS
BEGIN
UPDATE REIMBURSEMENT set r_status = 'Denied' WHERE r_id = re_id;
UPDATE REIMBURSEMENT set r_resolverId = manager_id WHERE r_id = re_id;
COMMIT;
END;
/
-------------------------------------------------------------------------
--SEQUENCE USED FOR AUTOINCREMENT  
CREATE SEQUENCE CUSTOMER_SEQ
  START WITH 1
  INCREMENT BY 1;
--HASHING FUNCTION THAT COMBINES USERNAME, PASSWORD AND A SPECIAL WORD  
CREATE OR REPLACE FUNCTION GET_EMPLOYEE_HASH(USERNAME VARCHAR2, PASSWORD VARCHAR2) RETURN VARCHAR2
IS
EXTRA VARCHAR2(10) := 'SALT';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || PASSWORD || EXTRA)));
END;
/

CREATE OR REPLACE FUNCTION GET_EMPLOYEE_HASH(PASSWORD VARCHAR2) RETURN VARCHAR2
IS
EXTRA VARCHAR2(10) := 'SALT';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => PASSWORD || EXTRA)));
END;
/
 -- INPUT => UTL_I18N.STRING_TO_RAW(DATA => USERNAME || PASSWORD || EXTRA)));
--EXTRA VARCHAR2(10) := 'SALT';
--TRIGGET THAT GETS NEXT SEQUENCE VALUE FOR ID AND HASHES BLANK PASSWORD
drop trigger employee_b_insert;
CREATE OR REPLACE TRIGGER EMPLOYEE_B_INSERT
BEFORE INSERT
ON EMPLOYEE
FOR EACH ROW
BEGIN
  SELECT GET_EMPLOYEE_HASH(:NEW.E_PASSWORD) INTO :NEW.E_PASSWORD FROM DUAL;
END;
/
-------------------
ReimbursementProject
admin
p4ssw0rd
ORCLProj
1521
reimbursementproject.cskfdvs8fiur.us-east-1.rds.amazonaws.com

-------------------