package com.coffeandit.transactionsvc.domain;

import com.coffeandit.transactionsvc.dto.Conta;
import com.coffeandit.transactionsvc.dto.RequestTransactionDto;
import com.coffeandit.transactionsvc.dto.TransactionDto;
import com.coffeandit.transactionsvc.repositories.TransactionRepository;
import com.coffeandit.transactionsvc.validation.TransactionValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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


    public List<TransactionDto> findByConta(final Long codigoAgencia, final Long codigoConta) {
        var conta = new Conta();
        conta.setCodigoAgencia(codigoAgencia);
        conta.setCodigoConta(codigoConta);
        return transactionRepository.findByConta(conta);
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
