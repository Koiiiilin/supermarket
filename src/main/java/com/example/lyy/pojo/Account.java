package com.example.lyy.pojo;

public class Account {
	private int id;
	private String accountName;
	private double balance;
	
	
	public Account() {
		super();
	}
	
	public Account(String accountName, double balance) {
		super();
		this.accountName = accountName;
		this.balance = balance;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
}
