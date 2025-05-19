package com.coffeandit.transactionsvc.validation;

import com.coffeandit.transactionsvc.dto.RequestTransactionDto;
import com.coffeandit.transactionsvc.exception.DomainBussinessException;

@FunctionalInterface
public interface TransactionValidator {

    void validate(final RequestTransactionDto requestTransactionDto) throws DomainBussinessException;

}
