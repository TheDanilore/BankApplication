package com.devsu.hackerearth.backend.account.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.devsu.hackerearth.backend.account.model.dto.AccountDto;
import com.devsu.hackerearth.backend.account.model.dto.PartialAccountDto;
import com.devsu.hackerearth.backend.account.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

	private final AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping
	public ResponseEntity<List<AccountDto>> getAll() {
		// api/accounts
		// Get all accounts
		List<AccountDto> accounts = accountService.getAll();
		if (!accounts.isEmpty()) {
			return ResponseEntity.ok(accounts);
		} else {
			return ResponseEntity.noContent().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<AccountDto> getById(@PathVariable Long id) {
		// api/accounts/{id}
		// Get accounts by id
		AccountDto accountDto = accountService.getById(id);
		if (accountDto != null) {
			return ResponseEntity.ok(accountDto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	public ResponseEntity<AccountDto> create(@RequestBody AccountDto accountDto) {
		// api/accounts
		// Create accounts
		AccountDto createdAccount = accountService.create(accountDto);
		return ResponseEntity.status(201).body(createdAccount);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AccountDto> update(@PathVariable Long id, @RequestBody AccountDto accountDto) {
		// api/accounts/{id}
		// Update accounts
		AccountDto updatedAccount = accountService.update(accountDto);
		if (updatedAccount != null) {
			return ResponseEntity.ok(updatedAccount);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<AccountDto> partialUpdate(@PathVariable Long id,
			@RequestBody PartialAccountDto partialAccountDto) {
		// api/accounts/{id}
		// Partial update accounts
		AccountDto updatedAccount = accountService.partialUpdate(id, partialAccountDto);
		if (updatedAccount != null) {
			return ResponseEntity.ok(updatedAccount);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		// api/accounts/{id}
		// Delete accounts
		accountService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
