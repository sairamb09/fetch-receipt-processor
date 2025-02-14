package com.fetchrewards.challenge.receiptpointscalculator;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class ReceiptService {
    private final Map<String, Receipt> receiptMap = new HashMap<>();
    private static final String UPLOAD_DIR = new File("uploads").getAbsolutePath();

    public String processReceipt(Receipt receipt) {
        String id = UUID.randomUUID().toString();
        receiptMap.put(id, receipt);
        return id;
    }

    public int getPoints(String id) {
        Receipt receipt = receiptMap.get(id);
        if (receipt == null) {
            throw new IllegalArgumentException("Receipt not found");
        }
        return PointsCalculator.calculatePoints(receipt);
    }

    public Map<String, Double> getCategoryPoints() {
        Map<String, Double> categoryPoints = new HashMap<>();
        for (Receipt receipt : receiptMap.values()) {
            for (Item item : receipt.getItems()) {
                categoryPoints.put(
                        item.getCategory(),
                        categoryPoints.getOrDefault(item.getCategory(), 0.0) + PointsCalculator.getCategoryBasedPoints(item)
                );
            }
        }
        return categoryPoints;
    }

    public Map<String, Receipt> getAllReceipts() {
        return receiptMap;
    }

    public Map<String, Integer> getReceiptPoints() {
        Map<String, Integer> receiptPoints = new HashMap<>();
        for (Map.Entry<String, Receipt> entry : receiptMap.entrySet()) {
            String id = entry.getKey();
            Receipt receipt = entry.getValue();
            receiptPoints.put(id, PointsCalculator.calculatePoints(receipt));
        }
        return receiptPoints;
    }

    public Map<String, Object> getReceiptsHistory() {
        Map<String, Object> receiptsHistory = new HashMap<>();

        Map<String, Receipt> allReceipts = getAllReceipts();
        Map<String, Integer> receiptPoints = getReceiptPoints();

        for (Map.Entry<String, Receipt> entry : allReceipts.entrySet()) {
            String id = entry.getKey();
            Receipt receipt = entry.getValue();
            Map<String, Object> receiptDetails = new HashMap<>();

            receiptDetails.put("retailer", receipt.getRetailer());
            receiptDetails.put("purchaseDate", receipt.getPurchaseDate());
            receiptDetails.put("total", receipt.getTotal());
            receiptDetails.put("points", receiptPoints.getOrDefault(id, 0));

            // Provide correct image URL
            if (receipt.getImagePath() != null) {
                receiptDetails.put("imagePath", "/images/" + receipt.getImagePath()); // Use the new endpoint
            } else {
                receiptDetails.put("imagePath", null);
            }

            receiptsHistory.put(id, receiptDetails);
        }

        return receiptsHistory;
    }

    public String uploadReceiptImage(String receiptId, MultipartFile file) throws IOException {
        Receipt receipt = receiptMap.get(receiptId);
        if (receipt == null) {
            throw new IllegalArgumentException("Receipt not found");
        }

        // Ensure upload directory exists
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists() && !uploadDir.mkdirs()) {
            throw new IOException("Failed to create upload directory: " + UPLOAD_DIR);
        }

        // Generate unique filename
        String fileName = UUID.randomUUID() + "." + Objects.requireNonNull(file.getOriginalFilename()).split("\\.")[1];
        File uploadFile = new File(uploadDir, fileName);

        // Save file
        file.transferTo(uploadFile);

        // Store only the filename, not the full absolute path
        receipt.setImagePath(fileName);

        // Return the accessible image URL
        return "/images/" + fileName;
    }


}
