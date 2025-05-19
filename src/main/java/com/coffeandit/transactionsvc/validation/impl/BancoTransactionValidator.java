package com.coffeandit.transactionsvc.validation.impl;

import com.coffeandit.transactionsvc.dto.RequestTransactionDto;
import com.coffeandit.transactionsvc.exception.DomainBussinessException;
import com.coffeandit.transactionsvc.validation.TransactionValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConditionalOnProperty(
        value = "transaction.validation.banco",
        havingValue = "true",
        matchIfMissing = false)
public class BancoTransactionValidator implements TransactionValidator {

    public static final int CODIGO_BANCO = 785;

    @Override
    public void validate(RequestTransactionDto requestTransactionDto) throws DomainBussinessException {
        if (Objects.isNull(requestTransactionDto.getBeneficiario())) {
            throw new DomainBussinessException("Banco do beneficiário inválido.");
        } else if (Objects.isNull(requestTransactionDto.getBeneficiario().getCodigoBanco())
                || requestTransactionDto.getBeneficiario().getCodigoBanco().compareTo((long) CODIGO_BANCO) != 0) {
            throw new DomainBussinessException("Banco do beneficiário inválido.");
        }
    }

}
