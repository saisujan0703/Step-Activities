package com.activity11.account;

public class SavingsAccount extends AbstractAccount {
    public SavingsAccount(String accountNumber, String accountHolderName, String pin, double initialBalance) {
        super(accountNumber, accountHolderName, pin, initialBalance);
    }

    @Override
    public String getAccountType() {
        return "SAVINGS";
    }

    @Override
    protected void validateWithdrawal(double amount) {
        if (getBalance() - amount < 0) {
            throw new IllegalStateException("Savings account cannot be overdrawn.");
        }
    }
}
