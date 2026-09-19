package com.activity12.transaction;

public class TransactionFactory {
    public static ITransaction createTransaction(String type, String transactionId, String description, double amount) {
        String normalizedType = normalizeTransactionType(type);

        switch (normalizedType) {
            case "CREDIT":
                return new CreditTransaction(transactionId, description, amount);
            case "DEBIT":
                return new DebitTransaction(transactionId, description, amount);
            case "TRANSFER":
                return new TransferTransaction(transactionId, description, amount);
            default:
                throw new InvalidTransactionTypeException("Invalid transaction type: " + type);
        }
    }

    private static String normalizeTransactionType(String type) {
        if (type == null) {
            throw new InvalidTransactionTypeException("Transaction type cannot be blank.");
        }
        return type.trim().toUpperCase();
    }
}
