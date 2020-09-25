package com.agileengine.accounting.exception;

public class LowBalanceException extends Exception {

    public LowBalanceException(String message) {
        super(message);
    }
}
