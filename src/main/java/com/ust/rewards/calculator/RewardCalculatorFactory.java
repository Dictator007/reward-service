package com.ust.rewards.calculator;

import com.ust.rewards.exception.RewardsServiceException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RewardCalculatorFactory {

    private final Map<String, RewardCalculator> calculators;

    public RewardCalculatorFactory(Set<RewardCalculator> calculators) {
        // Convert the set into a map where the key is the category
        this.calculators = calculators.stream()
                .collect(Collectors.toMap(RewardCalculator::getCategory, calculator -> calculator));
    }

    public RewardCalculator getCalculator(String category) throws RewardsServiceException {
        if (!calculators.containsKey(category)) {
            throw new RewardsServiceException("Invalid category: " + category);
        }
        return calculators.get(category);
    }
}
