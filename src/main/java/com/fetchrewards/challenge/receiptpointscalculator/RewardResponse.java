package com.fetchrewards.challenge.receiptpointscalculator;

public class RewardResponse {
    private String message;
    private int points;

    // Constructor
    public RewardResponse(String message, int points) {
        this.message = message;
        this.points = points;
    }

    // Getters
    public String getMessage() {
        return message;
    }

    public int getPoints() {
        return points;
    }

    // Setters (optional, if needed)
    public void setMessage(String message) {
        this.message = message;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}