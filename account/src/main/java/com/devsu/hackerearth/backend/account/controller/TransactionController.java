package com.devsu.hackerearth.backend.account.controller;

import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.devsu.hackerearth.backend.account.model.dto.BankStatementDto;
import com.devsu.hackerearth.backend.account.model.dto.TransactionDto;
import com.devsu.hackerearth.backend.account.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

	private final TransactionService transactionService;

	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@GetMapping
	public ResponseEntity<List<TransactionDto>> getAll() {
		// api/transactions
		// Get all transactions
		List<TransactionDto> transactions = transactionService.getAll();
		if (!transactions.isEmpty()) {
			return ResponseEntity.ok(transactions);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<TransactionDto> get(@PathVariable Long id) {
		// api/transactions/{id}
		// Get transactions by id
		TransactionDto transactionDto = transactionService.getById(id);
		if (transactionDto != null) {
			return ResponseEntity.ok(transactionDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<TransactionDto> create(@RequestBody TransactionDto transactionDto) {
		// api/transactions
		// Create transactions
		TransactionDto createdTransaction = transactionService.create(transactionDto);
		return ResponseEntity.status(201).body(createdTransaction);
	}

	@GetMapping("/clients/{clientId}/report")
	public ResponseEntity<List<BankStatementDto>> getReport(@PathVariable Long clientId,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTransactionStart,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateTransactionEnd) {
		// api/transactions/clients/{clientId}/report
		// Get report
		List<BankStatementDto> report = transactionService.getAllByAccountClientIdAndDateBetween(clientId,
				dateTransactionStart, dateTransactionEnd);
		if (!report.isEmpty()) {
			return ResponseEntity.ok(report);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping("/last/{accountId}")
	public ResponseEntity<TransactionDto> getLastByAccountId(@PathVariable Long accountId) {
		TransactionDto lastTransaction = transactionService.getLastByAccountId(accountId);
		if (lastTransaction != null) {
			return ResponseEntity.ok(lastTransaction);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
