--how to find all tables? 
select * from all_tables;
--count of all system tables
select count(*) from all_tables;
select * from all_tables where table_name = 'EMPLOYEE_1901';

select * from all_tables where table_name like 'U%';
select * from all_tables where table_name like 'USER$';

select * from USER$;

--how to drop user?
drop user programmer;
--how to create a new user? 
create user programmer identified by p4ssw0rd;
--grant user permission
grant connect, resource to programmer;
--grant admin permission
grant DBA to programmer with admin option;
commit;

--how to see the attribute information in a table?
--how to describe a table? 
desc customer;
--dual is temporary table
desc dual;
select * from dual;
--desc at the begining of sql means describe
--desc at the end of sql means descending

desc system_privilege_map;
select * from SYSTEM_PRIVILEGE_MAP;


