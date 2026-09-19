package com.activity12.transaction;

public class DebitTransaction extends AbstractTransaction {
    public DebitTransaction(String transactionId, String description, double amount) {
        super(transactionId, description, amount);
    }

    @Override
    public String getTransactionType() {
        return "DEBIT";
    }
}
