package com.bank.rewards.utils;

import com.bank.rewards.config.PointsConfig;
import com.bank.rewards.entities.Transaction;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PointsCalculatorUtil {

    public static Long calculatePoints(List<Transaction> transactions, List<PointsConfig> pointsConfigs) {
        return transactions.stream().mapToLong(transaction -> PointsCalculatorUtil.calculatePointsForTransaction(transaction, pointsConfigs)).sum();
    }

    /**
     * Calculate points for given transaction
     *
     * @param transaction
     * @return Long
     */
    public static Long calculatePointsForTransaction(Transaction transaction, List<PointsConfig> pointsConfigs) {
        Long points = 0L;
        Long prevCalculated = 0L;
        for (PointsConfig pointsConfig : pointsConfigs.stream().filter(pointsConfig -> transaction.getAmount() > pointsConfig.getAmountThreshold()).sorted(Comparator.reverseOrder()).collect(Collectors.toList())) {
            prevCalculated = transaction.getAmount() - pointsConfig.getAmountThreshold() - prevCalculated;
            points += prevCalculated * pointsConfig.getPointsAwardedPerSpend();
        }
        return points;
    }
}
