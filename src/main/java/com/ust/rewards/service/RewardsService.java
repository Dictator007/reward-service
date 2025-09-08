package com.ust.rewards.service;

import com.ust.rewards.adapter.RewardAdapter;
import com.ust.rewards.adapter.RewardAdapterFactory;
import com.ust.rewards.calculator.RewardCalculator;
import com.ust.rewards.calculator.RewardCalculatorFactory;
import com.ust.rewards.dto.RewardPointsResponse;
import com.ust.rewards.exception.RewardsServiceException;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Log
public class RewardsService {

    private final RewardAdapterFactory adapterFactory;
    private final RewardCalculatorFactory calculatorFactory;

    public RewardsService(RewardAdapterFactory adapterFactory, RewardCalculatorFactory calculatorFactory) {
        this.adapterFactory = adapterFactory;
        this.calculatorFactory = calculatorFactory;
    }

    @SuppressWarnings("unchecked")
    public RewardPointsResponse calculateRewards(String customerId, List<String> categories) throws RewardsServiceException{
        if (categories == null || categories.isEmpty()) {
            log.info("Category Is Null Or Empty");
            categories = adapterFactory.getAllCategories();
        }

        int totalPoints = 0;
        Map<String, Integer> categoryPoints = new HashMap<>();

        for (String category : categories) {
            // Get the adapter and calculator for the category
            RewardAdapter<?> adapter = adapterFactory.getAdapter(category);
            RewardCalculator<?> calculator = calculatorFactory.getCalculator(category);
            // Delegate to generic helper to calculate points
            // Safe cast to raw types for generic helper
            int points = processCategory((RewardAdapter) adapter, (RewardCalculator) calculator, customerId);
            categoryPoints.put(category, points);
            log.info("Category with points : " + category + " = " + points);
            totalPoints += points;
        }

        return new RewardPointsResponse(customerId, totalPoints, categoryPoints);
    }

    // Generic helper method
    private <T> int processCategory(RewardAdapter<T> adapter, RewardCalculator<T> calculator, String customerId) throws RewardsServiceException {
        List<T> transactions = adapter.fetchTransactions(customerId);
        return calculator.calculateRewards(transactions);
    }
}
