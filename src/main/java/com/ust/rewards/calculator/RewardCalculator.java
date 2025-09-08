package com.ust.rewards.calculator;

import java.util.List;

public interface RewardCalculator<T> {

    String getCategory();

    int calculateRewards(List<T> transactions);
}
