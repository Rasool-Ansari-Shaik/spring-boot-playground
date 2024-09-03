package com.myfinbank.model;

import java.util.Date;




public class Transaction {
private String transactionId;
    
    private Double amount;
    
    private String type; // "DEPOSIT", "WITHDRAWAL", "TRANSFER"
    
    
    private Date date;

    
    private Account account;


	public String getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Account getAccount() {
		return account;
	}


	public void setAccount(Account account) {
		this.account = account;
	}
    
}
