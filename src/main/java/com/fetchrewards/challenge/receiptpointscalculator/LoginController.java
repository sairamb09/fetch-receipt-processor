package com.fetchrewards.challenge.receiptpointscalculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    ReceiptService receiptService;

    @GetMapping("/")
    public String home() {
        return "landing"; // Redirect to landing page
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Render login page
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        Map<String, Double> categoryPoints = receiptService.getCategoryPoints();
        Map<String, Receipt> allReceipts = receiptService.getAllReceipts();
        Map<String, Integer> receiptPoints = receiptService.getReceiptPoints();

        model.addAttribute("username", principal != null ? principal.getName() : "Guest");
        model.addAttribute("points", categoryPoints);
        model.addAttribute("receipts", allReceipts); // Send all receipts
        model.addAttribute("receiptPoints", receiptPoints); // Points per receipt
        model.addAttribute("categoryPoints", categoryPoints); // Add category-based rewards

        return "dashboard";
    }

    @GetMapping("/receipts/categoryPoints")
    @ResponseBody
    public Map<String, Double> getCategoryPoints() {
        return receiptService.getCategoryPoints();
    }

}
