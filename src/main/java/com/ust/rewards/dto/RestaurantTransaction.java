package com.ust.rewards.dto;

public record RestaurantTransaction(
        String reservationId,
        String customerId,
        String restaurantName,
        String reservationDate,
        int numberOfGuests,
        double billAmount,
        boolean specialOccasion // e.g., birthday, anniversary
) {}
