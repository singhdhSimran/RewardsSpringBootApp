package com.bank.rewards.utils;

import com.bank.rewards.config.PointsConfig;
import com.bank.rewards.entities.Transaction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointsCalculatorUtilTest {

    @Test
    void calculatePoints() {
        PointsConfig pointsConfig = new PointsConfig();
        pointsConfig.setPointsAwardedPerSpend(2L);
        pointsConfig.setAmountThreshold(100L);
        List<PointsConfig> configs = new ArrayList<>();
        configs.add(pointsConfig);
        pointsConfig = new PointsConfig();
        pointsConfig.setPointsAwardedPerSpend(1L);
        pointsConfig.setAmountThreshold(50L);
        configs.add(pointsConfig);
        Transaction transaction = new Transaction();
        transaction.setAmount(120L);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        transaction = new Transaction();
        transaction.setAmount(90L);
        transactions.add(transaction);
        assertEquals(130L, PointsCalculatorUtil.calculatePoints(transactions, configs));
    }

    @Test
    void calculatePointsZeroPoints() {
        PointsConfig pointsConfig = new PointsConfig();
        pointsConfig.setPointsAwardedPerSpend(2L);
        pointsConfig.setAmountThreshold(100L);
        List<PointsConfig> configs = new ArrayList<>();
        configs.add(pointsConfig);
        pointsConfig = new PointsConfig();
        pointsConfig.setPointsAwardedPerSpend(1L);
        pointsConfig.setAmountThreshold(50L);
        configs.add(pointsConfig);
        Transaction transaction = new Transaction();
        transaction.setAmount(40L);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        transaction = new Transaction();
        transaction.setAmount(50L);
        transactions.add(transaction);
        assertEquals(0L, PointsCalculatorUtil.calculatePoints(transactions, configs));
    }

    @Test
    void calculatePoints100Spend() {
        PointsConfig pointsConfig = new PointsConfig();
        pointsConfig.setPointsAwardedPerSpend(2L);
        pointsConfig.setAmountThreshold(100L);
        List<PointsConfig> configs = new ArrayList<>();
        configs.add(pointsConfig);
        pointsConfig = new PointsConfig();
        pointsConfig.setPointsAwardedPerSpend(1L);
        pointsConfig.setAmountThreshold(50L);
        configs.add(pointsConfig);
        Transaction transaction = new Transaction();
        transaction.setAmount(100L);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        assertEquals(50L, PointsCalculatorUtil.calculatePoints(transactions, configs));
    }
}
