-- DDL --
drop public synonym users;
drop public synonym reimbursements;
drop public synonym new_reimbursement_id;
drop public synonym new_transaction_id;
drop public synonym delete_user;
drop public synonym encrypt_password;
drop table account_transactions;
drop table reimbursements;
drop table users;
drop sequence new_user_id;
drop sequence new_reimbursement_id;
drop sequence new_transaction_id;
drop user super;
drop user brock;

create sequence new_reimbursement_id
minvalue 1
start with 1
increment by 1
nocache;

create sequence new_user_id
minvalue 1
start with 1
increment by 1
nocache;

-- DDL --
create table users
(   
    username varchar(25),
    email varchar(50) not null,
    password varchar(150) not null,
    superuser number not null,
    --
    constraint username_pk primary key(username),
    constraint unq_email unique (email)
); -- for tables, you put ;

create table reimbursements
(
    reimbursement_id number not null,
    username varchar(25) not null,
    amount Decimal(13,4) default(0) not null,
    status varchar(15) default('pending') not null,
    approving_manager varchar(25) default('n/a'),
    image number default(0),
    --
    constraint reimbursement_id_pk primary key(reimbursement_id)
); -- for tables, you put ;

--create table account_transactions
--(
--    transaction_id number not null,
--    account_id number not null,
--    user_id number not null,
--    amount Decimal(13,4) default(0) not null,
--    --
--    constraint transaction_id_pk primary key(transaction_id),
--    constraint another_user_id_fk foreign key (user_id) references login(user_id) on delete cascade
--); -- for tables, you put ;

create or replace procedure delete_user(input_username varchar)
as
begin
delete from users where username = input_username;
commit;-- saves changes
end;
/

--hashing function that combines password and extra word get_customer_hash(?) ?
--taken from yuvi's notes
create or replace function encrypt_password(username varchar, password varchar) return varchar
is
extra varchar(10) := '0L2di59Fw7';
begin
    return to_char(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => username || password || extra)));
end;
/


insert into users values( 'super','fuksyr@gmail.com',encrypt_password('super', 'superpass'), 25);
create user super identified by superpass;
grant dba to super with admin option;

---- TCL --
create public synonym users for bsokevitz.users;
create public synonym reimbursements for bsokevitz.reimbursements;
create public synonym new_reimbursement_id for bsokevitz.new_reimbursement_id;
--create public synonym new_transaction_id for bsokevitz.new_transaction_id;
create public synonym delete_user for bsokevitz.delete_user;
create public synonym encrypt_password for bsokevitz.encrypt_password;

commit;