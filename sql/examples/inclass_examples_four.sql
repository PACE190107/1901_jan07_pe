--DDL
--create a table with no primary key and then add data
--the can we add primary key? - No
create table Player 
(
    name varchar2(20), 
    position varchar2(20)
);

--DML
insert into Player values ('Peyton Manning','QB');
--DQL
select * from Player;
--DDL
--can't do this
alter table Player add player_id number(10) primary key;
--other disadvantage of not having primary key?
--can have duplicate records 
insert into Player values ('Peyton Manning','QB');
truncate table Player;
desc Player;
--after removing data should allow adding primary key
--insert into Player (player_id, name, position)
--values(1,'name','position');
insert into Player values ('Peyton Manning','QB',1);
insert into Player values ('Steph Curry','QB',2);
insert into Player values ('Dwayne Johnson','QB',3);
insert into Player values ('Kevin Hart','QB',4);
insert into Player values ('Terry Crews','QB',5);
insert into Player values ('Dennis Rodman','QB',6);
insert into Player values ('OJ Simpson','QB',7);
insert into Player values ('Joe Montana','QB',8);
insert into Player values ('Dan Marion','QB',9);
insert into Player values ('Brett Favre','QB',10);
insert into Player values ('Bugs Bunny','QB',11);
insert into Player values ('','QB',12);

select * from Player;
--total number of records in a table
select count(*) from Player;
select name, position from Player;
select name, position from Player where NAME like 'B%';
select name, position from Player where NAME like 'b%';
SELECT NAME, POSITION FROM PLAYER WHERE NAME LIKE 'B%';
select * from Player where PLAYER_ID >= 7;
select * from Player where PLAYER_ID = 7;
--select * from Player where name is 'Bugs Bunny';
select * from Player where name is not null;
select * from Player where name is null;
-- delete the records for which name is null
delete from player where name is null;
select count(*) from Player;
--how to modify the constraint of a column in a table
-- unlike primary key we could apply not null to a 
-- table when there is already in it.
alter table Player modify name Not null;
-- remove a constraint
alter table Player modify name null;

create table teams(
id number(10) primary key,
team_name varchar2(40) not null,
city varchar2(30)
);
desc player;
alter table player add team_id number (10);
alter table player 
add foreign key (team_id) references teams(id);

insert into teams values(1, 'Broncos', 'Denver');
insert into teams values(2, 'Packers', 'GreenBay');
insert into teams values(3, 'Rockets', 'Houston');
insert into teams values(4, 'Steelers', 'Pittsburgh');
insert into teams values(5, 'Stampeders', 'Calgary');
insert into teams values(6, 'Eagles', 'Philadelphia');
insert into teams values(7, 'Fever', 'Indianapollis');
insert into teams values(8, 'Wild', 'Minnesota');
insert into teams values(9, 'Devils', 'New Jersey');

select * from teams;
select * from player;
--how to update records in a table? 
update player set TEAM_ID = 1 where name = 'Bugs Bunny';
update player set TEAM_ID = 8 where name = 'Peyton Manning';
update player set TEAM_ID = 2 where name = 'Steph Curry';
update player set TEAM_ID = 3 where name = 'Dwayne Johnson';

--alias
select * from players p where p.id >= 5;

create table player_details(
    id number (10),
    address varchar2(40),
    salary number(30),
    mvp varchar2(40), 
    team_id number(10) references teams(id)
);
alter table player_details modify id number(10) primary key;
alter table player_details add foreign key(id) 
references player(player_id);

insert into player_details values (1, '1 main st, Denver', 10000000000, '2013', 5);
insert into player_details values (2, '1 main st, Okland', 20000000000, '2016', 4);
insert into player_details values (3, '1 main st, SFO', 30000000000, '2008', 2);
insert into player_details values (4, '1 main st, Washington DC', 40000000000, '2013', 3);
insert into player_details values (5, '1 main st, New York', 50000000000, '2012', 1);
insert into player_details values (6, '1 main st, Raleigh', 60000000000, '2014', 1);
commit;

select * from player;   --11 records player id 1 to 11
select * from teams;    --9 records - team id 1 to 9
select * from player_details;   --6 records - player id 1 to 5

--inner join
--aliases used typically to avoid retyping the table names
select * from player p join player_details pd on p.PLAYER_ID = pd.id;  --6

--9 on the left have values & 6 on the right have values 
select * from teams left join PLAYER_DETAILS 
on teams.ID = PLAYER_DETAILS.TEAM_ID
order by teams.TEAM_NAME
;

--total 6
--left with values -6 & right with values -6 
select * from teams right join PLAYER_DETAILS 
on teams.id = PLAYER_DETAILS.TEAM_ID
order by teams.TEAM_NAME
;

--full join
--total 9
--left with values 9 & right with values 6
select * from teams full join PLAYER_DETAILS 
on teams.id = PLAYER_DETAILS.TEAM_ID
order by teams.TEAM_NAME
;
--player 11, teams 9, player details 6
--number of players part of team - 4
--number teams that have player - 5
--number of players who has player details - 6
select * from teams 
    join player 
