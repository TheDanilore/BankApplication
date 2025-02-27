package com.devsu.hackerearth.backend.account.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.devsu.hackerearth.backend.account.model.Account;
import com.devsu.hackerearth.backend.account.model.dto.AccountDto;
import com.devsu.hackerearth.backend.account.model.dto.PartialAccountDto;
import com.devsu.hackerearth.backend.account.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<AccountDto> getAll() {
        // Get all accounts
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(account -> new AccountDto(account.getId(), account.getNumber(), account.getType(),
                account.getInitialAmount(), account.isActive(), account.getClientId())).collect(Collectors.toList());
    }

    @Override
    public AccountDto getById(Long id) {
        // Get accounts by id
        Optional<Account> accountOptional = accountRepository.findById(id);
        return accountOptional.map(account -> new AccountDto(account.getId(), account.getNumber(), account.getType(),
                account.getInitialAmount(), account.isActive(), account.getClientId())).orElse(null);
    }

    @Override
    public AccountDto create(AccountDto accountDto) {
        // Create account
        Account account = new Account(accountDto.getId(), accountDto.getNumber(), accountDto.getType(),
                accountDto.getInitialAmount(), accountDto.isActive(), accountDto.getClientId());

        Account savedAccount = accountRepository.save(account);
        return new AccountDto(savedAccount.getId(), savedAccount.getNumber(), savedAccount.getType(),
                savedAccount.getInitialAmount(), savedAccount.isActive(), savedAccount.getClientId());
    }

    @Override
    public AccountDto update(AccountDto accountDto) {
        // Update account
        Optional<Account> accountOptional = accountRepository.findById(accountDto.getId());

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setNumber(accountDto.getNumber());
            account.setType(accountDto.getType());
            account.setInitialAmount(accountDto.getInitialAmount());
            account.setActive(accountDto.isActive());
            account.setClientId(accountDto.getClientId());

            Account updatedAccount = accountRepository.save(account);
            return new AccountDto(updatedAccount.getId(), updatedAccount.getNumber(), updatedAccount.getType(),
                    updatedAccount.getInitialAmount(), updatedAccount.isActive(), updatedAccount.getClientId());
        }
        return null;
    }

    @Override
    public AccountDto partialUpdate(Long id, PartialAccountDto partialAccountDto) {
        // Partial update account
        Optional<Account> accountOptional = accountRepository.findById(id);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();

            // Actualiza solo el campo
            if (partialAccountDto.getIsActive() != null) {
                account.setActive(partialAccountDto.getIsActive());
            }
            Account updatedAccount = accountRepository.save(account);
            return new AccountDto(updatedAccount.getId(), updatedAccount.getNumber(), updatedAccount.getType(),
                    updatedAccount.getInitialAmount(), updatedAccount.isActive(), updatedAccount.getClientId());
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        // Delete account
        accountRepository.deleteById(id);
    }

}
