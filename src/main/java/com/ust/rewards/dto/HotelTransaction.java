package com.ust.rewards.dto;

public record HotelTransaction(
        String bookingId,
        String customerId,
        String hotelName,
        String checkInDate,
        String checkOutDate,
        int nightsStayed,
        double amountPaid
) {}
