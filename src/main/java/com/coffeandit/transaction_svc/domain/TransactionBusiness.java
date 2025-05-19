package com.coffeandit.transaction_svc.domain;

import com.coffeandit.transaction_svc.dto.RequestTransactionDto;
import com.coffeandit.transaction_svc.dto.TransactionDto;
import com.coffeandit.transaction_svc.repositort.TransactionRepository;
import com.coffeandit.transaction_svc.validation.TransactionValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class TransactionBusiness {

    private TransactionRepository transactionRepository;
    private TransactionValidation transactionValidation;

    public TransactionBusiness(TransactionRepository transactionRepository, TransactionValidation transactionValidation) {
        this.transactionRepository = transactionRepository;
        this.transactionValidation = transactionValidation;
    }


    public void save(RequestTransactionDto requestTransactionDto) {

        if (Objects.isNull(requestTransactionDto.getData())) {
            requestTransactionDto.setData(LocalDate.now());
        }

        transactionValidation.validate(requestTransactionDto);
        transactionRepository.save(requestTransactionDto);
    }

    public void atualizarTransaction(TransactionDto transactionDto) {
        log.info("Atualizando a transação {}", transactionDto);
        transactionRepository.save(transactionDto);
    }

    public void aprovarTransacao(TransactionDto transEvent) {
        var transacao = buscarTransacao(transEvent);
        if (transacao.isPresent()) {
            var transDB = transacao.get();
            if (!transDB.isAnalisada() && transEvent.isAnalisada()) {
                transDB.aprovar();
                atualizarTransaction(transDB);
                log.info("Transação aprovada: {}", transDB);
            }
        }
    }


    public Optional<TransactionDto> buscarTransacao(TransactionDto transactionDto) {

        return transactionRepository.findById(transactionDto.getUuid());

    }

}
