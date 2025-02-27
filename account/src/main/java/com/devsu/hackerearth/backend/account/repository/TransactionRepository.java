package com.devsu.hackerearth.backend.account.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devsu.hackerearth.backend.account.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT t FROM Transaction t JOIN t.account a WHERE a.clientId = :clientId AND t.date BETWEEN :startDate AND :endDate")
    List<Transaction> findByAccountClientIdAndDateBetween(@Param("clientId") Long clientId,
            @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT t FROM Transaction t WHERE t.accountId = :accountId ORDER BY t.date DESC")
    Optional<Transaction> findTopByAccountIdOrderByDateDesc(@Param("accountId") Long accountID);
}
