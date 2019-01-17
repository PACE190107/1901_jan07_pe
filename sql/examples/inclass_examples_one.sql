--data types
-- varchar, nvarchar, 
-- varch2, nvarchar2, char,
-- timestamp, date, 
-- number, float, long, double, 
-- blob, clob

    --char will utilize space fully,
    -- meaning will utilize 50 characters
    -- or bytes irrespective of number of characters used
    -- try to avoid using char
--DDL
drop table employee_1901;

--DDL
create table employee_1901
(
    emp_id number not null,
    first_name varchar(50),
    last_name varchar(50),
    constraint emp_id_pk primary key(emp_id)
);
--DML
--1st approach 
insert into EMPLOYEE_1901 values(1, 'Dak', 'Prescott');
--2nd approach
insert into EMPLOYEE_1901 (FIRST_NAME, last_name, emp_id) 
values('Tom','Brady',2);
insert into EMPLOYEE_1901 values(3, 'Jered', 'Goff');
insert into EMPLOYEE_1901 values(4, 'Patrick', 'Mahommes');
insert into EMPLOYEE_1901 values(5, 'Andrew', 'Luck');
insert into EMPLOYEE_1901 values(6, 'Tim', 'Tebow');
insert into EMPLOYEE_1901 values(7, 'Bobby', 'Busche');
insert into EMPLOYEE_1901 values(8, 'Drew', 'Brees');
insert into EMPLOYEE_1901 values(9, 'Michael', 'Thomas');
insert into EMPLOYEE_1901 values(10, 'Nick', 'Foles');

--DQL
select * from EMPLOYEE_1901;

--DML
--delete from EMPLOYEE_1901;

--TCL
commit;


--sql is not case sensitive
--however, values in database are case sensitive

