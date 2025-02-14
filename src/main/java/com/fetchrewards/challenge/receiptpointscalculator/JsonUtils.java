package com.fetchrewards.challenge.receiptpointscalculator;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Receipt convertJsonToReceipt(String json) throws Exception {
        return objectMapper.readValue(json, Receipt.class);
    }
}
