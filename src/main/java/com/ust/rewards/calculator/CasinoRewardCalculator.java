package com.ust.rewards.calculator;

import com.ust.rewards.dto.CasinoTransaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CasinoRewardCalculator implements RewardCalculator<CasinoTransaction> {

    @Override
    public String getCategory() {
        return "casinos";
    }

    @Override
    public int calculateRewards(List<CasinoTransaction> transactions) {
        // Example rule: 1 point per $10 spent
        return transactions.stream()
                .mapToInt(tx -> (int) (tx.amountSpent() / 10))
                .sum();
    }
}
