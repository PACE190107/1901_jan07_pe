create table BankUsers(
    user_id number,
    firstname varchar2(40) not null,
    lastname varchar2(40) not null,
    username varchar2(40) not null,
    pass varchar2(40) not null,
    constraint PK_USER primary key (user_id),
    constraint UNQ_USER unique (username)
);

create table Accounts(
    account_id number,
    user_id number not null,
    account_type varchar2(40),
    balance number not null,
    constraint PK_ID primary key (account_id)
);

create table Transactions(
    transaction_id number,
    account_id number not null,
    amount number not null,
    constraint PK_TRANSACTIONS primary key (transaction_id)
);

--alter table Accounts add foreign key (user_id) references BankUsers(user_id);
--alter table Transactions add foreign key (account_id) references Accounts(account_id);
alter table Accounts add constraint cascadeDeleteAccounts foreign key (user_id) references BankUsers(user_id) on delete cascade;
alter table Transactions add constraint cascadeDeleteTransactions foreign key (account_id) references Accounts(account_id) on delete cascade;
commit;

--insert into Accounts values(1,0,0);
--insert into BankUsers values(1,1,'fname','lname','uname','pw');

--drop table BankUsers;
--drop table Accounts;
--drop table Transactions;
--commit;

-- Sequences used for auto-increment:  
CREATE SEQUENCE USER_SEQ
  START WITH 1
  INCREMENT BY 1;
  
CREATE SEQUENCE ACCOUNT_SEQ
  START WITH 1
  INCREMENT BY 1;

CREATE SEQUENCE TRANSACTION_SEQ
  START WITH 1
  INCREMENT BY 1;
  
--drop sequence user_seq;
  
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
create or replace trigger USER_B_INSERT
BEFORE INSERT
ON BankUsers
FOR EACH ROW
BEGIN
  /* INCREASE THE SEQUENCE */
  IF :NEW.user_id IS NULL THEN
    SELECT USER_SEQ.NEXTVAL INTO :NEW.user_id FROM DUAL;
  END IF;
  
  /* SAVE HASH INSTEAD OF PASSWORD */
  SELECT GET_USER_HASH(:NEW.username,:NEW.pass) INTO :NEW.pass FROM DUAL;
END;
/

--TRIGGER THAT GETS NEXT SEQUENCE VALUE FOR Account ID
create or replace trigger Account_B_insert
BEFORE INSERT
ON Accounts
FOR EACH ROW
BEGIN
  /* INCREASE THE SEQUENCE */
  IF :NEW.account_id IS NULL THEN
    SELECT ACCOUNT_SEQ.NEXTVAL INTO :NEW.account_id FROM DUAL;
  END IF;
END;
/

--TRIGGER THAT GETS NEXT SEQUENCE VALUE FOR Transaction ID
create or replace trigger Transaction_B_insert
BEFORE INSERT
ON Transactions
FOR EACH ROW
BEGIN
  /* INCREASE THE SEQUENCE */
  IF :NEW.transaction_id IS NULL THEN
    SELECT TRANSACTION_SEQ.NEXTVAL INTO :NEW.transaction_id FROM DUAL;
  END IF;
END;
/

-- Stored procedure to insert user
CREATE OR REPLACE PROCEDURE INSERT_USER(
firstname in VARCHAR2, lastname in VARCHAR2, username in VARCHAR2, pass in VARCHAR2, resultCount out number)
AS
    beforeCount number;
    afterCount number;
BEGIN
    select count (*) into beforeCount from BankUsers;
  insert into BankUsers values(null, firstname, lastname, username, pass);
  commit;
    select count (*) into afterCount from BankUsers;
    resultCount := afterCount - beforeCount;
END;
/


-- Stored procedure to create account
CREATE OR REPLACE PROCEDURE Create_Account(user_id in number, account_type in varchar2, resultCount out number)
AS
    beforeCount number;
    afterCount number;
BEGIN
    select count (*) into beforeCount from BankUsers;
  insert into Accounts values(null, user_id, account_type, 0);
  commit;
    select count (*) into afterCount from BankUsers;
    resultCount := afterCount - beforeCount;
END;
/

-- Stored procedure to create a transaction log
CREATE OR REPLACE PROCEDURE Log_Transaction(account_id in varchar2, amount in number, resultCount out number)
AS
    beforeCount number;
    afterCount number;
BEGIN
    select count (*) into beforeCount from BankUsers;
  insert into Transactions values(null, account_id, amount);
  commit;
    select count (*) into afterCount from BankUsers;
    resultCount := afterCount - beforeCount;
END;
/

-- Stored procedure to verify a password
create or replace procedure verify_password(username in varchar2, pass in varchar2, verified out number)
as
begin
    select count(pass) into verified from BankUsers where BankUsers.username = username and BankUsers.pass = get_User_Hash(username,pass);
    --select pass into tempPW from BankUsers where BankUsers.username = username;
    --tempPW2 := GET_USER_HASH(username,pass);
    --if tempPW = tempPW2 then verified := 1;
    --else verified := 0;
    --end if;
end;
/

select count(pass) from BankUsers where BankUsers.username = 'm' and BankUsers.pass = get_User_Hash('m','c');

select firstname from BankUsers where BankUsers.user_id = 1;

insert into Accounts values(1,62,'checking',0);
insert into Accounts values(null,62,'savings',0);
insert into BankUsers values(null, 'm', 'c', 'm', 'c');
insert into BankUsers values(null, 'm', 'c', 'm_coon', 'p_word');
insert into BankUsers values(null, 'phoebe', 'elizabeth','audelia','hemsworth');
select * from Accounts;
select * from BankUsers;

--update Accounts set balance = 10.0 + balance where user_id = 21 and account_type = 'checking';

--update Accounts set balance = -10.0 + balance where user_id = 21 and account_type = 'checking';

--select * from Accounts where User_ID = 62 and account_Type = 'checking';
--select count(user_ID) from Accounts where Accounts.user_ID = 62 and Accounts.account_type = 'checking';
--select count(pass) from BankUsers where BankUsers.username = 'm' and BankUsers.pass = get_User_Hash('m', 'c');
--select count(pass) from BankUsers where BankUsers.username = 'c' and BankUsers.pass = get_User_Hash('c', 'g');

--delete from Accounts where Accounts.account_id = 1;
--delete from BankUsers where BankUsers.username = 'm';
--delete from BankUsers where BankUsers.user_id = 21;
--delete from BankUsers;
--delete from BankUsers where BankUsers.user_id = 63;

--create or replace procedure Add_To_Account(account_id in varchar2, amount in number)
--as
--begin 
--select balance from Accounts where Accounts.account_id = account_id;
--end;
--/

--commit;