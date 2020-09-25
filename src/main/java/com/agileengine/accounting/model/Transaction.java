package com.agileengine.accounting.model;


import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Transaction {

    private int id;
    private TransactionType type;
    private BigDecimal amount;
    private Date effectiveDate;
    private static AtomicInteger uniqueId = new AtomicInteger();

    public Transaction(TransactionType type, BigDecimal amount, Date effectiveDate) {
        this.id = uniqueId.getAndIncrement();
        this.type = type;
        this.amount = amount;
        this.effectiveDate = effectiveDate;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
