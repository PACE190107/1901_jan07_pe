import Employee from "./Employee";

class Request {

    constructor(name) {
        // invokes the setter
        this._r_id = 0;
        this._e_id = 0;
        this._subject = 'subject';
        this._description = 'description';
        this._amount = 12.34;
        this._approver_id = 0;
        this._status = 'PENDING';
        this._name = name;
    }

    get name() {
        return this._name;
    }

    set name(value) {
        this._name = value;
    }

    get r_id() {
        return this._r_id;
    }

    set r_id(value) {
        this._r_id = value;
    }

    get e_id() {
        return this._e_id;
    }

    set e_id(value) {
        this._e_id = value;
    }

    get subject() {
        return this._subject;
    }

    set subject(value) {
        this._subject = value;
    }

    get description() {
        return this._description;
    }

    set description(value) {
        this._description = value;
    }

    get amount() {
        return this._amount;
    }

    set amount(value) {
        this._amount = value;
    }

    get approver_id() {
        return this._approver_id;
    }

    set approver_id(value) {
        this._approver_id = value;
    }

    get status() {
        return this._status;
    }

    set status(value) {
        this._status = value;
    }
}

export default Request;