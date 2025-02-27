package com.devsu.hackerearth.backend.account.service;

import java.util.Date;
import java.util.List;
import com.devsu.hackerearth.backend.account.model.dto.BankStatementDto;
import com.devsu.hackerearth.backend.account.model.dto.TransactionDto;

public interface TransactionService {

    public List<TransactionDto> getAll();

    public TransactionDto getById(Long id);

    public TransactionDto create(TransactionDto transactionDto);

    public List<BankStatementDto> getAllByAccountClientIdAndDateBetween(Long clientId,
            Date dateTransactionStart,
            Date dateTransactionEnd);

    public TransactionDto getLastByAccountId(Long accountId);
}
