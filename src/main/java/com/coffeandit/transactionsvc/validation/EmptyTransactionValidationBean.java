package com.coffeandit.transactionsvc.validation;

import com.coffeandit.transactionsvc.dto.RequestTransactionDto;
import com.coffeandit.transactionsvc.exception.DomainBussinessException;

public class EmptyTransactionValidationBean implements TransactionValidation {

    @Override
    public void validate(final RequestTransactionDto requestTransactionDto) throws DomainBussinessException {

    }

}
