package com.coffeandit.transaction_svc.domain;

import com.coffeandit.transaction_svc.dto.RequestTransactionDto;
import com.coffeandit.transaction_svc.repositort.TransactionRepository;
import com.coffeandit.transaction_svc.validation.TransactionValidation;
import com.coffeandit.transaction_svc.validation.impl.TransactionValidationBean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class TransactionBusiness {

    private TransactionRepository transactionRepository;
    private TransactionValidation transactionValidation;

    public TransactionBusiness(TransactionRepository transactionRepository, TransactionValidation transactionValidation) {
        this.transactionRepository = transactionRepository;
        this.transactionValidation = transactionValidation;
    }


    public void save (RequestTransactionDto requestTransactionDto){

        if(Objects.isNull(requestTransactionDto.getData())){
            requestTransactionDto.setData(LocalDateTime.now());
        }

        transactionValidation.validate(requestTransactionDto);
        transactionRepository.save(requestTransactionDto);
    }

}
