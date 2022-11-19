package com.bank.rewards.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@PropertySource("classpath:rewards.properties")
@ConfigurationProperties(prefix = "rewards")
public class RewardsProperties {
    private List<PointsConfig> pointsConfig;

    public RewardsProperties() {
    }

    public List<PointsConfig> getPointsConfig() {
        return pointsConfig;
    }

    public void setPointsConfig(List<PointsConfig> pointsConfig) {
        this.pointsConfig = pointsConfig;
    }
}
