package com.activity11.account;

import java.util.Locale;

public final class AccountFactory {
    private AccountFactory() {
    }

    public static IAccount createAccount(String accountType, String accountNumber, String accountHolderName, String pin,
                                        double initialBalance) {
        return createAccount(accountType, accountNumber, accountHolderName, pin, initialBalance, 0.0);
    }

    public static IAccount createAccount(String accountType, String accountNumber, String accountHolderName, String pin,
                                        double initialBalance, double overdraftLimit) {
        String normalizedType = normalizeAccountType(accountType);

        switch (normalizedType) {
            case "SAVINGS":
                return new SavingsAccount(accountNumber, accountHolderName, pin, initialBalance);
            case "CURRENT":
                return new CurrentAccount(accountNumber, accountHolderName, pin, initialBalance, overdraftLimit);
            case "FIXED_DEPOSIT":
                return new FixedDepositAccount(accountNumber, accountHolderName, pin, initialBalance);
            case "SALARY":
                return new SalaryAccount(accountNumber, accountHolderName, pin, initialBalance);
            default:
                throw new InvalidAccountTypeException("Unsupported account type: " + accountType);
        }
    }

    private static String normalizeAccountType(String accountType) {
        if (accountType == null || accountType.trim().isEmpty()) {
            throw new InvalidAccountTypeException("Account type is required.");
        }
        String normalized = accountType.trim().toUpperCase(Locale.ROOT).replace('-', '_');
        normalized = normalized.replace(' ', '_');

        if (normalized.equals("FIXEDDEPOSIT")) {
            return "FIXED_DEPOSIT";
        }
        return normalized;
    }
}