on player.TEAM_ID = teams.ID
    inner join PLAYER_DETAILS
on player.PLAYER_ID = PLAYER_DETAILS.ID
order by teams.ID;


select count(teams.city), teams.city from teams 
    join player 
on player.TEAM_ID = teams.ID
    inner join PLAYER_DETAILS
on player.PLAYER_ID = PLAYER_DETAILS.ID
group by teams.city
order by teams.city
;

select count(player.POSITION), player.POSITION from player join teams 
    on player.team_id = teams.id
group by player.POSITION 
order by player.POSITION;

-- set operators
create table tablea (id number(10), name varchar2(20));
create table tableb (id number(10), name varchar2(20));
create table tablec (id number(10), name varchar2(20));

insert into tablea values(1,'a');
insert into tablea values(2,'b');
insert into tablea values(3,'c');
insert into tableb values(1,'a');
insert into tableb values(3,'c');
insert into tableb values(4,'d');
insert into tableb values(5,'e');
insert into tablec values(3,'c');
insert into tablec values(4,'d');
insert into tablec values(5,'e');
insert into tablec values(6,'f');
insert into tablec values(7,'g');
insert into tablec values(8,'h');

select * from tablea union select * from tableb;    --1,2,3,4,5
select * from tablea union select * from tablec;    --1 thru 8
select * from tableb union select * from tablec;    --1 thru 8 except 2

select * from tablea union all select * from tableb;    --1,2,3,1,3,4,5
select * from tablea union all select * from tablec;    --1,2,3,3,4,5,6,7,8
select * from tableb union all select * from tablec;    --1,3,4,5,3,4,5,6,7,8

select * from tablea intersect select * from tableb;    --1,3
select * from tablea intersect select * from tablec;    --3
select * from tableb intersect select * from tablec;    --3,4,5

select * from tablea minus select * from tableb;    --2
select * from tablea minus select * from tablec;    --1,2
select * from tableb minus select * from tablec;    --1
select * from tableb minus select * from tablea;    --4,5
select * from tablec minus select * from tablea;    --4,5,6,7,8
select * from tablec minus select * from tableb;    --6,7,8

--procedural languages

--function
create or replace function multiply(x number, y number) 
return number as results number;
begin
    results := x * y;
    return results;
end;
/
--dual is one of the temporary tables in oracle
select * from dual; 
--approach 1
select multiply(3434,98797) from dual;
--approach 2
declare
    myResults number;
begin
    myResults := multiply(10,20);
    DBMS_OUTPUT.PUT_LINE(myResults);
end;
/

--stored procedure
create or replace procedure printMe(message varchar2)
as 
begin
    DBMS_OUTPUT.PUT_LINE(message);
end;
/

exec printMe('is this going to work?');
exec printMe('yes, this does work');

create or replace procedure deleteAllRecordsFromTableA
as 
begin
    delete from (select * from tablea where tablea.id > = 5);
    --delete from tablea where tablea.id > = 5;
end;
/
exec DELETEALLRECORDSFROMTABLEA;

select * from tablea;
insert into tablea values(1,'a');
insert into tablea values(2,'b');
insert into tablea values(3,'c');
insert into tablea values(4,'a');
insert into tablea values(5,'b');
insert into tablea values(6,'c');
insert into tablea values(7,'a');
insert into tablea values(8,'b');
insert into tablea values(9,'c');
insert into tablea values(10,'c');
insert into tablea values(11,'c');

rollback;
--approach 2
begin
    DELETEALLRECORDSFROMTABLEA;
end;
/

savepoint pm_1172019418;

rollback to pm_1172019418;
--tcl -- savepoint, rollback, commit
commit;
rollback to pm_1172019418;

--reestablish your connection to the user which has employee table
select * from EMPLOYEE_1901;
select concat(first_name, last_name) as fullname from employee_1901;
select concat(substr(first_name, 1,2), last_name) as fullname from employee_1901;
select concat(upper(substr(first_name, 1,2)), last_name) as fullname 
from employee_1901;

create or replace procedure getNames(YuviCursor out sys_refcursor)
as
begin
    open YuviCursor for select concat(upper(substr(first_name, 1,2)), last_name) as fullname 
    from employee_1901;
end;
/

declare 
    myvariable Sys_Refcursor;
    fullName varchar2(40);
begin
    getNames(myvariable);
    Loop
        Fetch myvariable into fullName;
        exit when myvariable%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Employee name is ' || fullName);
    End Loop;
end;
/

create view innerJoinExample as 
select count(teams.city), teams.city  from teams 
    join player 
on player.TEAM_ID = teams.ID
    inner join PLAYER_DETAILS
on player.PLAYER_ID = PLAYER_DETAILS.ID
group by teams.city
order by teams.city
;

