package com.activity11.account;

public interface IAccount {
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();
    String getAccountType();
}
