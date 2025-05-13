package com.coffeandit.transaction_svc.exception;

public class DomainBussinessException extends RuntimeException {

    public DomainBussinessException(String message) {
        super(message);
    }
}
