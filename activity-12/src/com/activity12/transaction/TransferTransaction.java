package com.activity12.transaction;

public class TransferTransaction extends AbstractTransaction {
    public TransferTransaction(String transactionId, String description, double amount) {
        super(transactionId, description, amount);
    }

    @Override
    public String getTransactionType() {
        return "TRANSFER";
    }
}
