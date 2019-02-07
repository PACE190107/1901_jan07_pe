package com.revature.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Request {
    private long r_id;
    private long e_id;
    private String subject;
    private String description;
    private double amount;
    private long approver_id;
    private Status status;

    public Request() {
    }

    public Request(long r_id, long approver_id, Status status){
        this.r_id = r_id;
        this.approver_id = approver_id;
        this.status = status;
    }

    public Request(long e_id, String subject, String description, double amount) {
        this.e_id = e_id;
        this.subject = subject;
        this.description = description;
        this.amount = amount;
        this.status = Status.PENDING;
    }

    public Request(long id, long e_id, String subject, String description, double amount, long approver_id, Status status) {
        this.r_id = id;
        this.e_id = e_id;
        this.subject = subject;
        this.description = description;
        this.amount = amount;
        this.approver_id = approver_id;
        this.status = status;
    }

    public long getR_id() {
        return this.r_id;
    }

    public void setR_id(long id) {
        this.r_id = id;
    }

    public long getE_id() {
        return e_id;
    }

    public void setE_id(long e_id) {
        this.e_id = e_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getApprover_id() {
        return approver_id;
    }

    public void setApprover_id(long approver_id) {
        this.approver_id = approver_id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return r_id == request.r_id &&
                e_id == request.e_id &&
                Double.compare(request.amount, amount) == 0 &&
                approver_id == request.approver_id &&
                Objects.equals(subject, request.subject) &&
                Objects.equals(description, request.description) &&
                status == request.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r_id, e_id, subject, description, amount, approver_id, status);
    }

    @Override
    public String toString() {
        return "Request{" +
                "r_id=" + r_id +
                ", e_id=" + e_id +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", approver_id=" + approver_id +
                ", status=" + status +
                '}';
    }
}
