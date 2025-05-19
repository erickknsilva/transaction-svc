package com.coffeandit.transactionsvc.exception;

public class DomainBussinessException extends RuntimeException {

    public DomainBussinessException(String message) {
        super(message);
    }
}
