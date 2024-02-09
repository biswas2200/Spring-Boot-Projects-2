package com.mappings.flightserviceexample.utils;

import com.mappings.flightserviceexample.exception.InsufficientBalanceException;

import java.util.HashMap;
import java.util.Map;

public class PaymentUtils {
    private static Map<String, Double> paymentData = new HashMap<>();

    static {
        paymentData.put("acc1", 10000.0);
        paymentData.put("acc2", 8000.0);
        paymentData.put("acc3", 5000.0);
        paymentData.put("acc4", 12000.0);
    }

    public static boolean validatePayment(String accNo, double paidAmt) {
        if (paidAmt > paymentData.get(accNo))
            throw new InsufficientBalanceException("Insufficient Balance");
        else
            return true;
    }
}
