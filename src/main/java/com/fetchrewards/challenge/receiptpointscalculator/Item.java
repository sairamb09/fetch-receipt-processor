package com.fetchrewards.challenge.receiptpointscalculator;

public class Item {
    private String shortDescription;
    private String price;
    private String category; // New category field (e.g., "grocery", "electronics")

    // Getters and Setters
    public String getShortDescription() { return shortDescription; }
    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
