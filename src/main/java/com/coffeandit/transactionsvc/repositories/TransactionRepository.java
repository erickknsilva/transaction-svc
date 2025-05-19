package com.coffeandit.transactionsvc.repositories;

import com.coffeandit.transactionsvc.dto.Conta;
import com.coffeandit.transactionsvc.dto.TransactionDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository
        extends MongoRepository<TransactionDto, UUID> {

    List<TransactionDto> findByConta(Conta conta);

}
