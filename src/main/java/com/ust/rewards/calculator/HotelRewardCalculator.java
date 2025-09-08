package com.ust.rewards.calculator;

import com.ust.rewards.dto.HotelTransaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HotelRewardCalculator implements RewardCalculator<HotelTransaction> {

    @Override
    public String getCategory() {
        return "hotels";
    }

    @Override
    public int calculateRewards(List<HotelTransaction> transactions) {

        // Example rule: 10 points per night stayed
        return transactions.stream()
                .mapToInt(HotelTransaction::nightsStayed)
                .map(nights -> nights * 10)
                .sum();
    }
}
