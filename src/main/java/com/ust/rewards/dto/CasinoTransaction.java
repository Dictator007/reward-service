package com.ust.rewards.dto;

public record CasinoTransaction(
        String transactionId,
        String customerId,
        String casinoName,
        String transactionDate,
        double amountSpent,
        String gameType // e.g., slots, poker, blackjack
) {}
