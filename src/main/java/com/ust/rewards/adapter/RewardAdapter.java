package com.ust.rewards.adapter;

import com.ust.rewards.exception.RewardsServiceException;

import java.util.List;

public interface RewardAdapter<T> {
    String getCategory();

    List<T> fetchTransactions(String customerId) throws RewardsServiceException;
}
