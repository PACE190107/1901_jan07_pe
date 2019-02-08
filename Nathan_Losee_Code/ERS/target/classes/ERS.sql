DECLARE
    CURSOR user_list is SELECT e_username FROM employees;
    num_employees INTEGER;
    username VARCHAR(20);
BEGIN
    OPEN user_list;
    SELECT COUNT(*) INTO num_employees FROM employees;
    FOR i IN 1..num_employees LOOP
        FETCH user_list INTO username;
        EXIT WHEN user_list%NOTFOUND;
        EXECUTE IMMEDIATE ('DROP USER '||username);
    END LOOP;
    CLOSE user_list;
    COMMIT;
END;
/

DROP SEQUENCE e_id_seq;
DROP SEQUENCE rr_id_seq;
DROP TABLE reimbursement_requests;
DROP TABLE employees;

CREATE SEQUENCE e_id_seq
    START WITH 10000000;
    /

CREATE SEQUENCE rr_id_seq
    START WITH 10000000;
    /

CREATE TABLE employees
(
    e_id INTEGER PRIMARY KEY,
    e_username VARCHAR(20) UNIQUE NOT NULL,
    e_password CHAR(30) NOT NULL,
    e_first_name VARCHAR(20) NOT NULL,
    e_last_name VARCHAR(20) NOT NULL,
    e_email VARCHAR(30) NOT NULL,
    is_manager CHAR(1) NOT NULL,
    is_confirmed CHAR(1) NOT NULL
);

CREATE TABLE reimbursement_requests
(
    rr_id INTEGER PRIMARY KEY,
    e_id INTEGER,
    m_id INTEGER,
    rr_description VARCHAR(200),
    rr_amount NUMBER(10,2) NOT NULL,
    is_approved CHAR(1),
    CONSTRAINT rr_fk_employee
        FOREIGN KEY (e_id)
        REFERENCES employees (e_id)
        ON DELETE CASCADE,
    CONSTRAINT rr_fk_manager
        FOREIGN KEY (m_id)
        REFERENCES employees (e_id)
        ON DELETE CASCADE
);
    
CREATE OR REPLACE FUNCTION get_employee_hash(in_e_password VARCHAR2) RETURN VARCHAR2
IS
extra VARCHAR2(10) := 'SALT';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(
  INPUT => UTL_I18N.STRING_TO_RAW(DATA => in_e_password || extra)));
END;
/

CREATE OR REPLACE PROCEDURE insert_employee
    (in_username IN VARCHAR, in_password IN VARCHAR, in_first_name IN VARCHAR, in_last_name IN VARCHAR,
    in_email IN VARCHAR, in_is_manager CHAR, p_success OUT INTEGER)
    AUTHID CURRENT_USER AS
    BEGIN
        INSERT INTO employees VALUES(NULL, in_username, 'PW'||SUBSTR(get_employee_hash(in_password),1,28), in_first_name, in_last_name, in_email, in_is_manager, 'f');
        p_success := SQL%ROWCOUNT;
        IF in_is_manager = 'f' THEN
            EXECUTE IMMEDIATE ('CREATE USER '||in_username||' IDENTIFIED BY PW'||SUBSTR(get_employee_hash(in_password),1,28));
            EXECUTE IMMEDIATE ('GRANT CREATE SESSION TO '||in_username);
            EXECUTE IMMEDIATE ('GRANT EXECUTE ON update_employee TO '||in_username);
            EXECUTE IMMEDIATE ('GRANT EXECUTE ON insert_request TO '||in_username);
            EXECUTE IMMEDIATE ('GRANT EXECUTE ON get_employee_hash TO '||in_username);
            EXECUTE IMMEDIATE ('GRANT SELECT,UPDATE ON employees TO '||in_username);
            EXECUTE IMMEDIATE ('GRANT INSERT,SELECT ON reimbursement_requests TO '||in_username);
            EXECUTE IMMEDIATE ('GRANT SELECT ON rr_id_seq TO '||in_username);
        ELSE
            EXECUTE IMMEDIATE ('CREATE USER '||in_username||' IDENTIFIED BY PW'||SUBSTR(get_employee_hash(in_password),1,28));
            EXECUTE IMMEDIATE ('GRANT DBA TO '||in_username);
        END IF;
        COMMIT;
    END;
    /
    
