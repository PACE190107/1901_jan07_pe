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

select * from player;
select * from teams;
select * from player_details;

--inner join
--aliases used typically to avoid retyping the table names
select * from player p join player_details pd on p.id = pd.id;

