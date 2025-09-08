package com.ust.rewards.calculator;

import com.ust.rewards.dto.RestaurantTransaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantRewardCalculator implements RewardCalculator<RestaurantTransaction> {

    @Override
    public String getCategory() {
        return "restaurants";
    }

    @Override
    public int calculateRewards(List<RestaurantTransaction> transactions) {
        // Example rule: 5 points per reservation
        return transactions.size() * 5;
    }
}
