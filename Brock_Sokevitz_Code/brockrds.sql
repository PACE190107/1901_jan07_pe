-- DDL --
drop public synonym bank_accounts;
drop public synonym bank_users;
drop public synonym account_transactions;
drop public synonym new_user_id;
drop public synonym new_account_id;
drop public synonym new_transaction_id;
drop public synonym delete_user;
drop public synonym encrypt_password;
drop table account_transactions;
drop table bank_accounts;
drop table bank_users;
drop sequence new_user_id;
drop sequence new_account_id;
drop sequence new_transaction_id;
drop user super;
drop user brock;

create sequence new_user_id
minvalue 1
start with 1
increment by 1
nocache;

create sequence new_account_id
minvalue 1
start with 1
increment by 1
nocache;

create sequence new_transaction_id
minvalue 1
start with 1
increment by 1
nocache;

-- DDL --
create table bank_users
(
    user_id number not null,
    username varchar(15) not null,
    password varchar(50) not null,
    superuser number not null,
    --
    constraint user_pass_pk primary key(user_id),
    constraint unq_username unique (username)
); -- for tables, you put ;

create table bank_accounts
(
    account_id number not null,
    user_id number not null,
    account_type varchar(15) not null,
    balance Decimal(13,4) default(0) not null,
    --
    constraint account_id_pk primary key(account_id),
    constraint user_id_fk foreign key (user_id) references bank_users(user_id)
); -- for tables, you put ;

create table account_transactions
(
    transaction_id number not null,
    account_id number not null,
    user_id number not null,
    amount Decimal(13,4) default(0) not null,
    --
    constraint transaction_id_pk primary key(transaction_id),
    constraint another_user_id_fk foreign key (user_id) references bank_users(user_id),
    constraint account_id_fk foreign key (account_id) references bank_accounts(account_id)
); -- for tables, you put ;

create or replace procedure delete_user(userid integer)
as
begin
delete from bank_users where user_id = userid;
commit;-- saves changes
end;
/

--hashing function that combines password and extra word get_customer_hash(?) ?
--taken from yuvi's notes
create or replace function encrypt_password(username varchar, password varchar) return varchar
is
extra varchar(10) := 'friendship';
begin
    return to_char(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => username || password || extra)));
end;
/


insert into bank_users values(new_user_id.nextval,'super', encrypt_password('super', 'superpass'), 25);
create user super identified by superpass;
grant dba to super with admin option;

---- TCL --
create public synonym bank_accounts for bsokevitz.bank_accounts;
create public synonym bank_users for bsokevitz.bank_users;
create public synonym account_transactions for bsokevitz.account_transactions;
create public synonym new_user_id for bsokevitz.new_user_id;
create public synonym new_account_id for bsokevitz.new_account_id;
create public synonym new_transaction_id for bsokevitz.new_transaction_id;
create public synonym delete_user for bsokevitz.delete_user;
create public synonym encrypt_password for bsokevitz.encrypt_password;

commit;