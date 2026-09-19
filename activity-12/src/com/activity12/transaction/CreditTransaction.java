package com.activity12.transaction;

public class CreditTransaction extends AbstractTransaction {
    public CreditTransaction(String transactionId, String description, double amount) {
        super(transactionId, description, amount);
    }

    @Override
    public String getTransactionType() {
        return "CREDIT";
    }
}
