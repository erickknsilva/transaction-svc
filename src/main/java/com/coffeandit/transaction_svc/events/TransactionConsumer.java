package com.coffeandit.transaction_svc.events;

import com.coffeandit.transaction_svc.domain.TransactionBusiness;
import com.coffeandit.transaction_svc.dto.SituacaoEnum;
import com.coffeandit.transaction_svc.dto.TransactionDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Slf4j
public class TransactionConsumer {

    private final ObjectMapper objectMapper;
    private final TransactionBusiness transactionBusiness;

    public TransactionConsumer(ObjectMapper objectMapper, TransactionBusiness transactionBusiness) {
        this.objectMapper = objectMapper;
        this.transactionBusiness = transactionBusiness;
    }

    @KafkaListener(topics = "${app.consumeTopic}")
    void consumeTransaction(String message) {

        try {
            var transaction = getTransaction(message);
            transactionBusiness.atualizarTransaction(transaction);

        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }

    }

    @KafkaListener(topics = "${app.returnTopic}")
    public void consumeTransactionExtorno(String message) {

        try {
            var transaction = getTransaction(message);
            log.info("Transação recebida da analise: {}", transaction);

            if (!transaction.isAnalisada()) {
                return;
            } else {
                log.info("Transação Aprovada: {}", transaction);
                transactionBusiness.aprovarTransacao(transaction);
            }

        } catch (JsonProcessingException e) {
            log.error(e.getMessage(), e);
        }

    }

    private TransactionDto getTransaction(String message) throws JsonProcessingException {

        TransactionDto transactionDto = objectMapper.readValue(message, TransactionDto.class);

        if (Objects.isNull(transactionDto.getSituacao())) {
            transactionDto.setSituacao(SituacaoEnum.NAO_ANALISADA);
        }

        transactionDto.setData(LocalDate.now());
        return transactionDto;

    }

}
