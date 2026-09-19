package com.activity11.tests;

import com.activity11.account.AccountFactory;
import com.activity11.account.IAccount;
import com.activity11.account.InvalidAccountTypeException;

public class AccountFactoryTest {
    public static void main(String[] args) {
        testSavingsAccount();
        testCurrentAccount();
        testFixedDepositAccount();
        testSalaryAccount();
        testInvalidAccountType();
        testInvalidOperations();

        System.out.println("All account factory validation checks passed.");
    }

    private static void testSavingsAccount() {
        IAccount account = AccountFactory.createAccount("SAVINGS", "SA-1001", "Alice Johnson", "1234", 1000.0);
        assert account != null;
        assert "SAVINGS".equals(account.getAccountType());
        assert account.getBalance() == 1000.0;

        account.deposit(250.0);
        assert Math.abs(account.getBalance() - 1250.0) < 0.0001;

        account.withdraw(200.0);
        assert Math.abs(account.getBalance() - 1050.0) < 0.0001;
    }

    private static void testCurrentAccount() {
        IAccount account = AccountFactory.createAccount("CURRENT", "CA-2001", "Bob Smith", "4321", 500.0, 300.0);
        assert account != null;
        assert "CURRENT".equals(account.getAccountType());
        assert account.getBalance() == 500.0;

        account.deposit(100.0);
        assert Math.abs(account.getBalance() - 600.0) < 0.0001;

        account.withdraw(700.0);
        assert Math.abs(account.getBalance() + 100.0) < 0.0001;

        try {
            account.withdraw(500.0);
            throw new AssertionError("Expected IllegalStateException for current account overdraw beyond overdraft limit");
        } catch (IllegalStateException expected) {
            // expected
        }
    }

    private static void testFixedDepositAccount() {
        IAccount account = AccountFactory.createAccount("FIXED_DEPOSIT", "FD-3001", "Carol Lee", "9999", 2000.0);
        assert account != null;
        assert "FIXED_DEPOSIT".equals(account.getAccountType());
        assert Math.abs(account.getBalance() - 2000.0) < 0.0001;

        account.deposit(300.0);
        assert Math.abs(account.getBalance() - 2300.0) < 0.0001;

        account.withdraw(100.0);
        assert Math.abs(account.getBalance() - 2200.0) < 0.0001;
    }

    private static void testSalaryAccount() {
        IAccount account = AccountFactory.createAccount("SALARY", "SL-4001", "Derek Ross", "0000", 1500.0);
        assert account != null;
        assert "SALARY".equals(account.getAccountType());
        assert Math.abs(account.getBalance() - 1500.0) < 0.0001;

        account.deposit(400.0);
        assert Math.abs(account.getBalance() - 1900.0) < 0.0001;

        account.withdraw(250.0);
        assert Math.abs(account.getBalance() - 1650.0) < 0.0001;
    }

    private static void testInvalidAccountType() {
        try {
            AccountFactory.createAccount("UNKNOWN", "XX-1", "Test User", "1234", 100.0);
            throw new AssertionError("Expected InvalidAccountTypeException");
        } catch (InvalidAccountTypeException expected) {
            // expected
        }
    }

    private static void testInvalidOperations() {
        IAccount account = AccountFactory.createAccount("SAVINGS", "SA-5001", "Eva Green", "1234", 500.0);

        try {
            account.deposit(-10.0);
            throw new AssertionError("Expected IllegalArgumentException for negative deposit");
        } catch (IllegalArgumentException expected) {
            // expected
        }

        try {
            account.withdraw(700.0);
            throw new AssertionError("Expected IllegalStateException for over-withdraw");
        } catch (IllegalStateException expected) {
            // expected
        }
    }
}
