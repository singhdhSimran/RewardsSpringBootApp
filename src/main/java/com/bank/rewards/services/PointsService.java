package com.bank.rewards.services;

import com.bank.rewards.exceptions.NoRecordFoundException;

import java.util.Date;

public interface PointsService {

    /**
     * get points given customer id and month no
     *
     * @param customerId
     * @param month
     * @return Long Points
     * @throws NoRecordFoundException
     */
    public Long getPointsByCustomerIdAndMonth(Long customerId, int month);

    /**
     * get points given customer id and month no
     *
     * @param customerId
     * @param fromDate
     * @param toDate
     * @return Long Points
     * @throws NoRecordFoundException
     */
    public Long getPointsByCustomerIdAndBetweenDates(Long customerId, Date fromDate, Date toDate);

}
