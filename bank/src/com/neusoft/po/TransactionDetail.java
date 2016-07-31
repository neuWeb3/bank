package com.neusoft.po;

import java.sql.Timestamp;

/**
 * TransactionDetail entity. @author MyEclipse Persistence Tools
 */

public class TransactionDetail implements java.io.Serializable {

	// Fields

	private Integer id;
	private Account accountByTargetAccount;
	private Account accountByAccountId;
	private Timestamp transactionTime;
	private Double amountPaid;
	private Double amountReceived;
	private Double balance;
	private String currency;
	private String type;
	private String message;

	// Constructors

	/** default constructor */
	public TransactionDetail() {
	}

	/** minimal constructor */
	public TransactionDetail(Account accountByAccountId, Double amountPaid,
			Double amountReceived, Double balance, String currency) {
		this.accountByAccountId = accountByAccountId;
		this.amountPaid = amountPaid;
		this.amountReceived = amountReceived;
		this.balance = balance;
		this.currency = currency;
	}

	/** full constructor */
	public TransactionDetail(Account accountByTargetAccount,
			Account accountByAccountId, Timestamp transactionTime,
			Double amountPaid, Double amountReceived, Double balance,
			String currency, String type, String message) {
		this.accountByTargetAccount = accountByTargetAccount;
		this.accountByAccountId = accountByAccountId;
		this.transactionTime = transactionTime;
		this.amountPaid = amountPaid;
		this.amountReceived = amountReceived;
		this.balance = balance;
		this.currency = currency;
		this.type = type;
		this.message = message;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Account getAccountByTargetAccount() {
		return this.accountByTargetAccount;
	}

	public void setAccountByTargetAccount(Account accountByTargetAccount) {
		this.accountByTargetAccount = accountByTargetAccount;
	}

	public Account getAccountByAccountId() {
		return this.accountByAccountId;
	}

	public void setAccountByAccountId(Account accountByAccountId) {
		this.accountByAccountId = accountByAccountId;
	}

	public Timestamp getTransactionTime() {
		return this.transactionTime;
	}

	public void setTransactionTime(Timestamp transactionTime) {
		this.transactionTime = transactionTime;
	}

	public Double getAmountPaid() {
		return this.amountPaid;
	}

	public void setAmountPaid(Double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Double getAmountReceived() {
		return this.amountReceived;
	}

	public void setAmountReceived(Double amountReceived) {
		this.amountReceived = amountReceived;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}