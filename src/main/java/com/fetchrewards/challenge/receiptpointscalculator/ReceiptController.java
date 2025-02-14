package com.fetchrewards.challenge.receiptpointscalculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;
    private static final String UPLOAD_DIR = System.getProperty("user.home") + "/uploads/";

    @PostMapping("/process")
    public Map<String, String> processReceipt(@RequestBody Receipt receipt) {
        String id = receiptService.processReceipt(receipt);
        return Map.of("id", id);
    }

    @GetMapping("/{id}/points")
    public RewardResponse getPoints(@PathVariable String id) {
        int points = receiptService.getPoints(id);
        String message = "Congratulations! You have earned " + points + " reward points.";
        return new RewardResponse(message, points);
    }

    @PostMapping("/{id}/upload")
    public Map<String, String> uploadReceiptImage(@PathVariable String id, @RequestParam("file") MultipartFile file) throws IOException {
        String fileName = receiptService.uploadReceiptImage(id, file);
        return Map.of("imageId", fileName);
    }

    @GetMapping("/history")
    public Map<String, Object> getReceiptsHistory() {
        return receiptService.getReceiptsHistory();
    }


}