package com.myfinbank.model;

public class Account {

	private User user;
private String accountNumber;
    
    private Double balance;

	
	public User getUser() {
		// TODO Auto-generated method stub
		return user;
	}

	public void setUser(User user) {
		// TODO Auto-generated method stub

		this.user=user;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	

}
