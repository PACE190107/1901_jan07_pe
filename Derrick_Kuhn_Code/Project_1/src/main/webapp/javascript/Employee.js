class Employee {

    constructor(username, password) {
        // invokes the setter
        this.e_id = 0;
        this.username = username;
        this.password = password;
        this.first_name = 'John';
        this.last_name = 'Doe';
        this.email = 'jdoe@123.com';
        this.manager = false;
    }

    fullEmployee(e_id, username, password, first_name, last_name, email, manager) {
        this.e_id = e_id;
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.manager = manager;
    }

    get username() {
        return this.username;
    }

    set username(value) {
        this.username = value;
    }

    get password() {
        return this.password;
    }

    set password(value) {
        this.password = value;
    }

    get e_id() {
        return this.e_id;
    }

    set e_id(value) {
        this.e_id = value;
    }

    get first_name() {
        return this.first_name;
    }

    set first_name(value) {
        this.first_name = value;
    }

    get last_name() {
        return this.last_name;
    }

    set last_name(value) {
        this.last_name = value;
    }

    get email() {
        return this.email;
    }

    set email(value) {
        this.email = value;
    }

    get manager() {
        return this.manager;
    }

    set manager(value) {
        this.manager = value;
    }
}

export default Employee;