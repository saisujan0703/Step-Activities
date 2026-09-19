package com.activity11.account;

public class CurrentAccount extends AbstractAccount {
    private final double overdraftLimit;

    public CurrentAccount(String accountNumber, String accountHolderName, String pin, double initialBalance,
                         double overdraftLimit) {
        super(accountNumber, accountHolderName, pin, initialBalance);
        if (overdraftLimit < 0) {
            throw new IllegalArgumentException("Overdraft limit cannot be negative.");
        }
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    @Override
    public String getAccountType() {
        return "CURRENT";
    }

    @Override
    protected void validateWithdrawal(double amount) {
        if (getBalance() - amount < -overdraftLimit) {
            throw new IllegalStateException("Current account withdrawal exceeds overdraft limit.");
        }
    }
}
