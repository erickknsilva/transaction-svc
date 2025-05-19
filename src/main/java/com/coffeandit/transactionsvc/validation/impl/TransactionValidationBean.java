package com.coffeandit.transactionsvc.validation.impl;


import com.coffeandit.transactionsvc.dto.RequestTransactionDto;
import com.coffeandit.transactionsvc.exception.DomainBussinessException;
import com.coffeandit.transactionsvc.validation.TransactionValidation;
import com.coffeandit.transactionsvc.validation.TransactionValidator;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@ConditionalOnBean(value = {BancoTransactionValidator.class, HorarioTransactionValidator.class})
@ConditionalOnExpression("${transaction.validation.enabled:true}")
public class TransactionValidationBean implements TransactionValidation {


    private List<TransactionValidator> transactionValidatorList = new ArrayList<>();


    @PostConstruct
    public void addBeans() {
        transactionValidatorList.add(new BancoTransactionValidator());
        transactionValidatorList.add(new HorarioTransactionValidator());
    }

    @Override
    public void validate(RequestTransactionDto requestTransactionDto) {

        try {
            transactionValidatorList
                    .stream()
                    .forEach(transactionValidator -> {
                        validate(requestTransactionDto);
                    });
        } catch (DomainBussinessException ex) {
            log.error(ex.getMessage());
            log.error(ex.getCause().getLocalizedMessage());
        }

    }
}
