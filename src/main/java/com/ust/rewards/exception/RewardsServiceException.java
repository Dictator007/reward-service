package com.ust.rewards.exception;

public class RewardsServiceException extends Exception {
    private static final long serialVersionUID = 1L;

    public RewardsServiceException(String message) {
        super(message);
    }

    public RewardsServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
