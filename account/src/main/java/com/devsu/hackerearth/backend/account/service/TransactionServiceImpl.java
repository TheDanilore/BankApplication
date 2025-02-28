package com.devsu.hackerearth.backend.account.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.devsu.hackerearth.backend.account.exception.InsufficientFundsException;
import com.devsu.hackerearth.backend.account.model.Account;
import com.devsu.hackerearth.backend.account.model.Transaction;
import com.devsu.hackerearth.backend.account.model.dto.BankStatementDto;
import com.devsu.hackerearth.backend.account.model.dto.TransactionDto;
import com.devsu.hackerearth.backend.account.repository.AccountRepository;
import com.devsu.hackerearth.backend.account.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

        private final TransactionRepository transactionRepository;
        private final AccountRepository accountRepository;

        public TransactionServiceImpl(TransactionRepository transactionRepository,
                        AccountRepository accountRepository) {
                this.transactionRepository = transactionRepository;
                this.accountRepository = accountRepository;
        }

        @Override
        public List<TransactionDto> getAll() {
                // Get all transactions
                List<Transaction> transactions = transactionRepository.findAll();
                return transactions.stream()
                                .map(transaction -> new TransactionDto(transaction.getId(), transaction.getDate(),
                                                transaction.getType(), transaction.getAmount(),
                                                transaction.getBalance(),
                                                transaction.getAccountId()))
                                .collect(Collectors.toList());
        }

        @Override
        public TransactionDto getById(Long id) {
                // Get transactions by id
                Optional<Transaction> transactionOptional = transactionRepository.findById(id);
                return transactionOptional
                                .map(transaction -> new TransactionDto(transaction.getId(), transaction.getDate(),
                                                transaction.getType(), transaction.getAmount(),
                                                transaction.getBalance(), transaction.getAccountId()))
                                .orElse(null);
        }

        @Override
        public TransactionDto create(TransactionDto transactionDto) {
                // Create transaction
                Account account = accountRepository.findById(transactionDto.getAccountId())
                                .orElseThrow(() -> new RuntimeException("Account not found"));

                double newBalance = account.getInitialAmount();

                if (newBalance < 0) {
                        throw new InsufficientFundsException("Saldo no disponible");
                }

                account.setInitialAmount(newBalance);
                accountRepository.save(account);

                Transaction transaction = new Transaction(transactionDto.getId(), transactionDto.getDate(),
                                transactionDto.getType(), transactionDto.getAmount(), newBalance,
                                transactionDto.getAccountId());

                Transaction savedTransaction = transactionRepository.save(transaction);
                return new TransactionDto(savedTransaction.getId(), savedTransaction.getDate(),
                                savedTransaction.getType(), savedTransaction.getAmount(), savedTransaction.getBalance(),
                                savedTransaction.getAccountId());
        }

        @Override
        public List<BankStatementDto> getAllByAccountClientIdAndDateBetween(Long clientId, Date dateTransactionStart,
                        Date dateTransactionEnd) {
                // Report
                // Obtener todas las transacciones dentro del rango de fechas especificado
                List<Transaction> transactions = transactionRepository.findByAccountClientIdAndDateBetween(clientId,
                                dateTransactionStart, dateTransactionEnd);

                // convertir las transacciones a BankStatementDto
                return transactions.stream().map(transaction -> {
                        // Obtener la cuenta asociada usando el accountId
                        Account account = accountRepository.findById(transaction.getAccountId())
                                        .orElseThrow(() -> new RuntimeException("Account not found"));

                        return new BankStatementDto(transaction.getDate(),
                                        clientId.toString(), // clientId en lugar del nombre del cliente
                                        account.getNumber(),
                                        account.getType(),
                                        account.getInitialAmount(),
                                        account.isActive(),
                                        transaction.getType(),
                                        transaction.getAmount(),
                                        transaction.getBalance());
                })
                                .collect(Collectors.toList());
        }

        @Override
        public TransactionDto getLastByAccountId(Long accountId) {
                // If you need it
                List<Transaction> transactions = transactionRepository
                                .findTopByAccountIdOrderByDateDesc(accountId);

                if (transactions.isEmpty()) {
                        return null;
                }

                Transaction lastTransaction = transactions.get(0);

                return new TransactionDto(
                        lastTransaction.getId(),
                        lastTransaction.getDate(),
                        lastTransaction.getType(),
                        lastTransaction.getAmount(),
                        lastTransaction.getBalance(),
                        lastTransaction.getAccountId());
        }

}
