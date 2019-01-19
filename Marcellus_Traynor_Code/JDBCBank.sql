--User table
drop table user_details;
drop sequence user_seq;

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

--Generate auto-sequence for user_ID
CREATE SEQUENCE user_seq
    START WITH 100
    INCREMENT BY 1;
    
--Encrypt passwords
--CREATE OR REPLACE FUNCTION encrypt_user(username varchar2, password varchar2)
--    return varchar2
--IS
--    extra varchar2(10) := 'S and P';
--BEGIN
--    RETURN to_char(dbms_obfuscation_toolkit.MD5
--    (
--        INPUT => UTL_I18N.string_to_raw(DATA => username || password || extra)
--    ));
--END;
--/

--Trigger to initiate sequence and encryption
CREATE OR REPLACE TRIGGER new_user
BEFORE INSERT
    ON User_Details
FOR EACH ROW
BEGIN
    --increment sequence
    IF :new.user_id IS NULL THEN
        SELECT user_seq.NEXTVAL INTO :new.user_id FROM dual;
    END IF;
    
    --save encrypted passwords
--    SELECT encrypt_user(:new.user_name, :new.user_password) 
--    INTO :new.user_password FROM dual;
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

commit;
--drop function encrypt_user;
select * from user_details;