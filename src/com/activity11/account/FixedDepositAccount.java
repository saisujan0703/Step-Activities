package com.activity11.account;

public class FixedDepositAccount extends AbstractAccount {
    public FixedDepositAccount(String accountNumber, String accountHolderName, String pin, double initialBalance) {
        super(accountNumber, accountHolderName, pin, initialBalance);
    }

    @Override
    public String getAccountType() {
        return "FIXED_DEPOSIT";
    }

    @Override
    protected void validateWithdrawal(double amount) {
        if (getBalance() - amount < 0) {
            throw new IllegalStateException("Fixed deposit account cannot be overdrawn.");
        }
    }
}
