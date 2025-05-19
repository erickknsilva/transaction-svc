package com.coffeandit.transactionsvc.validation.impl;

import com.coffeandit.transactionsvc.dto.RequestTransactionDto;
import com.coffeandit.transactionsvc.exception.DomainBussinessException;
import com.coffeandit.transactionsvc.validation.TransactionValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@ConditionalOnProperty(
        value = "transaction.validation.banco",
        havingValue = "true",
        matchIfMissing = false)
public class HorarioTransactionValidator implements TransactionValidator {

    public static final int HOUR_END = 18;

    @Override
    public void validate(RequestTransactionDto requestTransactionDto) throws DomainBussinessException {
        if (LocalDateTime.now().getHour() > HOUR_END) {
            throw new DomainBussinessException("Não é possivel fazer transação após as 18.");
        }
    }
}
