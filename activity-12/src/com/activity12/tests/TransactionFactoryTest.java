package com.activity12.tests;

import com.activity12.transaction.ITransaction;
import com.activity12.transaction.InvalidTransactionTypeException;
import com.activity12.transaction.TransactionFactory;

public class TransactionFactoryTest {
    public static void main(String[] args) {
        try {
            testValidCredit();
            testValidDebit();
            testValidTransfer();
            testInvalidType();
            testBlankTransactionId();
            testBlankDescription();
            testZeroAmount();
            testProcessOnce();
            System.out.println("All transaction factory validation checks passed.");
        } catch (AssertionError | Exception e) {
            System.out.println("Transaction factory validation failed: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void testValidCredit() {
        ITransaction t = TransactionFactory.createTransaction("CREDIT", "TXN-001", "Salary credit", 5000.0);
        assertEquals("CREDIT", t.getTransactionType());
        assertEquals("TXN-001", t.getTransactionId());
        assertEquals(5000.0, t.getAmount(), 0.001);
        assertFalse(t.isProcessed());
    }

    private static void testValidDebit() {
        ITransaction t = TransactionFactory.createTransaction("DEBIT", "TXN-002", "Groceries", 1200.0);
        assertEquals("DEBIT", t.getTransactionType());
        assertEquals("TXN-002", t.getTransactionId());
        assertEquals(1200.0, t.getAmount(), 0.001);
    }

    private static void testValidTransfer() {
        ITransaction t = TransactionFactory.createTransaction("TRANSFER", "TXN-003", "Rent transfer", 3000.0);
        assertEquals("TRANSFER", t.getTransactionType());
        assertEquals("TXN-003", t.getTransactionId());
        assertEquals(3000.0, t.getAmount(), 0.001);
    }

    private static void testInvalidType() {
        try {
            TransactionFactory.createTransaction("LOAN", "TXN-004", "Loan", 1000.0);
            throw new AssertionError("Expected InvalidTransactionTypeException for unsupported type.");
        } catch (InvalidTransactionTypeException e) {
            // expected
        }
    }

    private static void testBlankTransactionId() {
        try {
            TransactionFactory.createTransaction("CREDIT", "   ", "Salary", 2500.0);
            throw new AssertionError("Expected IllegalArgumentException for blank transaction ID.");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    private static void testBlankDescription() {
        try {
            TransactionFactory.createTransaction("DEBIT", "TXN-005", "   ", 250.0);
            throw new AssertionError("Expected IllegalArgumentException for blank description.");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    private static void testZeroAmount() {
        try {
            TransactionFactory.createTransaction("TRANSFER", "TXN-006", "Invalid amount", 0.0);
            throw new AssertionError("Expected IllegalArgumentException for zero amount.");
        } catch (IllegalArgumentException e) {
            // expected
        }
    }

    private static void testProcessOnce() {
        ITransaction t = TransactionFactory.createTransaction("CREDIT", "TXN-007", "Bonus", 1500.0);
        t.process();
        assertTrue(t.isProcessed());
        try {
            t.process();
            throw new AssertionError("Expected IllegalStateException on second processing.");
        } catch (IllegalStateException e) {
            // expected
        }
    }

    private static void assertEquals(String expected, String actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Expected: " + expected + " but got: " + actual);
        }
    }

    private static void assertEquals(double expected, double actual, double tolerance) {
        if (Math.abs(expected - actual) > tolerance) {
            throw new AssertionError("Expected: " + expected + " but got: " + actual);
        }
    }

    private static void assertTrue(boolean value) {
        if (!value) {
            throw new AssertionError("Expected value to be true.");
        }
    }

    private static void assertFalse(boolean value) {
        if (value) {
            throw new AssertionError("Expected value to be false.");
        }
    }
}
