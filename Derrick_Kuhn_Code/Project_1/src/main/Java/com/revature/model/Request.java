package com.revature.model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Request {
    private long id;
    private long e_id;
    private String subject;
    private String description;
    private double amount;
    private long approver_id;
    private LocalDateTime dateRequested;
    private LocalDateTime dateApproved;
    private Status status;

    public Request() {
    }

    public Request(long e_id, String subject, String description, double amount) {
        this.e_id = e_id;
        this.subject = subject;
        this.description = description;
        this.amount = amount;
        this.status = Status.PENDING;
        this.dateRequested = LocalDateTime.now();
    }

    public Request(long id, long e_id, String subject, String description, double amount, long approver_id, LocalDateTime date, LocalDateTime dateApproved, Status status) {
        this.id = id;
        this.e_id = e_id;
        this.subject = subject;
        this.description = description;
        this.amount = amount;
        this.approver_id = approver_id;
        this.dateRequested = date;
        this.dateApproved = dateApproved;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public LocalDateTime getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(LocalDateTime date) {
        this.dateRequested = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getDateApproved() {
        return dateApproved;
    }

    public void setDateApproved(LocalDateTime dateApproved) {
        this.dateApproved = dateApproved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return id == request.id &&
                e_id == request.e_id &&
                Double.compare(request.amount, amount) == 0 &&
                approver_id == request.approver_id &&
                Objects.equals(subject, request.subject) &&
                Objects.equals(description, request.description) &&
                Objects.equals(dateRequested, request.dateRequested) &&
                status == request.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, e_id, subject, description, amount, approver_id, dateRequested, dateApproved, status);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", e_id=" + e_id +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", approver_id=" + approver_id +
                ", date=" + dateRequested +
                ", status=" + status +
                '}';
    }
}
