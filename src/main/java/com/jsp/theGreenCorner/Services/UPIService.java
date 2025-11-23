package com.jsp.theGreenCorner.Services;

import org.springframework.stereotype.Service;

@Service
public class UPIService {

    private final String PAYEE_UPI = "swanandgholap@ybl";
    private final String PAYEE_NAME = "Swanand%20Gholap";
    private final String CURRENCY = "INR";

    public String generateUPILink(double amount) {
        return "upi://pay?" +
                "pa=" + PAYEE_UPI +
                "&pn=" + PAYEE_NAME +
                "&am=" + amount +
                "&cu=" + CURRENCY +
                "&tn=Payment";
    }
}
