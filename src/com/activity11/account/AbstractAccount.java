package com.activity11.account;

public abstract class AbstractAccount implements IAccount {
    private final String accountNumber;
    private final String accountHolderName;
    private final String pin;
    private String status;
    protected double balance;

    protected AbstractAccount(String accountNumber, String accountHolderName, String pin, double initialBalance) {
        validateAccountNumber(accountNumber);
        validateHolderName(accountHolderName);
        validatePin(pin);
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }

        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.pin = pin;
        this.balance = initialBalance;
        this.status = "ACTIVE";
    }

    public final String getAccountNumber() {
        return accountNumber;
    }

    public final String getAccountHolderName() {
        return accountHolderName;
    }

    public final String getStatus() {
        return status;
    }

    public final void setStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Account status cannot be empty.");
        }
        this.status = status.trim().toUpperCase();
    }

    public final boolean verifyPin(String suppliedPin) {
        if (suppliedPin == null) {
            return false;
        }
        return pin.equals(suppliedPin);
    }

    public final void changePin(String oldPin, String newPin) {
        if (!verifyPin(oldPin)) {
            throw new SecurityException("Old PIN is incorrect.");
        }
        validatePin(newPin);
        // pin is intentionally final; this change is modelled as a new state transition
        // and is handled via the same class lifecycle.
        throw new UnsupportedOperationException("PIN changes are not supported in this abstraction implementation.");
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        if (!"ACTIVE".equalsIgnoreCase(status)) {
            throw new IllegalStateException("Account is not active.");
        }
        validateDeposit(amount);
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (!"ACTIVE".equalsIgnoreCase(status)) {
            throw new IllegalStateException("Account is not active.");
        }
        validateWithdrawal(amount);
        balance -= amount;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    protected void validateDeposit(double amount) {
        // Implement account-type-specific deposit rules in subclasses when needed.
    }

    protected void validateWithdrawal(double amount) {
        // Implement account-type-specific withdrawal rules in subclasses when needed.
    }

    private void validateAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Account number is required.");
        }
    }

    private void validateHolderName(String accountHolderName) {
        if (accountHolderName == null || accountHolderName.trim().isEmpty()) {
            throw new IllegalArgumentException("Account holder name is required.");
        }
    }

    private void validatePin(String pin) {
        if (pin == null || pin.trim().isEmpty()) {
            throw new IllegalArgumentException("PIN is required.");
        }
        if (pin.length() < 4) {
            throw new IllegalArgumentException("PIN must be at least 4 digits.");
        }
    }
}
