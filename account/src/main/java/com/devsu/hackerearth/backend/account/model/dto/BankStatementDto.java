package com.devsu.hackerearth.backend.account.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankStatementDto {

	private Date date;
	private String client;
	private String accountNumber;
	private String accountType;
	private double initialAmount;
	private boolean isActive;
	private String transactionType;
	private double amount;
	private double balance;
	public BankStatementDto(Date date, String client, String transactionType, double amount, double balance) {
		this.date = date;
		this.client = client;
		this.transactionType = transactionType;
		this.amount = amount;
		this.balance = balance;
	}
}
