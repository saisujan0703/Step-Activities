package com.activity12.transaction;

public interface ITransaction {
    String getTransactionType();
    String getTransactionId();
    double getAmount();
    String getDescription();
    boolean isProcessed();
    void process();
}
