package com.coffeandit.transaction_svc.validation;

import com.coffeandit.transaction_svc.dto.RequestTransactionDto;
import com.coffeandit.transaction_svc.exception.DomainBussinessException;

@FunctionalInterface
public interface TransactionValidator {

    void validate(final RequestTransactionDto requestTransactionDto) throws DomainBussinessException;

}
