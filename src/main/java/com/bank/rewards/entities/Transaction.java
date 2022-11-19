package com.bank.rewards.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "execution_time")
    private Timestamp execTime;

    private Long amount;

    @Column(name = "customer_id")
    private Long customerId;

    public Transaction() {

    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Timestamp getExecTime() {
        return execTime;
    }

    public void setExecTime(Timestamp execTime) {
        this.execTime = execTime;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
