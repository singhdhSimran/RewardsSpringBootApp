package com.bank.rewards.services;

import com.bank.rewards.config.PointsConfig;
import com.bank.rewards.config.RewardsProperties;
import com.bank.rewards.entities.Customer;
import com.bank.rewards.entities.Transaction;
import com.bank.rewards.exceptions.ConfigurationException;
import com.bank.rewards.exceptions.NoRecordFoundException;
import com.bank.rewards.repositories.CustomerRepository;
import com.bank.rewards.repositories.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class PointsServiceImplTest {

    @InjectMocks
    private PointsServiceImpl pointsCalculator;

    @Mock
    private RewardsProperties rewardsProperties;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    TransactionRepository transactionRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPointsByCustomerIdAndBetweenDates() {
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
        Mockito.when(rewardsProperties.getPointsConfig()).thenReturn(configs);
        Mockito.when(customerRepository.findById(2L)).thenReturn(Optional.of(new Customer()));
        Mockito.when(transactionRepository.findAllByCustomerIdAndExecTimeBetween(Mockito.any(Long.class), Mockito.any(Timestamp.class), Mockito.any(Timestamp.class))).thenReturn(transactions);
        assertEquals(130L, pointsCalculator.getPointsByCustomerIdAndBetweenDates(2L, new Date(), new Date()));
    }

    @Test
    void getPointsByCustomerIdAndMonth() {
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
        Mockito.when(rewardsProperties.getPointsConfig()).thenReturn(configs);
        Mockito.when(customerRepository.findById(2L)).thenReturn(Optional.of(new Customer()));
        Mockito.when(transactionRepository.findAllByCustomerIdAndExecutionMonth(2L, 11)).thenReturn(transactions);
        assertEquals(130L, pointsCalculator.getPointsByCustomerIdAndMonth(2L, 11));
    }

    @Test
    void getPointsByCustomerIdAndMonthCustomerNotFound() {
        Mockito.when(customerRepository.findById(2L)).thenReturn(Optional.empty());
        try {
            pointsCalculator.getPointsByCustomerIdAndMonth(2L, 11);
            fail("Should have thrown NoRecordFoundException");
        } catch (NoRecordFoundException ex) {
        }
    }

    @Test
    void getPointsByCustomerIdAndMonthConfigError() {
        Mockito.when(rewardsProperties.getPointsConfig()).thenReturn(null);
        Mockito.when(customerRepository.findById(2L)).thenReturn(Optional.of(new Customer()));
        try {
            pointsCalculator.getPointsByCustomerIdAndMonth(2L, 11);
            fail("Should have thrown ConfigurationException");
        } catch (ConfigurationException ex) {
        }
    }

}