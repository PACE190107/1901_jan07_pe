package com.jdbcbank.models;

import java.io.Serializable;

public class Transaction implements Serializable {
	private static final long serialVersionUID = -3370889978030269137L;
	
	private String action;
	private Double amount;
	private Double balance;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	public Transaction() { }
	public Transaction(String action, Double amount, Double balance) {
		super();
		this.action = action;
		this.amount = amount;
		this.balance = balance;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((balance == null) ? 0 : balance.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (balance == null) {
			if (other.balance != null)
				return false;
		} else if (!balance.equals(other.balance))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "action= " + String.format("%8s", action.toUpperCase()) +
				", amount= " + String.format("$% ,020.2f", amount) +
				", balance= " + String.format("$%,020.2f", balance);
	}
}
