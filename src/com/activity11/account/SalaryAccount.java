package com.activity11.account;

public class SalaryAccount extends AbstractAccount {
    public SalaryAccount(String accountNumber, String accountHolderName, String pin, double initialBalance) {
        super(accountNumber, accountHolderName, pin, initialBalance);
    }

    @Override
    public String getAccountType() {
        return "SALARY";
    }

    @Override
    protected void validateWithdrawal(double amount) {
        if (amount > getBalance()) {
            throw new IllegalStateException("Salary account cannot be overdrawn.");
        }
    }
}
