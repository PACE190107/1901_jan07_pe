package com.revature.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

public class Employee implements Serializable {
    private long e_id;
    private String password;
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private boolean manager;

    public Employee() {
    }

    public Employee(Long e_id, boolean manager){
        this.e_id = e_id;
        this.manager = manager;
    }

    public Employee(String username, String password){
        this.password = password;
        this.username = username;
    }

    public Employee(String password, String username, String first_name, String last_name, String email) {
        this.password = password;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public Employee(long e_id, String password, String username, String first_name, String last_name, String email, boolean manager) {
        this.e_id = e_id;
        this.password = password;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.manager = manager;
    }

    public long getE_id() {
        return e_id;
    }

    public void setE_id(long e_id) {
        this.e_id = e_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return e_id == employee.e_id &&
                manager == employee.manager &&
                Objects.equals(password, employee.password) &&
                Objects.equals(username, employee.username) &&
                Objects.equals(first_name, employee.first_name) &&
                Objects.equals(last_name, employee.last_name) &&
                Objects.equals(email, employee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(e_id, password, username, first_name, last_name, email, manager);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "e_id=" + e_id +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", manager=" + manager +
                '}';
    }
}
