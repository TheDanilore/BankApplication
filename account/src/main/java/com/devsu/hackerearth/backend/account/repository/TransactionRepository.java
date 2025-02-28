package com.devsu.hackerearth.backend.account.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devsu.hackerearth.backend.account.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t WHERE t.accountId IN (SELECT a.id FROM Account a WHERE a.clientId = :clientId) AND t.date BETWEEN :dateTransactionStart AND :dateTransactionEnd")
    List<Transaction> findByAccountClientIdAndDateBetween(@Param("clientId") Long clientId,
            @Param("dateTransactionStart") Date dateTransactionStart, @Param("dateTransactionEnd") Date dateTransactionEnd);

    @Query("SELECT t FROM Transaction t WHERE t.accountId = :accountId ORDER BY t.date DESC")
    List<Transaction> findTopByAccountIdOrderByDateDesc(@Param("accountId") Long accountID);
}
