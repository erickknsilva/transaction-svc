package com.coffeandit.transaction_svc.validation.impl;

import com.coffeandit.transaction_svc.dto.RequestTransactionDto;
import com.coffeandit.transaction_svc.exception.DomainBussinessException;
import com.coffeandit.transaction_svc.validation.TransactionValidator;
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
