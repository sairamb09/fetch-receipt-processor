package com.fetchrewards.challenge.receiptpointscalculator;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PointsCalculator {

    private static final Map<String, Double> CATEGORY_MULTIPLIERS = new HashMap<>();

    static {
        CATEGORY_MULTIPLIERS.put("grocery", 0.1);      // 10% bonus
        CATEGORY_MULTIPLIERS.put("electronics", 0.2);  // 20% bonus
        CATEGORY_MULTIPLIERS.put("clothing", 0.05);    // 5% bonus
        CATEGORY_MULTIPLIERS.put("furniture", 0.15);   // 15% bonus
    }

    public static int calculatePoints(Receipt receipt) {
        int points = 0;
        Map<String, Integer> categoryPoints = new HashMap<>();

        // Rule 1: One point per alphanumeric character in retailer name
        String retailer = receipt.getRetailer();
        points += retailer.replaceAll("[^a-zA-Z0-9]", "").length();

        // Rule 2: 50 points if total is a round dollar amount
        double total = Double.parseDouble(receipt.getTotal());
        if (total == (int) total) {
            points += 50;
        }

        // Rule 3: 25 points if total is a multiple of 0.25
        if (total % 0.25 == 0) {
            points += 25;
        }

        // Rule 4: 5 points for every two items
        List<Item> items = receipt.getItems();
        points += (items.size() / 2) * 5;

        // Rule 5: Extra points for description length multiple of 3
        for (Item item : items) {
            String description = item.getShortDescription().trim();
            double price = Double.parseDouble(item.getPrice());

            if (description.length() % 3 == 0) {
                int extraPoints = (int) Math.ceil(price * 0.2);
                points += extraPoints;
            }

            // Track points per category
            if(item.getCategory() != null && !Objects.equals(item.getCategory(), "")) {
                categoryPoints.put(item.getCategory(), categoryPoints.getOrDefault(item.getCategory(), 0) + points);
            }
        }

        // Rule 6: 6 points if the purchase day is odd
        String purchaseDate = receipt.getPurchaseDate();
        int day = Integer.parseInt(purchaseDate.split("-")[2]);
        if (day % 2 != 0) {
            points += 6;
        }

        // Rule 7: 10 points if the purchase time is between 2PM and 4PM
        String purchaseTime = receipt.getPurchaseTime();
        LocalTime time = LocalTime.parse(purchaseTime, DateTimeFormatter.ofPattern("HH:mm"));
        if (time.isAfter(LocalTime.of(14, 0)) && time.isBefore(LocalTime.of(16, 0))) {
            points += 10;
        }

        // Apply category-based bonuses using the preloaded multipliers

        for (Map.Entry<String, Integer> entry : categoryPoints.entrySet()) {
            String category = entry.getKey();
            if (category == null || category.isEmpty())
                continue;

            int categoryBasePoints = entry.getValue();

            double multiplier = CATEGORY_MULTIPLIERS.getOrDefault(category.toLowerCase(), 0.0); // Default 0 if not listed
            points += (int) (categoryBasePoints * multiplier);
        }

        return points;
    }

    public static double getCategoryBasedPoints(Item item){

        if (item.getCategory() != null){
            return (CATEGORY_MULTIPLIERS.getOrDefault(item.getCategory().toLowerCase(), 0.0) * Double.parseDouble(item.getPrice()));
        }

        return 0;
    }
}
