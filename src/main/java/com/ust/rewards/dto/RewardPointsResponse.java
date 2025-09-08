package com.ust.rewards.dto;
import java.util.Map;

public record RewardPointsResponse(String customerId, int totalRewardPoints, Map<String,Integer> categories) {}