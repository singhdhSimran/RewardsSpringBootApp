package com.bank.rewards.repositories;

import com.bank.rewards.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByCustomerIdAndExecTimeBetween(Long customerId, Timestamp from, Timestamp to);

    @Query(value = "select * from transactions trans where trans.customer_id = ?1 and month(trans.execution_time) = ?2", nativeQuery = true)
    List<Transaction> findAllByCustomerIdAndExecutionMonth(Long customerId, Integer month);
}
