package com.bank.rewards.services;

import com.bank.rewards.config.RewardsProperties;
import com.bank.rewards.constants.ErrorMessages;
import com.bank.rewards.entities.Customer;
import com.bank.rewards.entities.Transaction;
import com.bank.rewards.exceptions.ConfigurationException;
import com.bank.rewards.exceptions.NoRecordFoundException;
import com.bank.rewards.repositories.CustomerRepository;
import com.bank.rewards.repositories.TransactionRepository;
import com.bank.rewards.utils.PointsCalculatorUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PointsServiceImpl implements PointsService {

    private static Logger logger = LogManager.getLogger(PointsServiceImpl.class);

    @Autowired
    private RewardsProperties rewardsProperties;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    /**
     * {@inheritDoc}
     */
    public Long getPointsByCustomerIdAndMonth(Long customerId, int month) {
        this.checkCustomerPresent(customerId);
        this.checkRewardsConfig();
        List<Transaction> transactions = this.transactionRepository.findAllByCustomerIdAndExecutionMonth(customerId, month);
        return PointsCalculatorUtil.calculatePoints(transactions, this.rewardsProperties.getPointsConfig());
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Long getPointsByCustomerIdAndBetweenDates(Long customerId, Date fromDate, Date toDate) {
        this.checkCustomerPresent(customerId);
        this.checkRewardsConfig();
        List<Transaction> transactions = this.transactionRepository.findAllByCustomerIdAndExecTimeBetween(customerId, new Timestamp(fromDate.getTime()), new Timestamp(toDate.getTime()));
        return PointsCalculatorUtil.calculatePoints(transactions, this.rewardsProperties.getPointsConfig());
    }

    /**
     * Check if rewards configurations is done
     */
    private void checkRewardsConfig() {
        if (this.rewardsProperties == null || this.rewardsProperties.getPointsConfig() == null || this.rewardsProperties.getPointsConfig().isEmpty()) {
            logger.error("Rewards settings not configured");
            throw new ConfigurationException(ErrorMessages.REWARDS_CONFIGURATION_MISSING);
        }
    }

    /**
     * Check if customer is present
     * @param customerId
     */
    private void checkCustomerPresent(Long customerId) {
        Optional<Customer> customer = this.customerRepository.findById(customerId);
        if (!customer.isPresent()) {
            logger.error("Customer not found with id: " + customerId);
            throw new NoRecordFoundException(ErrorMessages.CUSTOMER_NOT_FOUND);
        }
    }
}
