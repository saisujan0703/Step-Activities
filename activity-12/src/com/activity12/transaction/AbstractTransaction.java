package com.activity12.transaction;

public abstract class AbstractTransaction implements ITransaction {
    private final String transactionId;
    private final String description;
    private final double amount;
    private boolean processed;

    protected AbstractTransaction(String transactionId, String description, double amount) {
        if (transactionId == null || transactionId.trim().isEmpty()) {
            throw new IllegalArgumentException("Transaction ID cannot be blank.");
        }

        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Transaction description cannot be blank.");
        }

        if (amount <= 0) {
            throw new IllegalArgumentException("Transaction amount must be greater than zero.");
        }

        this.transactionId = transactionId.trim();
        this.description = description.trim();
        this.amount = amount;
    }

    @Override
    public String getTransactionId() {
        return transactionId;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isProcessed() {
        return processed;
    }

    @Override
    public void process() {
        if (processed) {
            throw new IllegalStateException("Transaction has already been processed.");
        }
        processed = true;
    }
}
