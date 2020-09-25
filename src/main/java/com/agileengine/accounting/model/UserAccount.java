package com.agileengine.accounting.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserAccount {

    private String name;
    private BigDecimal accountBalance;
    private List<Transaction> transactionsList;

    public UserAccount(String name, BigDecimal balance) {
        this.name = name;
        this.accountBalance = balance;
        this.transactionsList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Transaction> getTransactionsList() {
        return transactionsList;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }
}
