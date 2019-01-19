DROP TABLE transactions;
DROP TABLE bank_accounts;
DROP TABLE user_accounts;
DROP PUBLIC SYNONYM transactions;
DROP PUBLIC SYNONYM bank_accounts;
DROP PUBLIC SYNONYM user_accounts;
DROP PUBLIC SYNONYM insert_user;
DROP PUBLIC SYNONYM update_user;
DROP PUBLIC SYNONYM delete_user;
DROP PUBLIC SYNONYM insert_account;
DROP SEQUENCE user_id_seq;
DROP SEQUENCE account_id_seq;

CREATE SEQUENCE user_id_seq
    START WITH 10000000;
    /
    
CREATE SEQUENCE account_id_seq
    START WITH 10000000;
    /

CREATE TABLE user_accounts
(
    user_id NUMBER PRIMARY KEY,
    user_username VARCHAR(20) UNIQUE NOT NULL,
    user_password VARCHAR(20) NOT NULL
);
CREATE PUBLIC SYNONYM user_accounts
FOR bankadmin.user_accounts;
/

CREATE TABLE bank_accounts
(
    account_id NUMBER PRIMARY KEY,
    user_id NUMBER NOT NULL,
    account_type VARCHAR(10),
    account_balance NUMBER(*, 2),
    CONSTRAINT account_fk
        FOREIGN KEY (user_id)
        REFERENCES User_Accounts (user_id)
);
/
CREATE PUBLIC SYNONYM bank_accounts
    FOR bankadmin.bank_accounts;

CREATE TABLE transactions
(
    transaction_date TIMESTAMP(4) NOT NULL,
    account_id NUMBER NOT NULL,
    transaction_type VARCHAR(8),
    transaction_amount NUMBER(*, 2),
    transaction_balance NUMBER(*, 2),
    CONSTRAINT transaction_fk
        FOREIGN KEY (account_id)
        REFERENCES Bank_Accounts (account_id)
);
/
CREATE PUBLIC SYNONYM transactions
    FOR bankadmin.transactions;

CREATE OR REPLACE PROCEDURE insert_user
    (in_username IN VARCHAR, in_password IN VARCHAR, p_success OUT INTEGER)
    AUTHID CURRENT_USER AS
    BEGIN
        INSERT INTO user_accounts VALUES(NULL, in_username, in_password);
        p_success := SQL%ROWCOUNT;
        EXECUTE IMMEDIATE ('CREATE USER '||in_username||' IDENTIFIED BY '||in_password);
        EXECUTE IMMEDIATE ('GRANT CREATE SESSION TO '||in_username);
        EXECUTE IMMEDIATE ('GRANT EXECUTE ON insert_account TO '||in_username);
        EXECUTE IMMEDIATE ('GRANT SELECT,UPDATE,DELETE ON user_accounts TO '||in_username);
        EXECUTE IMMEDIATE ('GRANT INSERT,SELECT,UPDATE,DELETE ON bank_accounts TO '||in_username);
        EXECUTE IMMEDIATE ('GRANT INSERT,SELECT,UPDATE,DELETE ON transactions TO '||in_username);
        EXECUTE IMMEDIATE ('GRANT SELECT ON user_id_seq TO '||in_username);
        EXECUTE IMMEDIATE ('GRANT SELECT ON account_id_seq TO '||in_username);
        COMMIT;
    END;
    /
CREATE PUBLIC SYNONYM insert_user
    FOR bankadmin.insert_user;

CREATE OR REPLACE PROCEDURE update_user
    (in_username IN VARCHAR, in_old_password IN VARCHAR, in_new_password IN VARCHAR, p_success OUT INTEGER)
    AUTHID CURRENT_USER AS
    BEGIN
        UPDATE user_accounts 
            SET user_password = in_new_password
            WHERE user_username = in_username
            AND user_password = in_old_password;
        p_success := SQL%ROWCOUNT;
        EXECUTE IMMEDIATE ('ALTER USER '||in_username||' IDENTIFIED BY '||in_new_password);
        COMMIT;
    END;
    /
CREATE PUBLIC SYNONYM update_user
    FOR bankadmin.update_user;

CREATE OR REPLACE PROCEDURE delete_user
    (in_username IN VARCHAR, in_password IN VARCHAR, p_success OUT INTEGER)
    AUTHID CURRENT_USER AS
    BEGIN
        DELETE FROM user_accounts WHERE user_username = in_username AND user_password = in_password;
        p_success := SQL%ROWCOUNT;
        EXECUTE IMMEDIATE ('DROP USER '||in_username);
        COMMIT;
    END;
    /
CREATE PUBLIC SYNONYM delete_user
FOR bankadmin.delete_user;

CREATE OR REPLACE PROCEDURE insert_account
    (user_id IN NUMBER, account_type IN VARCHAR, account_balance IN NUMBER, p_success OUT INTEGER)
    AUTHID CURRENT_USER AS
    BEGIN
        INSERT INTO bank_accounts VALUES (NULL, user_id, account_type, account_balance);
        SELECT MAX(account_id) INTO p_success FROM bank_accounts;
        COMMIT;
    END;
    /
CREATE PUBLIC SYNONYM insert_account
    FOR bankadmin.insert_account;

CREATE OR REPLACE TRIGGER user_insert_trigger
    BEFORE INSERT
    ON user_accounts
    FOR EACH ROW
    BEGIN
        IF :NEW.user_id IS NULL THEN
            SELECT user_id_seq.NEXTVAL INTO :NEW.user_id FROM DUAL;
        END IF;
    END;
    /

CREATE OR REPLACE TRIGGER account_insert_trigger
    BEFORE INSERT
    ON bank_accounts
    FOR EACH ROW
    BEGIN
        IF :NEW.account_id IS NULL THEN
            SELECT account_id_seq.NEXTVAL INTO :NEW.account_id FROM DUAL;
        END IF;
    END;
    /
    
COMMIT;