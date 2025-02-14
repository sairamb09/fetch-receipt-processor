package com.fetchrewards.challenge.receiptpointscalculator;

import java.util.List;

public class Receipt {
    private String retailer;
    private String purchaseDate;
    private String purchaseTime;
    private String total;
    private List<Item> items;
    private String imagePath; // New field to store uploaded image filename

    // Getters and Setters
    public String getRetailer() { return retailer; }
    public void setRetailer(String retailer) { this.retailer = retailer; }
    public String getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(String purchaseDate) { this.purchaseDate = purchaseDate; }
    public String getPurchaseTime() { return purchaseTime; }
    public void setPurchaseTime(String purchaseTime) { this.purchaseTime = purchaseTime; }
    public String getTotal() { return total; }
    public void setTotal(String total) { this.total = total; }
    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }
    public String getImagePath() { return imagePath; } // Getter
    public void setImagePath(String imagePath) { this.imagePath = imagePath; } // Setter
}