CREATE OR REPLACE PROCEDURE update_employee
    (in_e_id IN INTEGER, in_credential IN VARCHAR, in_new_value IN VARCHAR, p_success OUT INTEGER)
    AUTHID CURRENT_USER AS
    username_store VARCHAR(20);
    password_store VARCHAR(30);
    unique_credential_exists INTEGER;
    BEGIN
        SELECT e_username, e_password INTO username_store, password_store
            FROM employees
            WHERE e_id = in_e_id;
        IF in_credential = 'username' THEN
            SELECT COUNT(*) INTO unique_credential_exists
                FROM employees
                WHERE e_username = in_new_value;
            IF unique_credential_exists = 0 THEN
                UPDATE employees 
                    SET e_username = in_new_value
                    WHERE e_id = in_e_id;
                p_success := SQL%ROWCOUNT;
                EXECUTE IMMEDIATE ('DROP USER '||username_store);
                SELECT COUNT(*) INTO unique_credential_exists FROM employees WHERE e_username = in_new_value AND is_manager = 't';
                IF unique_credential_exists > 0 THEN
                    EXECUTE IMMEDIATE ('CREATE USER '||in_new_value||' IDENTIFIED BY '||password_store);
                    EXECUTE IMMEDIATE ('GRANT DBA TO '||in_new_value);
                ELSE
                    EXECUTE IMMEDIATE ('CREATE USER '||in_new_value||' IDENTIFIED BY '||password_store);
                    EXECUTE IMMEDIATE ('GRANT CREATE SESSION TO '||in_new_value);
                    EXECUTE IMMEDIATE ('GRANT EXECUTE ON update_employee TO '||in_new_value);
                    EXECUTE IMMEDIATE ('GRANT EXECUTE ON insert_request TO '||in_new_value);
                    EXECUTE IMMEDIATE ('GRANT EXECUTE ON get_employee_hash TO '||in_new_value);
                    EXECUTE IMMEDIATE ('GRANT SELECT,UPDATE ON employees TO '||in_new_value);
                    EXECUTE IMMEDIATE ('GRANT INSERT,SELECT ON reimbursement_requests TO '||in_new_value);
                    EXECUTE IMMEDIATE ('GRANT SELECT ON rr_id_seq TO '||in_new_value);
                END IF;
            ELSE
                p_success := 2;
            END IF;
        ELSIF in_credential = 'password' THEN
            UPDATE employees 
                SET e_password = 'PW'||SUBSTR(get_employee_hash(in_new_value),1,28)
                WHERE e_id = in_e_id;
            p_success := SQL%ROWCOUNT; 
            EXECUTE IMMEDIATE ('ALTER USER '||username_store||' IDENTIFIED BY PW'||SUBSTR(get_employee_hash(in_new_value),1,28));
        ELSIF in_credential = 'firstName' THEN
            UPDATE employees 
                SET e_first_name = in_new_value
                WHERE e_id = in_e_id;
            p_success := SQL%ROWCOUNT;
        ELSIF in_credential = 'lastName' THEN
            UPDATE employees 
                SET e_last_name = in_new_value
                WHERE e_id = in_e_id;
            p_success := SQL%ROWCOUNT;
        ELSIF in_credential = 'email' THEN
            UPDATE employees 
                SET e_email = in_new_value
                WHERE e_id = in_e_id;
            p_success := SQL%ROWCOUNT;
        ELSIF in_credential = 'confirmation' THEN
            UPDATE employees 
                SET is_confirmed = 't'
                WHERE e_id = in_e_id;
            p_success := SQL%ROWCOUNT;
            UPDATE employees 
                SET e_password = 'PW'||SUBSTR(get_employee_hash(in_new_value),1,28)
                WHERE e_id = in_e_id;
            EXECUTE IMMEDIATE ('ALTER USER '||username_store||' IDENTIFIED BY PW'||SUBSTR(get_employee_hash(in_new_value),1,28));
        END IF;
        COMMIT;
    END;
    /

CREATE OR REPLACE PROCEDURE delete_employees
    AUTHID CURRENT_USER AS
    CURSOR user_list is SELECT e_username FROM employees;
    num_employees INTEGER;
    username VARCHAR(20);
    BEGIN
        OPEN user_list;
        SELECT COUNT(*) INTO num_employees FROM employees;
        FOR i IN 1..num_employees LOOP
            FETCH user_list INTO username;
            EXIT WHEN user_list%NOTFOUND;
            EXECUTE IMMEDIATE ('DROP USER '||username);
        END LOOP;
        CLOSE user_list;
        
        DELETE FROM employees;
        COMMIT;
    END;
    /

CREATE OR REPLACE PROCEDURE insert_request
    (e_id IN INTEGER, in_description IN VARCHAR, in_amount IN NUMBER, p_success OUT INTEGER)
    AUTHID CURRENT_USER AS
    BEGIN
        INSERT INTO reimbursement_requests VALUES(NULL, e_id, NULL, in_description, in_amount, NULL);
        p_success := SQL%ROWCOUNT;
        COMMIT;
    END;
    /

CREATE OR REPLACE TRIGGER employee_insert_trigger
    BEFORE INSERT
    ON employees
    FOR EACH ROW
    BEGIN
        IF :NEW.e_id IS NULL THEN
            SELECT e_id_seq.NEXTVAL INTO :NEW.e_id FROM DUAL;
        END IF;
    END;
    /

CREATE OR REPLACE TRIGGER request_insert_trigger
    BEFORE INSERT
    ON reimbursement_requests
    FOR EACH ROW
    BEGIN
        IF :NEW.rr_id IS NULL THEN
            SELECT rr_id_seq.NEXTVAL INTO :NEW.rr_id FROM DUAL;
        END IF;
    END;
    /

CREATE OR REPLACE PUBLIC SYNONYM employees
    FOR admin01.employees;
CREATE OR REPLACE PUBLIC SYNONYM reimbursement_requests
    FOR admin01.reimbursement_requests;
CREATE OR REPLACE PUBLIC SYNONYM e_id_seq
    FOR admin01.e_id_seq;
CREATE OR REPLACE PUBLIC SYNONYM rr_id_seq
    FOR admin01.rr_id_seq;
CREATE OR REPLACE PUBLIC SYNONYM insert_employee
    FOR admin01.insert_employee;
CREATE OR REPLACE PUBLIC SYNONYM update_employee
    FOR admin01.update_employee;
CREATE OR REPLACE PUBLIC SYNONYM delete_employee
    FOR admin01.delete_employee;
CREATE OR REPLACE PUBLIC SYNONYM insert_request
    FOR admin01.insert_request;
CREATE OR REPLACE PUBLIC SYNONYM get_employee_hash
    FOR admin01.get_employee_hash;

VARIABLE temp_num NUMBER;
EXECUTE insert_employee('manager','mpass','temp','manager','false@email.com','t',:temp_num);