package com.devsu.hackerearth.backend.account.model.dto;

import java.util.Date;

import com.devsu.hackerearth.backend.account.model.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

	private Long id;
    private Date date;
	private String type;
	private double amount;
	private double balance;
	private Long accountId;
	private Account account;
	
	public TransactionDto(Long id, Date date, String type, double amount, double balance, Long accountId) {
		this.id = id;
		this.date = date;
		this.type = type;
		this.amount = amount;
		this.balance = balance;
		this.accountId = accountId;
	}

}
