--Drop existing objects
drop table bank_accounts;
drop table user_details;
drop sequence user_seq;
drop sequence account_seq;
drop user MTRAYNOR;
drop PUBLIC SYNONYM User_Details;
drop PUBLIC SYNONYM Bank_Accounts;
drop PUBLIC SYNONYM user_seq;
drop PUBLIC SYNONYM account_seq;
drop PUBLIC SYNONYM register_user;
drop PUBLIC SYNONYM create_account;

--User table
CREATE TABLE User_Details
(
    user_id number(10) not null,
    first_name varchar2(20),
    last_name varchar2(20),
    user_name varchar(30),
    user_password varchar2(100),
    CONSTRAINT user_id_PK PRIMARY KEY (user_id),
    CONSTRAINT user_name_UNQ UNIQUE (user_name)
);

--Accounts table
CREATE TABLE Bank_Accounts
(
    u_id number(10) references User_Details(user_id),
    account_id number(10) not null,
    account_type varchar2(20),
    balance decimal(18,2),
    CONSTRAINT account_id_PK PRIMARY KEY (account_id)
);

--Generate auto-sequence for user_ID
CREATE SEQUENCE user_seq
    START WITH 100
    INCREMENT BY 1;
    
--Generate auto-sequence for account_ID
CREATE SEQUENCE account_seq
    START WITH 100
    INCREMENT BY 7;    

--Trigger to initiate user sequence
CREATE OR REPLACE TRIGGER new_user
BEFORE INSERT
    ON User_Details
FOR EACH ROW
BEGIN
    --increment sequence
    IF :new.user_id IS NULL THEN
        SELECT user_seq.NEXTVAL INTO :new.user_id FROM dual;
    END IF;
END;
/

--Trigger to initiate account sequence
CREATE OR REPLACE TRIGGER new_account
BEFORE INSERT
    ON Bank_Accounts
FOR EACH ROW
BEGIN
    --increment sequence
    IF :new.account_id IS NULL THEN
        SELECT account_seq.NEXTVAL INTO :new.account_id FROM dual;
    END IF;
END;
/

--Stored procedure to register a new user
CREATE OR REPLACE PROCEDURE register_user
(
    firstname varchar2, lastname varchar2, username varchar2, password varchar2, rows out number
)
AS
BEGIN
    INSERT INTO User_Details VALUES(null, firstname, lastname, username, password);
    rows := sql%rowcount;
    COMMIT;
END;
/

--Stored procedure to generate a new bank account
CREATE OR REPLACE PROCEDURE create_account
(
    uID number, acctype varchar2, accbalance decimal, rows out number
)
AS
BEGIN
    INSERT INTO Bank_Accounts VALUES(uID, null, acctype, accbalance);
    rows := sql%rowcount;
    COMMIT;
END;
/

--Synonyms
CREATE PUBLIC SYNONYM User_Details FOR MTRevature.User_Details;
CREATE PUBLIC SYNONYM Bank_Accounts FOR MTRevature.Bank_Accounts;
CREATE PUBLIC SYNONYM user_seq FOR MTRevature.user_seq;
CREATE PUBLIC SYNONYM account_seq FOR MTRevature.account_seq;
CREATE PUBLIC SYNONYM register_user FOR MTRevature.register_user;
CREATE PUBLIC SYNONYM create_account FOR MTRevature.create_account;

commit;
select * from user_details;
select * from bank_accounts;