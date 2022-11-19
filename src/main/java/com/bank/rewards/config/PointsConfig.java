package com.bank.rewards.config;

public class PointsConfig implements Comparable<PointsConfig> {
    private Long amountThreshold;
    private Long pointsAwardedPerSpend;

    public PointsConfig() {
    }

    public Long getAmountThreshold() {
        return amountThreshold;
    }

    public void setAmountThreshold(Long amountThreshold) {
        this.amountThreshold = amountThreshold;
    }

    public Long getPointsAwardedPerSpend() {
        return pointsAwardedPerSpend;
    }

    public void setPointsAwardedPerSpend(Long pointsAwardedPerSpend) {
        this.pointsAwardedPerSpend = pointsAwardedPerSpend;
    }

    @Override
    public int compareTo(PointsConfig config) {
        return Long.compare(this.getAmountThreshold(), config.getAmountThreshold());
    }
}
