package com.revature.model;

public interface AccountInterface {
	public double getBalance();
	public double withdrawl(double withdrawlCash);
	public double deposit(double depositCash);
	public boolean addUser();
	public boolean deleteAccount();
}